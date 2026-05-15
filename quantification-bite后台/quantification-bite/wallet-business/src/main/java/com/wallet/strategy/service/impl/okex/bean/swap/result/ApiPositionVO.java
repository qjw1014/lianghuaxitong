package com.wallet.strategy.service.impl.okex.bean.swap.result;

public class ApiPositionVO {
    /**
     *预估强平价
     */
    private String liquidation_price;
    /**
     * 	持仓数量
     */
    private String position;
    /**
     * 	可平数量
     */
    private String avail_position;
    /**
     * 	开仓平均价
     */
    private String avg_cost;
    /**
     * 	结算基准价
     */
    private String settlement_price;
    /**
     * 	合约名称
     */
    private String instrument_id;
    /**
     * 杠杆
     */
    private String leverage;
    /**
     * 	已实现盈亏
     */
    private String realized_pnl;
    /**
     * 方向
     */
    private String side;
    /**
     * 时间
     */
    private String timestamp;
    /**
     * 	最新成交价
     */
    private String last;
    /**
     * 维持保证金率
     */
    private String maint_margin_ratio;
    /**
     * 保证金
     */
    private String margin;
    /**
     * 已结算收益
     */
    private String settled_pnl;
    /**
     * 	未实现盈亏
     */
    private String unrealized_pnl;

    public String getLiquidation_price() {
        return liquidation_price;
    }

    public void setLiquidation_price(String liquidation_price) {
        this.liquidation_price = liquidation_price;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAvail_position() {
        return avail_position;
    }

    public void setAvail_position(String avail_position) {
        this.avail_position = avail_position;
    }

    public String getAvg_cost() {
        return avg_cost;
    }

    public void setAvg_cost(String avg_cost) {
        this.avg_cost = avg_cost;
    }

    public String getSettlement_price() {
        return settlement_price;
    }

    public void setSettlement_price(String settlement_price) {
        this.settlement_price = settlement_price;
    }

    public String getInstrument_id() {
        return instrument_id;
    }

    public void setInstrument_id(String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getLeverage() {
        return leverage;
    }

    public void setLeverage(String leverage) {
        this.leverage = leverage;
    }

    public String getRealized_pnl() {
        return realized_pnl;
    }

    public void setRealized_pnl(String realized_pnl) {
        this.realized_pnl = realized_pnl;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getMaint_margin_ratio() {
        return maint_margin_ratio;
    }

    public void setMaint_margin_ratio(String maint_margin_ratio) {
        this.maint_margin_ratio = maint_margin_ratio;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getSettled_pnl() {
        return settled_pnl;
    }

    public void setSettled_pnl(String settled_pnl) {
        this.settled_pnl = settled_pnl;
    }

    public String getUnrealized_pnl() {
        return unrealized_pnl;
    }

    public void setUnrealized_pnl(String unrealized_pnl) {
        this.unrealized_pnl = unrealized_pnl;
    }
}
