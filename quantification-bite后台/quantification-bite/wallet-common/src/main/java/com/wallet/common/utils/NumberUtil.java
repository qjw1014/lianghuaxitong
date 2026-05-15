package com.wallet.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {
    // 根据阿里巴巴代码规范，将Pattern设置为全局常量
    // 通过 -?[0-9]+(\\\\.[0-9]+)? 进行匹配是否为数字
    private static Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");

    /**
     * 通过正则表达式判断字符串是否为数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        // 通过Matcher进行字符串匹配
        Matcher m = pattern.matcher(str);
        // 如果正则匹配通过 m.matches() 方法返回 true ，反之 false
        return m.matches();
    }

    public static void main(String[] args) {
        System.out.println(NumberUtil.isNumber("1.0"));
    }
}
