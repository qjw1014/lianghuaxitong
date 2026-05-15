package com.wallet.strategy.service.impl.binance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Order execution type.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum ExecutionType {
  NEW,
  CANCELED,
  REPLACED,
  REJECTED,
  TRADE,
  EXPIRED
}