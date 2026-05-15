package com.wallet.common.enums;


    /**
     * 交易方向 buy买 sell卖
     *
     * @author wallet
     */
    public enum SaleDirectionTypeEnum
    {
        buy("buy", "买"), sell("sell", "卖");

        private final String code;
        private final String info;

        SaleDirectionTypeEnum(String code, String info)
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


