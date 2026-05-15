package com.wallet.common.enums;


import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
     * "en-US" 英文
     * "ru-RU"  俄语
     * "zh-Hans" 简体中文
     * "zh-Hant"  繁体中文
     *
     * @author wallet
     */
    public enum LanguageEnum
    {
        /**
         * 英文
         */
        en_US("en-US", "en"),

        /**
         * 俄语
         */
        ru_RU("ru-RU", "ru"),

        /**
         * 简体中文
         */
        zh_Hans("zh-Hans", "zh"),

        /**
         * 繁体中文
         */
        zh_Hant("zh-Hant", "zh_hant"),


        ;

        private final String code;
        private final String info;

        public static Map<String, LanguageEnum> enumMap = new HashMap<>();

        static {
            EnumSet<LanguageEnum> set = EnumSet.allOf(LanguageEnum.class);
            for (LanguageEnum each : set) {
                enumMap.put(each.getCode(), each);
            }
        }

        public static String getValue(String code) {
            return enumMap.get(code).getInfo();
        }

        LanguageEnum(String code, String info)
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


