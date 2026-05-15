package com.wallet.admin.task;

import com.alibaba.fastjson.JSONObject;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.StringUtils;
import com.wallet.strategy.domain.RevenueDetail;
import com.wallet.strategy.domain.StrategyInfo;
import com.wallet.strategy.redisKeyConfig.StrategyRedisConfig;
import com.wallet.strategy.service.IRevenueDetailService;
import com.wallet.strategy.service.IStrategyInfoService;
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
public class SynStrategyStatusTask {
    private static final Logger log = LoggerFactory.getLogger(SynStrategyStatusTask.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IStrategyInfoService strategyInfoService;

    /**
     *  获取同步策略状态
     */
//    @Scheduled(cron="0/5 * * * * ?")
    private void SynStrategyStatus(){
        log.info("获取同步策略状态开启");
        try {
            String  str = redisUtil.getContractOrder(StrategyRedisConfig.b_strategy_status);
            log.info("获取同步策略状态开启，获取数据{}",str);
            if(StringUtils.isNotBlank(str)){
                JSONObject json = JSONObject.parseObject(str);
                Long strategyId = json.getLong("strategy_id");
                Integer status = json.getInteger("status");
                //查询策略是否存在
                StrategyInfo strategyInfo = strategyInfoService.selectStrategyInfoById(strategyId);
                if(strategyInfo!=null){
                    strategyInfo.setStrategyStatus(status);
                    strategyInfo.setUpdateTime(new Date());
                    strategyInfoService.updateStrategyInfo(strategyInfo);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取同步策略状态错误",e);
        }
    }

}
