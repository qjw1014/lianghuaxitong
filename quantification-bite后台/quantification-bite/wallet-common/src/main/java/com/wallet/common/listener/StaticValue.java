package com.wallet.common.listener;

/**
 * 静态变量
 *
 * @author chenle
 */
public abstract class StaticValue {

    /**
     * 应用路径
     */
    public static String realPath = "";

    /**
     * 应用的上下文路径
     */
    public static String contextPath = "";

    /**
     * 是否客户端访问，用于区分服务端，如获取ip时
     */
    public static boolean isClient = false;

    /**
     * 主机
     */
    public static String host = "119.12.167.10";

}
