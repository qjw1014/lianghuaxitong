package com.wallet.strategy.service.impl.okex.enums.contract;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * All rights Reserved, Designed By www.bitzone.zone
 *
 * @package_name com.wallet.strategy.service.impl.okex.enums.contract
 * @class_name
 * @auth Administrator
 * @create_time 2020\1\2 0002 10:27
 * @company 中安链信公司
 * @comments
 * @method_name
 * @return 中安链信公司版权所有
 * 注意：本内容仅限于中安链信公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public enum  OkexSwapOrderTypeEnum {

    //0：普通委托（order_type不填或填0都是普通委托） 1：只做Maker（Post only） 2：全部成交或立即取消（FOK） 3：立即成交并取消剩余（IOC）
    /**
     * 限价
     */
    VALUE_LIMIT(1,"0"),


    /**
     * 只做Maker
     */
    VALUE_MAKER(3,"1"),

    /**
     * fok
     */
    VALUE_IOC(4,"2"),

    /**
     * ioc
     */
    VALUE_FOK(5,"3");


    private Integer value;

    private String code;

    OkexSwapOrderTypeEnum(Integer value, String code){
        this.value=value;
        this.code=code;
    }

    public static Map<Integer, OkexSwapOrderTypeEnum> enumMap = new HashMap<>();

    public static Map<String, OkexSwapOrderTypeEnum> codeEnumMap = new HashMap<>();

    static{
        EnumSet<OkexSwapOrderTypeEnum> set = EnumSet.allOf(OkexSwapOrderTypeEnum.class);
        for(OkexSwapOrderTypeEnum statusEnum :set){
            enumMap.put(statusEnum.getValue(),statusEnum);
        }

        EnumSet<OkexSwapOrderTypeEnum> setCode = EnumSet.allOf(OkexSwapOrderTypeEnum.class);
        for(OkexSwapOrderTypeEnum statusEnum :setCode){
            codeEnumMap.put(statusEnum.getCode(),statusEnum);
        }
    }

    public String getCode() {
        return code;
    }

    public Integer getValue() {
        return value;
    }
}
