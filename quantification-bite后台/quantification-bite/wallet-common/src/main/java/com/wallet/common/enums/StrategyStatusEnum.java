package com.wallet.common.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 策略状态 0未开始 1开启，2停止，3已完成，4删除，5异常停止
 *
 * @author chenle
 */
public enum StrategyStatusEnum {

    VALUE_0(0, "未开始"),

    VALUE_1(1, "开启"),

    VALUE_2(2, "停止"),

    VALUE_3(3, "已完成"),

    VALUE_4(4, "删除"),

    VALUE_5(5, "异常停止"),

   ;

    private Integer value;
    private String name;

    public static Map<Integer, StrategyStatusEnum> enumMap = new HashMap<>();

    static {
        EnumSet<StrategyStatusEnum> set = EnumSet.allOf(StrategyStatusEnum.class);
        for (StrategyStatusEnum each : set) {
            enumMap.put(each.getValue(), each);
        }
    }

    StrategyStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * @return 是否启用值
     */
    public Integer getValue() {
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
    public static StrategyStatusEnum getEnum(int value) {
        return enumMap.get(value);
    }
}
