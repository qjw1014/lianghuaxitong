package com.wallet.quartz.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * All rights Reserved, Designed By www.bitzone.zone
 *
 * @package_name com.zhognan.datacenter.constant
 * @class_name
 * @auth Administrator
 * @create_time 2019/4/22 17:14
 * @company 中安链信公司
 * @comments
 * @method_name
 * @return 中安链信公司版权所有
 * 注意：本内容仅限于中安链信公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public enum SocketSendTypeEnum {

    /**
     * currDateTime
     */
    CURR_DATE_TIME("currDateTime", "currDateTime"),

    /**
     * btc_usdt
     */
    TICKER("ticker","ticker"),


    ;

    private String code;
    
    private String value;
    
    SocketSendTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    
    private static Map<String, SocketSendTypeEnum> enumMap = new HashMap<>();
    
    static {
        EnumSet<SocketSendTypeEnum> set = EnumSet.allOf(SocketSendTypeEnum.class);
        for (SocketSendTypeEnum each : set) {
            enumMap.put(each.getCode(), each);
        }
    }
    
    public String getCode() {
        return code;
    }
    
    public String getValue() {
        return value;
    }
}
