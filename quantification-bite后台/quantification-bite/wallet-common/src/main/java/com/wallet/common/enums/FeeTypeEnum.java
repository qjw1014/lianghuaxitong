package com.wallet.common.enums;


    /**
     * 类型：release释放，nftSale 出售nft customized定制  vnftOtc  vnft-OTC交易 vnft_redeem赎回 vnft_cast(VNFT铸造申请) vnft_withdrawal(VNFT提现申请手续费)
     * vnft_cast_share(铸造分润)
     * vnft_cast_recommend_own(推荐分润自己) vnft_cast_recommend_parent(推荐分润上级)
     *
     * @author wallet
     */
    public enum FeeTypeEnum
    {
        release("release", "释放"),

        nftSale("nftSale", "出售nft"),

        customized("customized", "定制"),

        withdrawal("withdrawal", "提现"),

        vnftBuy("vnftOtc", "vnft-OTC交易"),

        vnft_redeem("vnft_redeem", "赎回"),

        vnft_cast("vnft_cast", "VNFT铸造申请"),

        vnft_withdrawal("vnft_withdrawal", "VNFT提现申请手续费"),

        vnft_cast_share("vnft_cast_share", "铸造分润"),

        vnft_cast_recommend_own("vnft_cast_recommend_own", "推荐分润自己"),

        vnft_cast_recommend_parent("vnft_cast_recommend_parent", "推荐分润上级"),

        vnft_destroy_share("vnft_destroy_share", "销毁分润"),
        ;

        private final String code;
        private final String info;

        FeeTypeEnum(String code, String info)
        {
            this.code = code;
            this.info = info;
        }

        public String getCode()
        {
            return code;
        }

        public String getInfo()
        {
            return info;
        }
    }


