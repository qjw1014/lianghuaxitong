package com.wallet.strategy.service.impl.okex.bean.swap.param;

/**
 * All rights Reserved, Designed By www.bitzone.zone
 *
 * @package_name com.wallet.strategy.service.impl.okex.bean.swap.param
 * @class_name
 * @auth Administrator
 * @create_time 2019\12\31 0031 18:20
 * @company 中安链信公司
 * @comments
 * @method_name
 * @return 中安链信公司版权所有
 * 注意：本内容仅限于中安链信公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class PpContractOrder {

    private String filled_qty;//成交数量

    private String fee;//手续费

    private String client_oid;//由您设置的订单ID来识别您的订单

    private String price_avg;//成交均价

    private String trigger_price;//强平的真实触发价格，仅强平单会返回此字段

    private String type;//1:开多 2:开空 3:平多 4:平空

    private String instrument_id;//合约名称

    private String size;//委托数量

    private  String price;//委托价格

    private String contract_val;//	合约面值

    private String  order_id;//合约订单

    private String order_type;//0：普通委托 1：只做Maker（Post only）2：全部成交或立即取消（FOK）3：立即成交并取消剩余（IOC）

    //-2:失败 -1:撤单成功  0:等待成交 1:部分成交 2:完全成交 3:下单中 4:撤单中
    private String state;//状态

    private String timestamp;//

    //杠杆倍数，1-100的数值
    private String leverage;

    public String getFilled_qty() {
        return filled_qty;
    }

    public void setFilled_qty(String filled_qty) {
        this.filled_qty = filled_qty;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getClient_oid() {
        return client_oid;
    }

    public void setClient_oid(String client_oid) {
        this.client_oid = client_oid;
    }

    public String getPrice_avg() {
        return price_avg;
    }

    public void setPrice_avg(String price_avg) {
        this.price_avg = price_avg;
    }

    public String getTrigger_price() {
        return trigger_price;
    }

    public void setTrigger_price(String trigger_price) {
        this.trigger_price = trigger_price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInstrument_id() {
        return instrument_id;
    }

    public void setInstrument_id(String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContract_val() {
        return contract_val;
    }

    public void setContract_val(String contract_val) {
        this.contract_val = contract_val;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLeverage() {
        return leverage;
    }

    public void setLeverage(String leverage) {
        this.leverage = leverage;
    }
}
