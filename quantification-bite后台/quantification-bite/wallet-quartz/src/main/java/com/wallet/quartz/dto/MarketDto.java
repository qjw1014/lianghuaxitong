package com.wallet.quartz.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * All rights Reserved, Designed By www.bitzone.zone
 *  行情数据
 * @package_name com.zhognan.datacenter.dto
 * @class_name
 * @auth Administrator
 * @create_time 2019/4/17 10:06
 * @comments
 * @method_name
 */
public class MarketDto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 市场
     */
    private String platform;
    
    /**
     * 交易对
     */
    private String symbol;
    
    /**
     * 最新成交价
     */
    private String last;
    
    /**
     * 买一价
     */
    private String buy;
    
    /**
     * 卖一价
     */
    private String sell;
    
    /**
     * 涨幅
     */
    private String rose;
    
    /**
     * 24小时成交量（按交易货币统计）
     */
    private String baseVolume;
    
    /**
     * 24小时成交量（计价币）
     */
    private String quoteVolume;
    
    /**
     * 24小时最高价
     */
    private String high;
    
    /**
     * 24小时最低价
     */
    private String low;

    /**
     * 排序
     */
    private Integer sort;


    /**
     * 折合为cny
     */
    private String priceCny;

    /**
     * 图标
     * @return
     */
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getRose() {
        return rose;
    }

    public void setRose(String rose) {
        this.rose = rose;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getBaseVolume() {
        return baseVolume;
    }

    public void setBaseVolume(String baseVolume) {
        this.baseVolume = baseVolume;
    }

    public String getQuoteVolume() {
        return quoteVolume;
    }

    public void setQuoteVolume(String quoteVolume) {
        this.quoteVolume = quoteVolume;
    }

    public String getPriceCny() {
        return priceCny;
    }

    public void setPriceCny(String priceCny) {
        this.priceCny = priceCny;
    }
}
