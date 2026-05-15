package com.wallet.strategy.service.impl.binance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
    LIMIT 限价单
	MARKET 市价单
	STOP_LOSS 止损单
	STOP_LOSS_LIMIT 限价止损单
	TAKE_PROFIT 止盈单
	TAKE_PROFIT_LIMIT 限价止盈单
	LIMIT_MAKER 限价做市单
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum OrderType {
  LIMIT,
  MARKET,
  STOP_LOSS,
  STOP_LOSS_LIMIT,
  TAKE_PROFIT,
  TAKE_PROFIT_LIMIT,
  LIMIT_MAKER
}
