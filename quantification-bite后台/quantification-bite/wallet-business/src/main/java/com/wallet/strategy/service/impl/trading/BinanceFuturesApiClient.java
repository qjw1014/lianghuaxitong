package com.wallet.strategy.service.impl.trading;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wallet.strategy.service.impl.binance.security.HmacSHA256Signer;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Binance USDⓈ-M Futures API 客户端
 * 使用 OkHttp 直接调用 fapi.binance.com 接口
 * 支持模拟模式（不下真实单）
 */
public class BinanceFuturesApiClient {

    private static final Logger log = LoggerFactory.getLogger(BinanceFuturesApiClient.class);
    private static final String FUTURES_API_URL = "https://fapi.binance.com";
    private static final MediaType JSON = MediaType.parse("application/json");

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build();

    // ====== 模拟模式 ======

    /** 模拟模式开关 — 开启时不调用真实下单/修改杠杆接口 */
    public static boolean SIMULATION = true;

    /** 模拟账户余额（USDT） */
    public static BigDecimal SIMULATION_BALANCE = new BigDecimal("10000");

    /** 模拟手续费率（taker，吃单方） */
    public static final BigDecimal TAKER_FEE_RATE = new BigDecimal("0.0004");

    /** 模拟持仓跟踪（key = apiKey + ":" + symbol） */
    private static final ConcurrentMap<String, SimulatedPosition> simulatedPositions = new ConcurrentHashMap<>();

    private static String simKey(String apiKey, String symbol) {
        return apiKey + ":" + symbol;
    }

    /** 获取最新价格 */
    public static BigDecimal getPrice(String symbol) {
        String url = FUTURES_API_URL + "/fapi/v1/ticker/price?symbol=" + symbol;
        Request request = new Request.Builder().url(url).get().build();
        try (Response resp = client.newCall(request).execute()) {
            String body = resp.body().string();
            JSONObject json = JSONObject.parseObject(body);
            return new BigDecimal(json.getString("price"));
        } catch (Exception e) {
            log.error("获取价格失败 symbol={}", symbol, e);
            return null;
        }
    }

    /** 获取持仓信息，返回持仓数量（正=多，负=空）和入场价 */
    public static PositionInfo getPositionRisk(String apiKey, String secret, String symbol) {
        // 模拟模式：先查模拟持仓
        if (SIMULATION) {
            SimulatedPosition sp = simulatedPositions.get(simKey(apiKey, symbol));
            if (sp != null && sp.positionAmt.compareTo(BigDecimal.ZERO) > 0) {
                PositionInfo info = new PositionInfo();
                info.positionAmt = sp.positionAmt;
                info.entryPrice = sp.entryPrice;
                info.markPrice = sp.entryPrice;
                info.unRealizedProfit = BigDecimal.ZERO;
                info.liquidationPrice = BigDecimal.ZERO;
                info.leverage = sp.leverage;
                info.positionSide = "LONG";
                return info;
            }
            return null;
        }

        long timestamp = System.currentTimeMillis();
        String query = "symbol=" + symbol + "&timestamp=" + timestamp;
        String signature = HmacSHA256Signer.sign(query, secret);
        String url = FUTURES_API_URL + "/fapi/v2/positionRisk?" + query + "&signature=" + signature;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-MBX-APIKEY", apiKey)
                .get()
                .build();

        try (Response resp = client.newCall(request).execute()) {
            String body = resp.body().string();
            JSONArray arr = JSONArray.parseArray(body);
            for (int i = 0; i < arr.size(); i++) {
                JSONObject pos = arr.getJSONObject(i);
                if (symbol.equals(pos.getString("symbol"))) {
                    BigDecimal amt = new BigDecimal(pos.getString("positionAmt"));
                    if (amt.compareTo(BigDecimal.ZERO) != 0) {
                        PositionInfo info = new PositionInfo();
                        info.positionAmt = amt;
                        info.entryPrice = new BigDecimal(pos.getString("entryPrice"));
                        info.markPrice = new BigDecimal(pos.getString("markPrice"));
                        info.unRealizedProfit = new BigDecimal(pos.getString("unRealizedProfit"));
                        info.liquidationPrice = new BigDecimal(pos.getString("liquidationPrice"));
                        info.leverage = pos.getInteger("leverage");
                        info.positionSide = pos.getString("positionSide");
                        return info;
                    }
                }
            }
            return null;
        } catch (Exception e) {
            log.error("获取持仓信息失败 symbol={}", symbol, e);
            return null;
        }
    }

