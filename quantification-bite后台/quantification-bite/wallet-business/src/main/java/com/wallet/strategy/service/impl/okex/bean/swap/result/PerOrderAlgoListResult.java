package com.wallet.strategy.service.impl.okex.bean.swap.result;

/**
 * All rights Reserved, Designed By www.bitzone.zone
 *
 * @package_name com.wallet.strategy.service.impl.okex.bean.swap.result
 * @class_name
 * @auth Administrator
 * @create_time 2020\1\10 0010 15:07
 * @company 中安链信公司
 * @comments
 * @method_name
 * @return 中安链信公司版权所有
 * 注意：本内容仅限于中安链信公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class PerOrderAlgoListResult {
    //合约ID
    String instrument_id;
    //1：止盈止损 2：跟踪委托 3：冰山委托 4：时间加权
    String order_type;
    //时间
    String timestamp;
    //实际委托价格
    String real_price;
    //订单ID
    String algo_id;
    //订单状态 1: 待生效 2: 已生效 3: 已撤销 4: 部分生效 5: 暂停生效
    String status;
    //订单类型 1:开多 2:开空 3:平多 4:平空
    String type;
    //杠杠倍数
    String leverage;
    //数量
    String size;
    //策略委托价格
    String algo_price;
    //实际委托数量
    String real_amount;
    //策略委托触发价格
    String trigger_price;

    public String getInstrument_id() {
        return instrument_id;
    }

    public void setInstrument_id(String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getReal_price() {
        return real_price;
    }

    public void setReal_price(String real_price) {
        this.real_price = real_price;
    }

    public String getAlgo_id() {
        return algo_id;
    }

    public void setAlgo_id(String algo_id) {
        this.algo_id = algo_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLeverage() {
        return leverage;
    }

    public void setLeverage(String leverage) {
        this.leverage = leverage;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAlgo_price() {
        return algo_price;
    }

    public void setAlgo_price(String algo_price) {
        this.algo_price = algo_price;
    }

    public String getReal_amount() {
        return real_amount;
    }

    public void setReal_amount(String real_amount) {
        this.real_amount = real_amount;
    }

    public String getTrigger_price() {
        return trigger_price;
    }

    public void setTrigger_price(String trigger_price) {
        this.trigger_price = trigger_price;
    }
}
