package com.wallet.common.redis;

/**
 * 存放redis主键
 */
public class RedisKey {

    //推荐关系（用户端）
    public final static String user_recommend = "user_recommend";

    //推荐关系（浏览器端）
    public final static String user_recommend_browser = "user_recommend_browser";

    //推荐关系处理历史数据（用户端）
    public final static String ole_user_recommend = "ole_user_recommend";

    //推荐关系去掉的地址
    public final static String user_recommend_not_address = "user_recommend_not_address";

    //用户最低限制数量
    public final static String user_recommend_min_num = "user_recommend_min_num";

    //eth矿机算力
    public final static String machine_power_eth_rate = "machine_power_eth_rate";

    public static String setUserRecommend(){
        return user_recommend;
    }

    public static String setRecommendBrowser(){
        return user_recommend_browser;
    }
}