    /** 获取合约账户余额(USDT) */
    public static BigDecimal getUsdtBalance(String apiKey, String secret) {
        if (SIMULATION) {
            return SIMULATION_BALANCE;
        }

        long timestamp = System.currentTimeMillis();
        String query = "timestamp=" + timestamp;
        String signature = HmacSHA256Signer.sign(query, secret);
        String url = FUTURES_API_URL + "/fapi/v2/account?" + query + "&signature=" + signature;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-MBX-APIKEY", apiKey)
                .get()
                .build();

        try (Response resp = client.newCall(request).execute()) {
            String body = resp.body().string();
            JSONObject json = JSONObject.parseObject(body);
            JSONArray assets = json.getJSONArray("assets");
            for (int i = 0; i < assets.size(); i++) {
                JSONObject asset = assets.getJSONObject(i);
                if ("USDT".equals(asset.getString("asset"))) {
                    return new BigDecimal(asset.getString("walletBalance"));
                }
            }
        } catch (Exception e) {
            log.error("获取账户余额失败", e);
        }
        return BigDecimal.ZERO;
    }

    /** 设置杠杆倍数 */
    public static boolean setLeverage(String apiKey, String secret, String symbol, int leverage) {
        if (SIMULATION) {
            // 记录模拟杠杆
            String key = simKey(apiKey, symbol);
            simulatedPositions.computeIfAbsent(key, k -> new SimulatedPosition()).leverage = leverage;
            log.info("[模拟] 设置杠杆 symbol={} leverage={}", symbol, leverage);
            return true;
        }

        long timestamp = System.currentTimeMillis();
        String query = "symbol=" + symbol + "&leverage=" + leverage + "&timestamp=" + timestamp;
        String signature = HmacSHA256Signer.sign(query, secret);
        String url = FUTURES_API_URL + "/fapi/v1/leverage?" + query + "&signature=" + signature;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-MBX-APIKEY", apiKey)
                .post(RequestBody.create("", JSON))
                .build();

        try (Response resp = client.newCall(request).execute()) {
            return resp.isSuccessful();
        } catch (Exception e) {
            log.error("设置杠杆失败 symbol={}", symbol, e);
            return false;
        }
    }

    /** 市价下单 */
    public static OrderResult placeMarketOrder(String apiKey, String secret,
                                                String symbol, String side,
                                                String positionSide, String quantity) {
        if (SIMULATION) {
            return simulateOrder(apiKey, symbol, side, positionSide, quantity);
        }

        long timestamp = System.currentTimeMillis();
        String query = "symbol=" + symbol
                + "&side=" + side
                + "&positionSide=" + positionSide
                + "&type=MARKET"
                + "&quantity=" + quantity
                + "&newOrderRespType=RESULT"
                + "&timestamp=" + timestamp;
        String signature = HmacSHA256Signer.sign(query, secret);
        String url = FUTURES_API_URL + "/fapi/v1/order?" + query + "&signature=" + signature;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-MBX-APIKEY", apiKey)
                .post(RequestBody.create("", JSON))
                .build();

        try (Response resp = client.newCall(request).execute()) {
            String body = resp.body().string();
            JSONObject json = JSONObject.parseObject(body);
            if (!resp.isSuccessful()) {
                log.error("下单失败: {}", body);
                OrderResult err = new OrderResult();
                err.errorMsg = json.getString("msg");
                return err;
            }
            OrderResult result = new OrderResult();
            result.orderId = json.getLong("orderId");
            result.status = json.getString("status");
            result.executedQty = new BigDecimal(json.getString("executedQty"));
            result.cumQuote = json.containsKey("cumQuote") ? new BigDecimal(json.getString("cumQuote")) : BigDecimal.ZERO;
            result.avgPrice = json.containsKey("avgPrice") ? new BigDecimal(json.getString("avgPrice")) : BigDecimal.ZERO;
            result.success = true;
            return result;
        } catch (Exception e) {
            log.error("下单异常", e);
            OrderResult err = new OrderResult();
            err.errorMsg = e.getMessage();
            return err;
        }
    }

