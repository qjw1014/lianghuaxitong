package com.wallet.admin.task;

import com.wallet.strategy.domain.*;
import com.wallet.strategy.domain.vo.MartinStrategyInfoVO;
import com.wallet.strategy.service.*;
import com.wallet.strategy.domain.StrageyPositionInfo;
import com.wallet.strategy.service.impl.trading.BinanceFuturesApiClient;
import com.wallet.strategy.service.impl.trading.BinanceFuturesApiClient.OrderResult;
import com.wallet.strategy.service.impl.trading.BinanceFuturesApiClient.PositionInfo;
import com.wallet.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 马丁策略交易引擎
 * 定时扫描数据库中已开启的策略，连接币安合约执行马丁策略交易
 */
@EnableScheduling
@Component
public class MartinTradingEngine {

    private static final Logger log = LoggerFactory.getLogger(MartinTradingEngine.class);

    @Autowired
    private IMartinStrategyInfoService strategyInfoService;
    @Autowired
    private IMartinStrategyApiService strategyApiService;
    @Autowired
    private IUserPlatformService userPlatformService;
    @Autowired
    private IMartinStrategySettingsService settingsService;
    @Autowired
    private IMartinStrategyOrderService orderService;
    @Autowired
    private IRevenueCurveService revenueCurveService;
    @Autowired
    private IStrageyPositionInfoService strageyPositionInfoService;

    /** 策略状态：最后开仓价 */
    private final Map<Long, BigDecimal> lastEntryPriceMap = new ConcurrentHashMap<>();
    /** 策略状态：当前加仓层级 */
    private final Map<Long, Integer> addLevelMap = new ConcurrentHashMap<>();
    /** 策略状态：首单数量(用于计算加仓) */
    private final Map<Long, BigDecimal> entryQtyMap = new ConcurrentHashMap<>();

    @Scheduled(fixedDelay = 30000)
    public void execute() {
        log.debug("马丁交易引擎开始扫描...");
        try {
            MartinStrategyInfo query = new MartinStrategyInfo();
            query.setStrategyStatus(1);
            query.setIsDelete("N");
            List<MartinStrategyInfo> strategies = strategyInfoService.selectMartinStrategyInfoList(query);
            if (strategies.isEmpty()) return;

            for (MartinStrategyInfo strategy : strategies) {
                try {
                    processStrategy(strategy);
                } catch (Exception e) {
                    log.error("交易引擎执行策略 {} 异常", strategy.getStrategyId(), e);
                }
            }
            cleanupStoppedStrategies();
        } catch (Exception e) {
            log.error("交易引擎扫描异常", e);
        }
    }

    private void processStrategy(MartinStrategyInfo strategy) {
        Long strategyId = strategy.getStrategyId();
        String symbol = strategy.getSymbol();
        if (StringUtils.isBlank(symbol)) {
            symbol = strategy.getCoin() + "USDT";
        }
        String platform = strategy.getPlatform();

        if (!"binance".equalsIgnoreCase(platform)) {
            return;
        }

        // 查询策略API绑定
        MartinStrategyApi apiQuery = new MartinStrategyApi();
        apiQuery.setStrategyId(strategyId);
        apiQuery.setIsDelete("N");
        List<MartinStrategyApi> apiList = strategyApiService.findSelect(apiQuery);
        if (apiList.isEmpty() || apiList.get(0).getAccountId() == null) {
            log.warn("策略 {} 未绑定API", strategyId);
            return;
        }
        MartinStrategyApi api = apiList.get(0);

        // 获取API密钥
        UserPlatform userPlatform = userPlatformService.selectUserPlatformById(api.getAccountId());
        if (userPlatform == null) {
            log.warn("策略 {} 的API密钥不存在", strategyId);
            return;
        }
        String apiKey = userPlatform.getAppkey();
        String secret = userPlatform.getAppsecret();

        // 获取策略设置
        MartinStrategySettings settingsQuery = new MartinStrategySettings();
        settingsQuery.setStrategyId(strategyId);
        List<MartinStrategySettings> settingsList = settingsService.findSelect(settingsQuery);
        if (settingsList.isEmpty()) {
            log.warn("策略 {} 未设置参数", strategyId);
            return;
        }
        MartinStrategySettings settings = settingsList.get(0);

        // 获取当前价格
        BigDecimal currentPrice = BinanceFuturesApiClient.getPrice(symbol);
        if (currentPrice == null || currentPrice.compareTo(BigDecimal.ZERO) <= 0) {
            log.warn("策略 {} 获取价格失败", strategyId);
            return;
        }

        // 设置杠杆
        if (strategy.getLeverage() != null && strategy.getLeverage() > 0) {
            BinanceFuturesApiClient.setLeverage(apiKey, secret, symbol, strategy.getLeverage());
        }

        // 获取持仓
        PositionInfo position = BinanceFuturesApiClient.getPositionRisk(apiKey, secret, symbol);

        // 执行策略
        if (position == null) {
            handleNoPosition(strategy, symbol, api, userPlatform, settings, currentPrice, apiKey, secret);
        } else {
            handleExistingPosition(strategy, symbol, settings, position, currentPrice, apiKey, secret, api.getAccountId());
        }

        // 更新收益曲线
        updateRevenueCurve(api, apiKey, secret);
    }

