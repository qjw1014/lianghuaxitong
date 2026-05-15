package com.wallet.quartz.dto;

import com.wallet.common.utils.BigDecimalUtils;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * okex
 */
public class OkexTicketDataDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 币对名称
     */
    private String instId;
    
    /**
     * 最新成交价
     */
    private String last;

    /**
     * 最新成交的数量
     */
    private String lastSz;

    /**
     * 买一价
     */
    private String bidPx;
    
    /**
     * 卖一价
     */
    private String askPx;
    
    /**
     * 24小时开盘价
     */
    private String open24h;
    
    /**
     * 24小时最高价
     */
    private String high24h;
    
    /**
     * 24小时最低价
     */
    private String low24h;

    /**
     * 24小时成交量，按交易货币统计
     */
    private String volCcy24h;

    /**
     * 	24小时成交量，按计价货币统计
     */
    private String vol24h;

    /**
     * UTC 0 时开盘价
     */
    private String sodUtc0;

    /**
     * 	UTC+8 时开盘价
     */
    private String sodUtc8;
    
    /**
     * 计算涨幅
     * @return
     */
    public String getRose(){
        BigDecimal subtract = BigDecimalUtils.subtract(last,sodUtc8);
        BigDecimal rose = BigDecimalUtils.divide(subtract.toString(),sodUtc8).multiply(new BigDecimal(100));
        rose = rose.setScale(2,BigDecimal.ROUND_DOWN);
        return rose.toString();
    }


    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLastSz() {
        return lastSz;
    }

    public void setLastSz(String lastSz) {
        this.lastSz = lastSz;
    }

    public String getBidPx() {
        return bidPx;
    }

    public void setBidPx(String bidPx) {
        this.bidPx = bidPx;
    }

    public String getAskPx() {
        return askPx;
    }

    public void setAskPx(String askPx) {
        this.askPx = askPx;
    }

    public String getOpen24h() {
        return open24h;
    }

    public void setOpen24h(String open24h) {
        this.open24h = open24h;
    }

    public String getHigh24h() {
        return high24h;
    }

    public void setHigh24h(String high24h) {
        this.high24h = high24h;
    }

    public String getLow24h() {
        return low24h;
    }

    public void setLow24h(String low24h) {
        this.low24h = low24h;
    }

    public String getVolCcy24h() {
        return volCcy24h;
    }

    public void setVolCcy24h(String volCcy24h) {
        this.volCcy24h = volCcy24h;
    }

    public String getVol24h() {
        return vol24h;
    }

    public void setVol24h(String vol24h) {
        this.vol24h = vol24h;
    }

    public String getSodUtc0() {
        return sodUtc0;
    }

    public void setSodUtc0(String sodUtc0) {
        this.sodUtc0 = sodUtc0;
    }

    public String getSodUtc8() {
        return sodUtc8;
    }

    public void setSodUtc8(String sodUtc8) {
        this.sodUtc8 = sodUtc8;
    }
}
