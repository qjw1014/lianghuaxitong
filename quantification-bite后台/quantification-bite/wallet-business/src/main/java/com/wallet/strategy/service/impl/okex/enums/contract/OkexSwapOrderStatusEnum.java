package com.wallet.strategy.service.impl.okex.enums.contract;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * All rights Reserved, Designed By www.bitzone.zone
 *
 * @package_name com.wallet.strategy.service.impl.huobi.hbdm.enums
 * @class_name
 * @auth Administrator
 * @create_time 2019\12\19 0019 14:34
 * @company 中安链信公司
 * @comments
 * @method_name
 * @return 中安链信公司版权所有
 * 注意：本内容仅限于中安链信公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public enum OkexSwapOrderStatusEnum {

    //交易所 返回 -2:失败 -1:撤单成功  0:等待成交 1:部分成交 2:完全成交 3:下单中 4:撤单中
    //系统定义 订单状态(0全部 1准备提交 2失败 3已提交 4部分成交 5部分成交已撤单 6全部成交 7已撤单 8撤单中 9.报单中 10 错单)

    /**
     * 失败
     */
    VALUE_FAIL(-2,2),

    /**
     * 撤单成功
     */
    VALUE_CANCEL(-1,7),

    /**
     * 已提交
     */
    VALUE_0(0,3),

    /**
     * 部分成交
     */
    VALUE_1(1,4),

    /**
     * 完全成交
     */
    VALUE_2(2,6),

    /**
     * 下单中
     */
    VALUE_4(4,8);


    private Integer value;

    private Integer code;

    OkexSwapOrderStatusEnum(Integer value, Integer code){
        this.value=value;
        this.code=code;
    }

    public static Map<Integer, OkexSwapOrderStatusEnum> enumMap = new HashMap<>();

    static{
        EnumSet<OkexSwapOrderStatusEnum> set = EnumSet.allOf(OkexSwapOrderStatusEnum.class);
        for(OkexSwapOrderStatusEnum statusEnum :set){
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
