package com.wallet.strategy.service.impl.binance.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 指定响应类型 ACK, RESULT, or FULL; MARKET 与 LIMIT 订单默认为FULL, 其他默认为ACK.
 * ACK 返回小量主要信息 返回速度快
 * RESULT 返回速度一般 信息比较多
 * FULL返回信息慢，返回信息很多
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum NewOrderResponseType {
    ACK,
    RESULT,
    FULL
}

