package com.wallet.api.task;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.wallet.common.redis.RedisUtil;

@EnableScheduling
@Component
public class CoinRateTask {
	private static final Logger log = LoggerFactory.getLogger(CoinRateTask.class);
    @Resource
    private RedisUtil redis;

    /**
     * 测试
     */
    @Scheduled(cron="0/10 * * * * ?")
    public void setCoinRate(){
        log.info("测试");
        try {

        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

}
