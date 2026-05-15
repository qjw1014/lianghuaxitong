package com.wallet.common.enums;


    /**
     * 默认添加菜单信息
     *
     * @author wallet
     */
    public enum UserPlatformMenuEnum
    {
        parentId("2701", "父级菜单"),

        component("index_home", "路由地址"),

        path("strategyInfo/", "路由地址"),
        ;

        private final String code;
        private final String info;

        UserPlatformMenuEnum(String code, String info)
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


