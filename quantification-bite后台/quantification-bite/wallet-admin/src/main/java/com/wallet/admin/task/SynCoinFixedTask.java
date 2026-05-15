package com.wallet.admin.task;

import com.wallet.common.constant.Constants;
import com.wallet.common.enums.Enum;
import com.wallet.common.redis.RedisUtil;
import com.wallet.strategy.domain.CoinInfo;
import com.wallet.strategy.service.ICoinInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@EnableScheduling
@Component
public class SynCoinFixedTask {
	private static final Logger log = LoggerFactory.getLogger(SynCoinFixedTask.class);
    @Resource
    private RedisUtil redis;

    @Autowired
    private ICoinInfoService coinInfoService;

    /**
     * 每天定时修改临时币（零点修改）
     */
//    @Scheduled(cron="0 0 0 * * ?")//0 0 0 * * ?
    public void setCoinRate(){
        log.info("每天定时修改临时币");
        try {
            CoinInfo info = new CoinInfo();
            info.setIsFixed(Enum.rule_validate.Y.getCode());
            List<CoinInfo> list = coinInfoService.findSelect(info);
            if(list!=null && list.size()>0){
                for(CoinInfo coinInfo:list){
                    coinInfo.setName(Constants.COIN_FIXED);
                    coinInfo.setUpdateDate(new Date());
                    coinInfoService.updateCoinInfo(coinInfo);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

}
