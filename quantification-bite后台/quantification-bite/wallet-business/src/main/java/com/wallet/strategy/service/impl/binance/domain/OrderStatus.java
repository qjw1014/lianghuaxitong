package com.wallet.strategy.service.impl.binance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * NEW 新建订单
 * PARTIALLY_FILLED 部分成交
 * FILLED 全部成交
 * CANCELED 已撤销
 * PENDING_CANCEL 正在撤销中(目前不会遇到这个状态)
 * REJECTED 订单被拒绝
 * EXPIRED 订单过期(根据timeInForce参数规则)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum OrderStatus {
  NEW,
  PARTIALLY_FILLED,
  FILLED,
  CANCELED,
  PENDING_CANCEL,
  REJECTED,
  EXPIRED
}
