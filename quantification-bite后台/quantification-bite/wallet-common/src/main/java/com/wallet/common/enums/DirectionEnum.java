package com.wallet.common.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 方向 1.收入 2.支出
 */
public enum DirectionEnum {

    /**
     * 收入
     */
    VALUE_1(1, "收入"),

    /**
     * 支出
     */
    VALUE_2(2, "支出"),


    /**
     * 不增不减
     */
    VALUE_3(3, "不增不减"),


    ;
    private Integer code;

    private String value;

    DirectionEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    private static Map<Integer, DirectionEnum> enumMap = new HashMap<>();

    static {
        EnumSet<DirectionEnum> set = EnumSet.allOf(DirectionEnum.class);
        for (DirectionEnum each : set) {
            enumMap.put(each.getCode(), each);
        }
    }

    public static String getValue(String code) {
        return enumMap.get(code).getValue();
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