    /** 开初始仓位 */
    private void handleNoPosition(MartinStrategyInfo strategy, String symbol, MartinStrategyApi api,
                                   UserPlatform userPlatform, MartinStrategySettings settings,
                                   BigDecimal currentPrice, String apiKey, String secret) {
        Long strategyId = strategy.getStrategyId();
        boolean doLong = "true".equalsIgnoreCase(strategy.getLongOpen());
        if (!doLong) {
            return;
        }

        BigDecimal initUsdt = userPlatform.getInitUsdt();
        BigDecimal baseRate = api.getBaseRate();
        BigDecimal baseUsdt = initUsdt.multiply(baseRate).setScale(2, RoundingMode.DOWN);
        BigDecimal quantity = baseUsdt.divide(currentPrice, 8, RoundingMode.DOWN);

        if (quantity.compareTo(BigDecimal.ZERO) <= 0) {
            log.warn("策略 {} 首单数量为0", strategyId);
            return;
        }

        log.info("策略 {} 开多仓: symbol={}, qty={}, price≈{}", strategyId, symbol, quantity, currentPrice);
        OrderResult result = BinanceFuturesApiClient.placeMarketOrder(
                apiKey, secret, symbol, "BUY", "LONG", quantity.toPlainString());

        if (result != null && result.success) {
            saveOrder(symbol, "BUY", "LONG", result, api.getAccountId());
            savePosition(symbol, "LONG", quantity, currentPrice,
                    strategy.getLeverage(), api.getAccountId());
            lastEntryPriceMap.put(strategyId, currentPrice);
            addLevelMap.put(strategyId, 0);
            entryQtyMap.put(strategyId, quantity);
            log.info("策略 {} 开仓成功, orderId={}", strategyId, result.orderId);
        } else {
            log.error("策略 {} 开仓失败: {}", strategyId, result != null ? result.errorMsg : "null");
        }
    }

    /** 管理仓位（加仓/止盈/止损） */
    private void handleExistingPosition(MartinStrategyInfo strategy, String symbol,
                                         MartinStrategySettings settings,
                                         PositionInfo position, BigDecimal currentPrice,
                                         String apiKey, String secret, Long accountId) {
        Long strategyId = strategy.getStrategyId();
        if (position.positionAmt.compareTo(BigDecimal.ZERO) <= 0) return;

        BigDecimal avgEntry = position.entryPrice;
        if (avgEntry == null || avgEntry.compareTo(BigDecimal.ZERO) <= 0) avgEntry = currentPrice;

        updatePositionPrice(symbol, currentPrice, accountId);

        BigDecimal changeRate = currentPrice.subtract(avgEntry).divide(avgEntry, 6, RoundingMode.HALF_UP);

        // 止盈
        if (settings.getLongStopRate() != null && changeRate.compareTo(settings.getLongStopRate()) >= 0) {
            log.info("策略 {} 止盈: profit={}%", strategyId, changeRate.multiply(BigDecimal.valueOf(100)));
            closeAllPosition(strategy, symbol, position, apiKey, secret, accountId);
            lastEntryPriceMap.remove(strategyId);
            addLevelMap.remove(strategyId);
            return;
        }

        // 止损
        if (settings.getLossRate() != null && settings.getLossRate().compareTo(BigDecimal.ZERO) > 0
                && changeRate.compareTo(settings.getLossRate().negate()) <= 0) {
            log.info("策略 {} 止损: loss={}%", strategyId, changeRate.multiply(BigDecimal.valueOf(100)));
            closeAllPosition(strategy, symbol, position, apiKey, secret, accountId);
            stopStrategy(strategyId);
            lastEntryPriceMap.remove(strategyId);
            addLevelMap.remove(strategyId);
            return;
        }

        // 加仓
        BigDecimal lastPrice = lastEntryPriceMap.get(strategyId);
        if (lastPrice == null) {
            lastPrice = avgEntry;
            lastEntryPriceMap.put(strategyId, lastPrice);
        }

        if (settings.getAddPriceRate() != null && settings.getAddPriceRate().compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal addThreshold = lastPrice.multiply(BigDecimal.ONE.subtract(settings.getAddPriceRate()));
            if (currentPrice.compareTo(addThreshold) <= 0) {
                int level = addLevelMap.getOrDefault(strategyId, 0);
                BigDecimal multiple = settings.getAddAmountMultiple();
                if (multiple == null || multiple.compareTo(BigDecimal.ZERO) <= 0) multiple = BigDecimal.ONE;

                BigDecimal initialQty = entryQtyMap.get(strategyId);
                if (initialQty == null) initialQty = position.positionAmt;
                BigDecimal addQty = initialQty.multiply(multiple.pow(level + 1)).setScale(6, RoundingMode.DOWN);

                log.info("策略 {} 加仓 level={}: qty={}, price={}", strategyId, level + 1, addQty, currentPrice);
                OrderResult result = BinanceFuturesApiClient.placeMarketOrder(
                        apiKey, secret, symbol, "BUY", "LONG", addQty.toPlainString());

                if (result != null && result.success) {
                    saveOrder(symbol, "BUY", "LONG", result, strategy.getApiAccountId());
                    lastEntryPriceMap.put(strategyId, currentPrice);
                    addLevelMap.put(strategyId, level + 1);
                    log.info("策略 {} 加仓成功, orderId={}", strategyId, result.orderId);
                } else {
                    log.error("策略 {} 加仓失败: {}", strategyId, result != null ? result.errorMsg : "null");
                }
            }
        }
    }

