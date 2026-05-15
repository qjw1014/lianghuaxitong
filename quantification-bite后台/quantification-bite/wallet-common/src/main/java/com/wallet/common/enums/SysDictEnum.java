package com.wallet.common.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 常用配置信息
 */
public enum SysDictEnum {

    /**
     * 需持有多小枚MBC才有资格授权额度
     */
    PLEDGE_HOLD_COUNT("PLEDGE_HOLD_COUNT", "需持有多小枚MBC才有资格授权额度"),

    ;
    private String code;

    private String value;

    SysDictEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private static Map<String, SysDictEnum> enumMap = new HashMap<>();

    static {
        EnumSet<SysDictEnum> set = EnumSet.allOf(SysDictEnum.class);
        for (SysDictEnum each : set) {
            enumMap.put(each.getCode(), each);
        }
    }

    public static String getValue(String code) {
        return enumMap.get(code).getValue();
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
