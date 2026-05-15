package com.wallet.quartz.dto;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * All rights Reserved, Designed By www.bitzone.zone
 *
 * @package_name com.zhognan.datacenter.dto
 * @class_name
 * @auth Administrator
 * @create_time 2019/4/22 18:10
 * @comments
 * @method_name
 */
public class SubscriberCacheDto {
    
    /**
     * 平台
     */
    private String platforms;
    
    /**
     * 交易对
     */
    private String symbol;
    
    /**
     * 返回数量
     */
    private Integer lastIndex;
    
    /**
     * 用户票据
     */
    private String apiToken;
    
    /**
     * 币种
     */
    private String coin;
    
    /**
     * 子账户id
     */
    private Long apiId;
    
    /**
     * 交易币
     */
    private String valueCoin;

    /**
     * 策略id
     */
    private Long strategyId;


    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(Integer lastIndex) {
        this.lastIndex = lastIndex;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    public String getValueCoin() {
        return valueCoin;
    }

    public void setValueCoin(String valueCoin) {
        this.valueCoin = valueCoin;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }
}
