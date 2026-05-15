package com.wallet.strategy.redisKeyConfig;

/**
 * redis键值存放
 */
public class StrategyRedisConfig {
    /**
     * 存放策略信息
     */
    public final static String settings="settings";

    /**
     * 存储策略键
     */
    public static final String account="account_info";

    /**
     * 存放开启的策略
     */
    public final static String MARTIN_strategy="MARTIN_strategy";

    /**
     * 存放改动策略信息
     */
    public final static String update_strategy="update_strategy";

    /**
     * 获取资产
     */
    public final static String balances="balances";

    /**
     * 获取订单信息
     */
    public final static String history_order_list="history_order_list";


    //比特方舟
    /**
     * 获取统计账号信息
     */
    public final static String b_account_detail="account_detail";

    /**
     * 获取仓位信息
     */
    public final static String b_position_info="position_info";


    /**
     * 收益信息，业绩走势图
     */
    public final static String b_revenue_curve="revenue_curve";

    /**
     * 收益明细
     */
    public final static String b_revenue_detail="revenue_detail";

    /**
     * 充值记录
     */
    public final static String b_charge_history="charge_history";

    /**
     * 存放策略信息
     */
    public final static String b_strategy_data="strategy_data";


    /**
     * python修改状态是，需要同步
     */
    public final static String b_strategy_status="strategy_status";

}
