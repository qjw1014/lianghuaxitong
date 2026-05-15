package com.wallet.admin.task;

import com.alibaba.fastjson.JSONObject;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.StringUtils;
import com.wallet.strategy.domain.RevenueCurve;
import com.wallet.strategy.mapper.RevenueCurveMapper;
import com.wallet.strategy.redisKeyConfig.StrategyRedisConfig;
import com.wallet.strategy.service.IRevenueCurveService;
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
public class SynRevenueCurveTask {
    private static final Logger log = LoggerFactory.getLogger(SynRevenueCurveTask.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IRevenueCurveService revenueCurveService;
    @Autowired
    private RevenueCurveMapper revenueCurveMapper;

    /**
     *  定时业绩走势曲线图记录
     *  20 0/1 * * * * ?
     */
//    @Scheduled(cron="0 0/2 * * * ?")// 20 0/1 * * * ?
    private void synRevenueDetail(){
        log.info("定时业绩走势曲线图记录开启");
        try {
            String  str = redisUtil.getContractOrder(StrategyRedisConfig.b_revenue_curve);
            log.info("定时同步定时收益明细记录开启，获取数据{}",str);
            if(StringUtils.isNotBlank(str)){
                JSONObject json = JSONObject.parseObject(str);
                RevenueCurve info = new RevenueCurve();
                String netValue =json.getString("net_value");
                info.setNetValue(new BigDecimal(netValue).stripTrailingZeros());
                info.setApiAccountId(json.getLong("account_id"));
                info.setDataTime(json.getString("date"));
                info.setCreateTime(new Date());
                //查询日期数据是否已存在
                RevenueCurve revenueCurve =revenueCurveMapper.selectAccountIdAndDateTime(info.getApiAccountId(),info.getDataTime());
                if(revenueCurve!=null){//说明已存在
                    revenueCurve.setNetValue(info.getNetValue());
                    revenueCurve.setDataTime(info.getDataTime());
                    revenueCurve.setCreateTime(new Date());
                    revenueCurveMapper.updateByPrimaryKeySelective(revenueCurve);
                }else{
                    revenueCurveService.insertRevenueCurve(info);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("定时业绩走势曲线图记录开启错误",e);
        }
    }

}
