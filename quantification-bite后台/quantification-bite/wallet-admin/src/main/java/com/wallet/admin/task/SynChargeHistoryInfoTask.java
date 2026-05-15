package com.wallet.admin.task;

import com.alibaba.fastjson.JSONObject;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.StringUtils;
import com.wallet.strategy.domain.ChargeHistory;
import com.wallet.strategy.redisKeyConfig.StrategyRedisConfig;
import com.wallet.strategy.service.IChargeHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@EnableScheduling
@Component
public class SynChargeHistoryInfoTask {
    private static final Logger log = LoggerFactory.getLogger(SynChargeHistoryInfoTask.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IChargeHistoryService chargeHistoryService;

    /**
     *  定时历史充值记录
     */
//    @Scheduled(cron="10 0/1 * * * ?")//0 0/10 * * * ?
    private void SynChargeHistoryInfo(){
        log.info("定时同步定时历史充值记录开启");
        try {
            String  str = redisUtil.getContractOrder(StrategyRedisConfig.b_charge_history);
            log.info("定时历史充值记录，获取数据{}",str);
            if(StringUtils.isNotBlank(str)){
                JSONObject json = JSONObject.parseObject(str);
                ChargeHistory chargeHistory = new ChargeHistory();
                chargeHistory.setCoin(json.getString("coin"));
                String amount =json.getString("amount");
                chargeHistory.setAmount(new BigDecimal(amount).stripTrailingZeros());
                chargeHistory.setApiAccountId(json.getLong("account_id"));
                chargeHistory.setActionType(json.getString("action_type"));
                chargeHistory.setCreateTime(json.getDate("date"));
                chargeHistoryService.insertChargeHistory(chargeHistory);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("定时同步定时历史充值记录错误",e);
        }
    }

}
