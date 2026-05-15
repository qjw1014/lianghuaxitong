package com.wallet.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal工具类
 *
 * @author wupengfei wupf86@qq.com
 */
public abstract class BigDecimalUtils {

    public final static int SCALE_NUM_8 = 8;

    /**
     * @param value1 值1
     * @param value2 值2
     * @return 相加
     */
    public static BigDecimal add(String value1, String value2) {
        return add(value1, value2, SCALE_NUM_8);
    }

    /**
     * @param value1 值1
     * @param value2 值2
     * @param scale  精度
     * @return 相加
     */
    public static BigDecimal add(String value1, String value2, int scale) {
        if (StringUtils.isBlank(value1) || StringUtils.isBlank(value2)) {
            return null;
        }
        BigDecimal add = new BigDecimal(value1).add(new BigDecimal(value2));
        String result = DecimalFormatUtils.formatDecimalPointFloor(add, scale);
        return new BigDecimal(result);
    }

    /**
     * @param value1 值1
     * @param value2 值2
     * @return 两个数值相减value1 - value2
     */
    public static BigDecimal subtract(String value1, String value2) {
        return subtract(value1, value2, SCALE_NUM_8);
    }

    /**
     * @param value1 值1
     * @param value2 值2
     * @param scale  精度
     * @return 两个数值相减value1 - value2
     */
    public static BigDecimal subtract(String value1, String value2, int scale) {
        if (StringUtils.isBlank(value1) || StringUtils.isBlank(value2)) {
            return null;
        }
        BigDecimal subtract = new BigDecimal(value1).subtract(new BigDecimal(value2));
        String result = DecimalFormatUtils.formatDecimalPointFloor(subtract, scale);
        return new BigDecimal(result);
    }

    /**
     * @param value1 值1
     * @param value2 值2
     * @return 相乘
     */
    public static BigDecimal multiply(String value1, String value2) {
        return multiply(value1, value2, SCALE_NUM_8);
    }

    /**
     * @param value1 值1
     * @param value2 值2
     * @param scale  精度
     * @return 相乘
     */
    public static BigDecimal multiply(String value1, String value2, int scale) {
        if (StringUtils.isBlank(value1) || StringUtils.isBlank(value2)) {
            return null;
        }
        BigDecimal multiply = new BigDecimal(value1).multiply(new BigDecimal(value2));
        String result = DecimalFormatUtils.formatDecimalPointFloor(multiply, scale);
        return new BigDecimal(result);
    }

    /**
     * @param value1 值1
     * @param value2 值2
     * @return value1除于value2
     */
    public static BigDecimal divide(String value1, String value2) {
        return divide(value1, value2, SCALE_NUM_8);
    }

    /**
     * @param value1 值1
     * @param value2 值2
     * @param scale  精度
     * @return value1除于value2
     */
    public static BigDecimal divide(String value1, String value2, int scale) {
        if (StringUtils.isBlank(value1) || StringUtils.isBlank(value2)) {
            return null;
        }
        return new BigDecimal(value1).divide(new BigDecimal(value2), scale, RoundingMode.FLOOR);
    }
}