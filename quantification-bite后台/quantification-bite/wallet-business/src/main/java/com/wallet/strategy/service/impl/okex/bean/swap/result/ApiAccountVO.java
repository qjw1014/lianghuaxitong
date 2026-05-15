package com.wallet.strategy.service.impl.okex.bean.swap.result;

public class ApiAccountVO {
    /**
     * 账户权益
     */
    private String equity;
    /**
     * 	账户余额
     */
    private String total_avail_balance;
    /**
     * 已用保证金
     */
    private String margin;
    /**
     * 	已实现盈亏
     */
    private String realized_pnl;
    /**
     * 未实现盈亏
     */
    private String unrealized_pnl;
    /**
     * 保证金率
     */
    private String margin_ratio;
    /**
     * 合约名称
     */
    private String instrument_id;
    /**
     * 	开仓冻结保证金
     */
    private String margin_frozen;
    /**
     * 时间
     */
    private String timestamp;
    /**
     * 仓位模式
     * 全仓：crossed
     * 逐仓：fixed
     */
    private String margin_mode;

    /**
     * 可划转数量
     */
    private String max_withdraw;

    /**
     * 维持保证金率
     */
    private String maint_margin_ratio;

    /**
     * 逐仓账户余额
     */
    private String fixed_balance;

    public String getEquity() {
        return equity;
    }

    public void setEquity(String equity) {
        this.equity = equity;
    }

    public String getTotal_avail_balance() {
        return total_avail_balance;
    }

    public void setTotal_avail_balance(String total_avail_balance) {
        this.total_avail_balance = total_avail_balance;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getRealized_pnl() {
        return realized_pnl;
    }

    public void setRealized_pnl(String realized_pnl) {
        this.realized_pnl = realized_pnl;
    }

    public String getUnrealized_pnl() {
        return unrealized_pnl;
    }

    public void setUnrealized_pnl(String unrealized_pnl) {
        this.unrealized_pnl = unrealized_pnl;
    }

    public String getMargin_ratio() {
        return margin_ratio;
    }

    public void setMargin_ratio(String margin_ratio) {
        this.margin_ratio = margin_ratio;
    }

    public String getInstrument_id() {
        return instrument_id;
    }

    public void setInstrument_id(String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getMargin_frozen() {
        return margin_frozen;
    }

    public void setMargin_frozen(String margin_frozen) {
        this.margin_frozen = margin_frozen;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMargin_mode() {
        return margin_mode;
    }

    public void setMargin_mode(String margin_mode) {
        this.margin_mode = margin_mode;
    }

    public String getMax_withdraw() {
        return max_withdraw;
    }

    public void setMax_withdraw(String max_withdraw) {
        this.max_withdraw = max_withdraw;
    }

    public String getMaint_margin_ratio() {
        return maint_margin_ratio;
    }

    public void setMaint_margin_ratio(String maint_margin_ratio) {
        this.maint_margin_ratio = maint_margin_ratio;
    }

    public String getFixed_balance() {
        return fixed_balance;
    }

    public void setFixed_balance(String fixed_balance) {
        this.fixed_balance = fixed_balance;
    }
}
