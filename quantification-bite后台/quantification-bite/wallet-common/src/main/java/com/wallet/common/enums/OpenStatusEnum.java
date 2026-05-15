package com.wallet.common.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 方向状态 True为启动，False为关闭
 *
 * @author chenle
 */
public enum OpenStatusEnum {

    TRUE("True", "启动"),

    FALSE("False", "关闭"),


   ;

    private String value;
    private String name;

    public static Map<String, OpenStatusEnum> enumMap = new HashMap<>();

    static {
        EnumSet<OpenStatusEnum> set = EnumSet.allOf(OpenStatusEnum.class);
        for (OpenStatusEnum each : set) {
            enumMap.put(each.getValue(), each);
        }
    }

    OpenStatusEnum(String value, String name) {
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
    public static OpenStatusEnum getEnum(int value) {
        return enumMap.get(value);
    }
}
