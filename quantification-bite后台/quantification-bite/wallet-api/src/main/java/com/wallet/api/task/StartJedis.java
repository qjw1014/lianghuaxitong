package com.wallet.api.task;


import com.wallet.common.redis.RedisUtil;
import com.wallet.redisConfig.FundsPoolRedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 项目启动加载数据
 */
@Component
public class StartJedis implements ApplicationRunner {
    private Logger log = LoggerFactory.getLogger(StartJedis.class);

    @Autowired
    private RedisUtil redisUtil;


    /***
     * 默认查询交易对数据存放redis
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            log.error("开始默认查询矿池数据存放redis");

        }catch (Exception e){
            e.printStackTrace();
            log.error("项目启动加载数据错误",e);
        }

    }
}
