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
public enum OkexEntrustTypeEnum {

    //委托类型 1合约下单 2止盈止损 3跟踪委托 4冰山委托 5时间加权
    /**
     * 1合约下单
     */
    VALUE_TRADE(1,0),

    /**
     * 止盈止损
     */
    VALUE_STOP(2,1),

    /**
     * 跟踪委托
     */
    VALUE_TRAC(3,2),

    /**
     * 冰山委托
     */
    VALUE_ICEBERG(4,3),

    /**
     * 时间加权
     */
    VALUE_TIME(5,4);


    private Integer value;

    private Integer code;

    OkexEntrustTypeEnum(Integer value, Integer code){
        this.value=value;
        this.code=code;
    }

    public static Map<Integer, OkexEntrustTypeEnum> enumMap = new HashMap<>();

    static{
        EnumSet<OkexEntrustTypeEnum> set = EnumSet.allOf(OkexEntrustTypeEnum.class);
        for(OkexEntrustTypeEnum statusEnum :set){
            enumMap.put(statusEnum.getValue(),statusEnum);
        }
    }

    public Integer getCode() {
        return code;
    }

    public Integer getValue() {
        return value;
    }
}
