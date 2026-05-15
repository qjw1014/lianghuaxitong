package com.wallet.common.enums;

/**
 * jedis枚举
 *
 * @author chenle
 */
public enum JedisEnum {

    JedisEnum;

    /**
     * 是否存在
     */
    public enum NXXX {

        /**
         * NX：仅当key不存在时设置
         */
        NX("NX"),

        /**
         * XX：仅当key存在时设置
         */
        XX("XX");

        private String value;

        NXXX(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 超时时间单位
     */
    public enum EXPX {

        /**
         * 秒
         */
        EX("EX"),

        /**
         * 毫秒
         */
        PX("PX");

        private String value;

        EXPX(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
