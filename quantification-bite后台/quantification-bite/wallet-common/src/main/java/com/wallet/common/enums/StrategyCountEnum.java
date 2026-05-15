package com.wallet.common.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 策略次数 "once"为单次，"cycle"为循环
 *  *
 * @author chenle
 */
public enum StrategyCountEnum {

    once("once", "单次"),

    cycle("cycle", "循环"),

   ;

    private String value;
    private String name;

    public static Map<String, StrategyCountEnum> enumMap = new HashMap<>();

    static {
        EnumSet<StrategyCountEnum> set = EnumSet.allOf(StrategyCountEnum.class);
        for (StrategyCountEnum each : set) {
            enumMap.put(each.getValue(), each);
        }
    }

    StrategyCountEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * @return 是否启用值
     */
    public String getValue() {
        return value;
    }

    /**
     * @return 是否启用值的名字
     */
    public String getName() {
        return name;
    }


    /**
     * 获取是否启用枚举类
     *
     * @param value 状态值
     * @return IsEnabledEnum
     */
    public static StrategyCountEnum getEnum(int value) {
        return enumMap.get(value);
    }
}
