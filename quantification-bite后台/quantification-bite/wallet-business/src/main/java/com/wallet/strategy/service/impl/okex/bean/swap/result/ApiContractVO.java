package com.wallet.strategy.service.impl.okex.bean.swap.result;

public class ApiContractVO {

    /**
     * 合约名称 标的指数，如：BTC-USD
     */
    private String instrument_id;
    /**
     * 币种名称
     */
    private String underlying_index;
    /**
     * 计价货币，如BTC-USD-SWAP中的USD
     */
    private String quote_currency;

    /**
     * 标的指数，如：BTC-USD
     */
    private String underlying;

    /**
     * 币种
     */
    private String coin;
    /**
     * 合约面值
     */
    private String contract_val;
    /**
     * 创建时间
     */
    private String listing;
    /**
     * 结算时间
     */
    private String delivery;
    /**
     * 数量精度
     */
    private String size_increment;

    /**
     * 价格精度
     */
    private String tick_size;

    /**
     * 币种
     */
    private String base_currency;

    /**
     * 盈亏结算和保证金币种，BTC
     */
    private String settlement_currency;

    /**
     * 	true or false ,是否是反向合约
     */
    private Boolean is_inverse;

    /**
     * 合约面值计价币种
     */
    private String contract_val_currency;

    public String getInstrument_id() {
        return instrument_id;
    }

    public void setInstrument_id(String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getUnderlying_index() {
        return underlying_index;
    }

    public void setUnderlying_index(String underlying_index) {
        this.underlying_index = underlying_index;
    }

    public String getQuote_currency() {
        return quote_currency;
    }

    public void setQuote_currency(String quote_currency) {
        this.quote_currency = quote_currency;
    }

    public String getUnderlying() {
        return underlying;
    }

    public void setUnderlying(String underlying) {
        this.underlying = underlying;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getContract_val() {
        return contract_val;
    }

    public void setContract_val(String contract_val) {
        this.contract_val = contract_val;
    }

    public String getListing() {
        return listing;
    }

    public void setListing(String listing) {
        this.listing = listing;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getSize_increment() {
        return size_increment;
    }

    public void setSize_increment(String size_increment) {
        this.size_increment = size_increment;
    }

    public String getTick_size() {
        return tick_size;
    }

    public void setTick_size(String tick_size) {
        this.tick_size = tick_size;
    }

    public String getBase_currency() {
        return base_currency;
    }

    public void setBase_currency(String base_currency) {
        this.base_currency = base_currency;
    }

    public String getSettlement_currency() {
        return settlement_currency;
    }

    public void setSettlement_currency(String settlement_currency) {
        this.settlement_currency = settlement_currency;
    }

    public Boolean getIs_inverse() {
        return is_inverse;
    }

    public void setIs_inverse(Boolean is_inverse) {
        this.is_inverse = is_inverse;
    }

    public String getContract_val_currency() {
        return contract_val_currency;
    }

    public void setContract_val_currency(String contract_val_currency) {
        this.contract_val_currency = contract_val_currency;
    }
}
