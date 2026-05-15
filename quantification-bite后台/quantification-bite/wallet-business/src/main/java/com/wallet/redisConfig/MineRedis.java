package com.wallet.redisConfig;

/**
 * 矿场redis配置
 */
public class MineRedis {
    //判断是否有正在生成标签
    public final static String batch_insert_mine_label_key = "batchInsertMineLabel";

    //判断矿机配置信息是否有正在生成
    public final static String batch_insert_mine_machine_key = "batchInsertMineMachineKey";

    //判断矿机NFT是否有正在铸造生成
    public final static String batch_insert_mine_machine_nft_key = "batchInsertMineMachineNftKey";


}
