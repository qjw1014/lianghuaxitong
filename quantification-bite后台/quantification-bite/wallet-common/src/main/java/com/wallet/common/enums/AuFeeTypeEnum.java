package com.wallet.common.enums;


    /**
     * 类型：pen：按笔，percentage：百分比
     *
     * @author wallet
     */
    public enum AuFeeTypeEnum
    {
        pen("pen", "按笔"), percentage("percentage", "百分比");

        private final String code;
        private final String info;

        AuFeeTypeEnum(String code, String info)
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


