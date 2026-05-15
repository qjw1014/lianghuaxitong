package com.wallet.strategy.service.impl.binance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *  GTC -  成交为止
 *  IOC -  无法立即成交(吃单)的部分就撤销
 *  FOK - 无法全部立即成交就撤销
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum TimeInForce {
  GTC,
  IOC,
  FOK
}
