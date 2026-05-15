package com.wallet.quartz.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum SocketSubscibeTypeEnum {
    /**
     * add_ticker
     */
    ADD_TICKER("add_ticker","add_ticker"),

    
    /**
     * del_ticker
     */
    DEL_TICKER("del_ticker", "del_ticker"),

    ;



    private String code;
    
    private String value;
    
    SocketSubscibeTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    
    private static Map<String, SocketSubscibeTypeEnum> enumMap = new HashMap<>();
    
    static {
        EnumSet<SocketSubscibeTypeEnum> set = EnumSet.allOf(SocketSubscibeTypeEnum.class);
        for (SocketSubscibeTypeEnum each : set) {
            enumMap.put(each.getCode(), each);
        }
    }
    
    public String getCode() {
        return code;
    }
    
    public String getValue() {
        return value;
    }
}