    private void closeAllPosition(MartinStrategyInfo strategy, String symbol, PositionInfo position,
                                   String apiKey, String secret, Long accountId) {
        BigDecimal closeQty = position.positionAmt.abs();
        if (closeQty.compareTo(BigDecimal.ZERO) <= 0) return;

        log.info("策略 {} 平仓: symbol={}, qty={}", strategy.getStrategyId(), symbol, closeQty);
        OrderResult result = BinanceFuturesApiClient.placeMarketOrder(
                apiKey, secret, symbol, "SELL", "LONG", closeQty.toPlainString());

        if (result != null && result.success) {
            saveOrder(symbol, "SELL", "LONG", result, accountId);
            deletePosition(accountId);

            if ("cycle".equalsIgnoreCase(strategy.getLongCount())) {
                lastEntryPriceMap.remove(strategy.getStrategyId());
                addLevelMap.remove(strategy.getStrategyId());
                log.info("策略 {} 循环模式，准备下一轮", strategy.getStrategyId());
            }
        }
    }

    private void saveOrder(String symbol, String side, String positionSide,
                            OrderResult result, Long accountId) {
        try {
            MartinStrategyOrder order = new MartinStrategyOrder();
            order.setSerialId(System.currentTimeMillis() + (long)(Math.random() * 1000));
            order.setSymbol(symbol);
            order.setOrderId(String.valueOf(result.orderId));
            order.setSide(side);
            order.setPositionSide(positionSide);
            order.setPrice(result.avgPrice.compareTo(BigDecimal.ZERO) > 0 ? result.avgPrice : BigDecimal.ZERO);
            order.setQty(result.executedQty);
            order.setQuoteQty(result.cumQuote);
            order.setRealizedPnl(BigDecimal.ZERO);
            order.setCommission(BigDecimal.ZERO);
            order.setCommissionAsset("USDT");
            order.setMarginAsset("USDT");
            order.setBuyer("BUY".equals(side) ? 1 : 0);
            order.setMaker(0);
            order.setAccountId(accountId);
            order.setOrderTime(new Date());
            order.setIsSettled("N");
            orderService.insertMartinStrategyOrder(order);
        } catch (Exception e) {
            log.error("保存订单失败", e);
        }
    }

