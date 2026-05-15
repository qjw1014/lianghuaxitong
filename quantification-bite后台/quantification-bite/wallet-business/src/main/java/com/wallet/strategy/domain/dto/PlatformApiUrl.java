package com.wallet.strategy.domain.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ipfs上传文件配置信息
 */
@Component
@ConfigurationProperties(prefix = "platform")
public class PlatformApiUrl {
    /**
     * okex APIkey
     */
    private String okexApiUrl;

    /**
     * binance APIkey
     */
    private String binanceApiBaseUrl;

    /**
     * 默认超时时间
     */
    private String binanceDefaultReceivingWindow;


    public String getOkexApiUrl() {
        return okexApiUrl;
    }

    public void setOkexApiUrl(String okexApiUrl) {
        this.okexApiUrl = okexApiUrl;
    }

    public String getBinanceApiBaseUrl() {
        return binanceApiBaseUrl;
    }

    public void setBinanceApiBaseUrl(String binanceApiBaseUrl) {
        this.binanceApiBaseUrl = binanceApiBaseUrl;
    }

    public String getBinanceDefaultReceivingWindow() {
        return binanceDefaultReceivingWindow;
    }

    public void setBinanceDefaultReceivingWindow(String binanceDefaultReceivingWindow) {
        this.binanceDefaultReceivingWindow = binanceDefaultReceivingWindow;
    }
}
