package com.wallet.admin.task;

import com.alibaba.fastjson.JSONObject;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.StringUtils;
import com.wallet.strategy.domain.RevenueDetail;
import com.wallet.strategy.redisKeyConfig.StrategyRedisConfig;
import com.wallet.strategy.service.IRevenueDetailService;
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
public class SynRevenueDetailTask {
    private static final Logger log = LoggerFactory.getLogger(SynRevenueDetailTask.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IRevenueDetailService revenueDetailService;

    /**
     *  定时收益明细记录
     */
//    @Scheduled(cron="30 0/1 * * * ?")
    private void synRevenueDetail(){
        log.info("定时同步定时收益明细记录开启");
        try {
            String  str = redisUtil.getContractOrder(StrategyRedisConfig.b_revenue_detail);
            log.info("定时同步定时收益明细记录开启，获取数据{}",str);
            if(StringUtils.isNotBlank(str)){
                JSONObject json = JSONObject.parseObject(str);
                RevenueDetail info = new RevenueDetail();
                info.setProfitCoin(json.getString("profit_coin"));
                String profit =json.getString("profit");
                info.setProfit(new BigDecimal(profit).stripTrailingZeros());
                info.setApiAccountId(json.getLong("account_id"));
                info.setDataTime(json.getString("date"));
                info.setCreateTime(new Date());
                revenueDetailService.insertRevenueDetail(info);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("定时同步定时收益明细记录记录错误",e);
        }
    }

}