    private void updateRevenueCurve(MartinStrategyApi api, String apiKey, String secret) {
        try {
            BigDecimal balance = BinanceFuturesApiClient.getUsdtBalance(apiKey, secret);
            if (balance != null && balance.compareTo(BigDecimal.ZERO) > 0) {
                String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                List<RevenueCurve> curveList = revenueCurveService.selectRevenueCurveListOrderByTime(api.getAccountId());
                RevenueCurve existing = null;
                if (curveList != null) {
                    for (RevenueCurve c : curveList) {
                        if (today.equals(c.getDataTime())) {
                            existing = c;
                            break;
                        }
                    }
                }

                if (existing != null) {
                    existing.setNetValue(balance);
                    revenueCurveService.updateRevenueCurve(existing);
                } else {
                    RevenueCurve newCurve = new RevenueCurve();
                    newCurve.setApiAccountId(api.getAccountId());
                    newCurve.setNetValue(balance);
                    newCurve.setDataTime(today);
                    newCurve.setCreateTime(new Date());
                    revenueCurveService.insertRevenueCurve(newCurve);
                }
            }
        } catch (Exception e) {
            log.error("更新收益曲线失败", e);
        }
    }

    private void stopStrategy(Long strategyId) {
        try {
            // 更新策略状态
            MartinStrategyInfoVO vo = new MartinStrategyInfoVO();
            vo.setStrategyId(strategyId);
            vo.setStrategyStatus(2);
            vo.setStopTime(new Date());
            strategyInfoService.updateMartinStrategyInfo(vo);

            // 查询API记录并停止
            MartinStrategyApi apiQuery = new MartinStrategyApi();
            apiQuery.setStrategyId(strategyId);
            apiQuery.setIsDelete("N");
            List<MartinStrategyApi> apiList = strategyApiService.findSelect(apiQuery);
            if (!apiList.isEmpty()) {
                MartinStrategyApi api = apiList.get(0);
                api.setStrategyStatus(2);
                api.setCrateTime(new Date());
                strategyApiService.updateMartinStrategyApi(api);
            }
            log.info("策略 {} 已停止", strategyId);
        } catch (Exception e) {
            log.error("停止策略 {} 失败", strategyId, e);
        }
    }

    private void savePosition(String symbol, String positionSide, BigDecimal quantity,
                               BigDecimal price, Integer leverage, Long accountId) {
        try {
            StrageyPositionInfo position = new StrageyPositionInfo();
            position.setApiAccountId(accountId);
            position.setExchange("binance");
            position.setSymbol(symbol);
            position.setPositionSide(positionSide);
            position.setQuantity(quantity);
            position.setOpenPrice(price);
            position.setCurrenPrice(price);
            position.setLeverage(leverage != null ? leverage.longValue() : 1L);
            position.setAdl(0L);
            position.setLiquidationPrice(BigDecimal.ZERO);
            position.setPositionValue(BigDecimal.ZERO);
            position.setCretaeTime(new Date());
            strageyPositionInfoService.insertStrageyPositionInfo(position);
        } catch (Exception e) {
            log.error("保存持仓失败", e);
        }
    }

    private void updatePositionPrice(String symbol, BigDecimal currentPrice, Long accountId) {
        try {
            StrageyPositionInfo query = new StrageyPositionInfo();
            query.setApiAccountId(accountId);
            query.setSymbol(symbol);
            List<StrageyPositionInfo> list = strageyPositionInfoService.findSelect(query);
            if (list != null && !list.isEmpty()) {
                StrageyPositionInfo pos = list.get(0);
                pos.setCurrenPrice(currentPrice);
                strageyPositionInfoService.updateStrageyPositionInfo(pos);
            }
        } catch (Exception e) {
            log.error("更新持仓价格失败", e);
        }
    }

    private void deletePosition(Long accountId) {
        try {
            StrageyPositionInfo query = new StrageyPositionInfo();
            query.setApiAccountId(accountId);
            List<StrageyPositionInfo> list = strageyPositionInfoService.findSelect(query);
            if (list != null && !list.isEmpty()) {
                strageyPositionInfoService.deleteStrageyPositionInfoById(list.get(0).getId());
            }
        } catch (Exception e) {
            log.error("删除持仓失败", e);
        }
    }

    private void cleanupStoppedStrategies() {
        try {
            MartinStrategyInfo query = new MartinStrategyInfo();
            query.setStrategyStatus(1);
            query.setIsDelete("N");
            List<MartinStrategyInfo> active = strategyInfoService.selectMartinStrategyInfoList(query);
            Set<Long> activeIds = new HashSet<>();
            for (MartinStrategyInfo s : active) activeIds.add(s.getStrategyId());
            lastEntryPriceMap.keySet().removeIf(id -> !activeIds.contains(id));
            addLevelMap.keySet().removeIf(id -> !activeIds.contains(id));
            entryQtyMap.keySet().removeIf(id -> !activeIds.contains(id));
        } catch (Exception ignored) {}
    }
}
