package com.wallet.strategy.service.impl.okex.bean.swap.param;

public class PpOrderDetail {
    /**
     * 成交id
     */
    private String trade_id;

    /**
     * 合约名称，如BTC-USD-SWAP
     */
    private String instrument_id;

    /**
     * 订单信息
     */
    private String order_id;

    /**
     * 成交价格
     */
    private String price;

    /**
     * 成交数量
     */
    private String order_qty;

    /**
     * 手续费
     */
    private String fee;

    /**
     * 时间
     */
    private String timestamp;

    /**
     *  流动性
     *  T：taker; M：maker
     */
    private String exec_type;

    /**
     * 订单方向
     long（做多）或short（做空）
     */
    private String side;

    public String getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(String trade_id) {
        this.trade_id = trade_id;
    }

    public String getInstrument_id() {
        return instrument_id;
    }

    public void setInstrument_id(String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrder_qty() {
        return order_qty;
    }

    public void setOrder_qty(String order_qty) {
        this.order_qty = order_qty;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getExec_type() {
        return exec_type;
    }

    public void setExec_type(String exec_type) {
        this.exec_type = exec_type;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
