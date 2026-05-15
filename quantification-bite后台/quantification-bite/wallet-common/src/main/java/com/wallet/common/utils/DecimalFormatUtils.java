package com.wallet.common.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * DecimalFormat工具类
 *
 * @author wupengfei wupf86@qq.com
 */
public abstract class DecimalFormatUtils {

    /**
     * 按指定的格式格式化内容
     *
     * @param value   值
     * @param pattern 格式
     * @return 格式化后的值
     */
    public static String formatFloor(Object value, String pattern) {
        if (value == null) {
            return null;
        }
        DecimalFormat df = new DecimalFormat(pattern);
        df.setRoundingMode(RoundingMode.FLOOR);
        return df.format(value);
    }

    /**
     * 截取小数点后指定的位数
     *
     * @param value 值
     * @param scale 保存的小数位
     * @return 截取后的值
     */
    public static String formatDecimalPointFloor(Object value, int scale) {
        if (value == null) {
            return null;
        }
        StringBuilder pattern = new StringBuilder();
        pattern.append("0");
        for (int i = 0; i < scale; i++) {
            if (i == 0) {
                pattern.append(".");
            }
            pattern.append("0");
        }
        return formatFloor(value, pattern.toString());
    }

}