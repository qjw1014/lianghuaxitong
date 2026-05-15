package com.wallet.common.enums;
import com.wallet.common.exception.BaseException;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum PlatformEnum {
    
    /**
     * zb
     */
    VALUE_ZB("zb", "zb"),
    
    /**
     * bw
     */
    VALUE_BW("bw", "bw"),
    
    /**
     * gate
     */
    VALUE_GATE("gate","gate"),
    
    /**
     * gate
     */
    VALUE_BINANCE("binance", "binance"),
    
    /**
     * gate
     */
    VALUE_HUOBI("huobi", "huobi"),
    
    /**
     * okex
     */
    VALUE_OKEX("okex", "okex"),

    /**
     * aifive
     */
    VALUE_AIFIVE("aifive", "aifive");
    
    private String code;
    
    private String value;
    
    PlatformEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    
    public static Map<String, PlatformEnum> enumMap = new HashMap<>();
    
    static {
        EnumSet<PlatformEnum> set = EnumSet.allOf(PlatformEnum.class);
        for (PlatformEnum each : set) {
            enumMap.put(each.getCode(), each);
        }
    }
    
    public static String getValue(String code) throws BaseException {
        if(enumMap.containsKey(code)) {
            return enumMap.get(code).getValue();
        }else{
            throw new BaseException("未开通此平台");
        }
    }
    
    public String getCode() {
        return code;
    }
    
    public String getValue() {
        return value;
    }
}
