package com.wallet.admin.task;

import com.alibaba.fastjson.JSONObject;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.StringUtils;
import com.wallet.strategy.domain.MartinStrategyOrder;
import com.wallet.strategy.redisKeyConfig.StrategyRedisConfig;
import com.wallet.strategy.service.IMartinStrategyOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@EnableScheduling
@Component
public class SynContractOrderInfoTask {
    private static final Logger log = LoggerFactory.getLogger(SynContractOrderInfoTask.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IMartinStrategyOrderService martinStrategyOrderService;

    /**
     *  定时同步运行策略的订单信息
     */
//    @Scheduled(cron="0/10 * * * * ?")//0 0/10 * * * ?
    private void synTronAddressTask(){
        log.info("定时同步运行策略的订单信息开启");
        try {
            String  str = redisUtil.getContractOrder(StrategyRedisConfig.history_order_list);
//            String str="{\"symbol\":\"ETCUSDT\",\"side\":\"BUY\",\"quoteQty\":\"388.60428\",\"orderId\":14871958875,\"positionSide\":\"LONG\",\"maker\":false,\"commissionAsset\":\"USDT\",\"buyer\":true,\"marginAsset\":\"USDT\",\"account_id\":129,\"price\":\"22.886\",\"qty\":\"16.98\",\"realizedPnl\":\"0\",\"commission\":\"0.15544171\",\"id\":593955020,\"time\":1666517965921}";
            if(StringUtils.isNotBlank(str)){
                JSONObject json = JSONObject.parseObject(str);
                MartinStrategyOrder order = new MartinStrategyOrder();
                order.setSymbol(json.getString("symbol"));
                order.setSide(json.getString("side"));
                String quoteQty =json.getString("quoteQty");
                order.setQuoteQty(new BigDecimal(quoteQty).stripTrailingZeros());
                order.setOrderId(json.getString("orderId"));
                order.setPositionSide(json.getString("positionSide"));
                boolean maker = json.getBoolean("maker");
                order.setMaker(maker?0:1);
                order.setCommissionAsset(json.getString("commissionAsset"));
                boolean buyer = json.getBoolean("buyer");
                order.setBuyer(buyer?0:1);
                order.setMarginAsset(json.getString("marginAsset"));
                order.setAccountId(json.getLong("account_id"));
                String price = json.getString("price");
                order.setPrice(new BigDecimal(price));
                order.setQty(new BigDecimal(json.getString("qty")));
                order.setRealizedPnl(new BigDecimal(json.getString("realizedPnl")));
                order.setCommission(new BigDecimal(json.getString("commission")));
                order.setId(json.getLong("id"));
                order.setOrderTime(new Date(json.getLong("time")));
                martinStrategyOrderService.insertMartinStrategyOrder(order);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("定时同步运行策略的订单信息错误",e);
        }
    }

}