    /** 模拟下单 */
    private static synchronized OrderResult simulateOrder(String apiKey, String symbol,
                                                          String side, String positionSide,
                                                          String quantity) {
        BigDecimal qty = new BigDecimal(quantity);
        BigDecimal price = getPrice(symbol);
        if (price == null) price = BigDecimal.ONE;
        BigDecimal quote = qty.multiply(price).setScale(2, RoundingMode.DOWN);
        // 手续费 = 成交额 × taker费率
        BigDecimal fee = quote.multiply(TAKER_FEE_RATE).setScale(8, RoundingMode.HALF_UP);

        String key = simKey(apiKey, symbol);
        SimulatedPosition sp = simulatedPositions.computeIfAbsent(key, k -> new SimulatedPosition());

        if ("BUY".equals(side)) {
            // 加仓：更新平均入场价（手续费计入成本）
            BigDecimal totalCost = sp.positionAmt.multiply(sp.entryPrice).add(quote).add(fee);
            sp.positionAmt = sp.positionAmt.add(qty);
            sp.entryPrice = totalCost.divide(sp.positionAmt, 8, RoundingMode.HALF_UP);
            SIMULATION_BALANCE = SIMULATION_BALANCE.subtract(quote).subtract(fee);
        } else {
            // 平仓
            BigDecimal pnl = quote.subtract(sp.positionAmt.multiply(sp.entryPrice));
            sp.positionAmt = BigDecimal.ZERO;
            sp.entryPrice = BigDecimal.ZERO;
            SIMULATION_BALANCE = SIMULATION_BALANCE.add(quote).add(pnl).subtract(fee);
        }

        log.info("[模拟] {} {} {} qty={} price≈{} fee={} balance={}",
                symbol, side, positionSide, quantity, price, fee, SIMULATION_BALANCE);

        OrderResult result = new OrderResult();
        result.orderId = System.currentTimeMillis();
        result.status = "FILLED";
        result.executedQty = qty;
        result.cumQuote = quote;
        result.avgPrice = price;
        result.commission = fee;
        result.success = true;
        return result;
    }

    /** 撤销订单 */
    public static boolean cancelOrder(String apiKey, String secret, String symbol, Long orderId) {
        if (SIMULATION) {
            return true;
        }

        long timestamp = System.currentTimeMillis();
        String query = "symbol=" + symbol + "&orderId=" + orderId + "&timestamp=" + timestamp;
        String signature = HmacSHA256Signer.sign(query, secret);
        String url = FUTURES_API_URL + "/fapi/v1/order?" + query + "&signature=" + signature;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-MBX-APIKEY", apiKey)
                .delete()
                .build();

        try (Response resp = client.newCall(request).execute()) {
            return resp.isSuccessful();
        } catch (Exception e) {
            log.error("撤单失败 orderId={}", orderId, e);
            return false;
        }
    }

    /** 获取未成交委托单 */
    public static List<JSONObject> getOpenOrders(String apiKey, String secret, String symbol) {
        long timestamp = System.currentTimeMillis();
        String query = "symbol=" + symbol + "&timestamp=" + timestamp;
        String signature = HmacSHA256Signer.sign(query, secret);
        String url = FUTURES_API_URL + "/fapi/v1/openOrders?" + query + "&signature=" + signature;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-MBX-APIKEY", apiKey)
                .get()
                .build();

        List<JSONObject> orders = new ArrayList<>();
        try (Response resp = client.newCall(request).execute()) {
            String body = resp.body().string();
            JSONArray arr = JSONArray.parseArray(body);
            for (int i = 0; i < arr.size(); i++) {
                orders.add(arr.getJSONObject(i));
            }
        } catch (Exception e) {
            log.error("获取未成交订单失败 symbol={}", symbol, e);
        }
        return orders;
    }

    // ====== 模拟持仓跟踪 ======

    public static class SimulatedPosition {
        BigDecimal positionAmt = BigDecimal.ZERO;
        BigDecimal entryPrice = BigDecimal.ZERO;
        int leverage = 1;
    }

    // ====== 内部数据类 ======

    public static class PositionInfo {
        public BigDecimal positionAmt;
        public BigDecimal entryPrice;
        public BigDecimal markPrice;
        public BigDecimal unRealizedProfit;
        public BigDecimal liquidationPrice;
        public Integer leverage;
        public String positionSide;
    }

    public static class OrderResult {
        public boolean success;
        public Long orderId;
        public String status;
        public BigDecimal executedQty = BigDecimal.ZERO;
        public BigDecimal cumQuote = BigDecimal.ZERO;
        public BigDecimal avgPrice = BigDecimal.ZERO;
        public BigDecimal commission = BigDecimal.ZERO;
        public String errorMsg;
    }
}
