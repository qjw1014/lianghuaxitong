package com.wallet.quartz.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * okex
 */
public class FeiTicketDataDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 币对名称
     */
    private String name;
    
    /**
     * 最新成交价USDT
     */
    private String current_price_usd;


    /**
     * 涨跌幅
     */
    private BigDecimal change_percent;

    /**
     * 24小时成交数量
     */
    private BigDecimal vol;

    /**
     * 兑换人民币
     */
    private BigDecimal current_price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrent_price_usd() {
        return current_price_usd;
    }

    public void setCurrent_price_usd(String current_price_usd) {
        this.current_price_usd = current_price_usd;
    }

    public BigDecimal getChange_percent() {
        return change_percent;
    }

    public void setChange_percent(BigDecimal change_percent) {
        this.change_percent = change_percent;
    }

    public BigDecimal getVol() {
        return vol;
    }

    public void setVol(BigDecimal vol) {
        this.vol = vol;
    }

    public BigDecimal getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(BigDecimal current_price) {
        this.current_price = current_price;
    }
}
