package com.wallet.redisConfig;

/**
 * 矿池redis
 */
public class FundsPoolRedis {

    //判断是否有正在生成标签
    public final static String FUNDS_POOL_RATE_KEY = "fundsPoolRate";

    //判断是否有错误信息调整
    public final static String FUNDS_POOL_ERROR_KEY = "fundsPoolError";
}
