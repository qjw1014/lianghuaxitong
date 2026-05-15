package com.wallet.strategy.service.impl.binance.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wallet.strategy.service.impl.binance.constant.BinanceApiConstants;
import com.wallet.strategy.service.impl.binance.domain.OrderSide;
import com.wallet.strategy.service.impl.binance.domain.OrderStatus;
import com.wallet.strategy.service.impl.binance.domain.OrderType;
import com.wallet.strategy.service.impl.binance.domain.TimeInForce;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 订单信息
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

  /**
   * 交易对
   */
  private String symbol;

  /**
   * 编号
   */
  private Long orderId;

  /**
   * 订单编号
   */
  private String clientOrderId;

  /**
   * 价格
   */
  private String price;

  /**
   * 原始数量
   */
  private String origQty;

  /**
   * 执行数量
   */
  private String executedQty;

  /**
   * 订单状态
   */
  private OrderStatus status;

  /**
   * 
   */
  private TimeInForce timeInForce;

  /**
   * 订单
   */
  private OrderType type;

  /**
   * 订单买卖状态
   */
  private OrderSide side;

  /**
   * 停止价格
   */
  private String stopPrice;

  /**
   * 冰山数量
   */
  private String icebergQty;

  /**
   * 时间戳
   */
  private long time;
  
  
  private String cummulativeQuoteQty;
  
  

  public String getCummulativeQuoteQty() {
	return cummulativeQuoteQty;
  }

  public void setCummulativeQuoteQty(String cummulativeQuoteQty) {
		this.cummulativeQuoteQty = cummulativeQuoteQty;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getClientOrderId() {
    return clientOrderId;
  }

  public void setClientOrderId(String clientOrderId) {
    this.clientOrderId = clientOrderId;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getOrigQty() {
    return origQty;
  }

  public void setOrigQty(String origQty) {
    this.origQty = origQty;
  }

  public String getExecutedQty() {
    return executedQty;
  }

  public void setExecutedQty(String executedQty) {
    this.executedQty = executedQty;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public TimeInForce getTimeInForce() {
    return timeInForce;
  }

  public void setTimeInForce(TimeInForce timeInForce) {
    this.timeInForce = timeInForce;
  }

  public OrderType getType() {
    return type;
  }

  public void setType(OrderType type) {
    this.type = type;
  }

  public OrderSide getSide() {
    return side;
  }

  public void setSide(OrderSide side) {
    this.side = side;
  }

  public String getStopPrice() {
    return stopPrice;
  }

  public void setStopPrice(String stopPrice) {
    this.stopPrice = stopPrice;
  }

  public String getIcebergQty() {
    return icebergQty;
  }

  public void setIcebergQty(String icebergQty) {
    this.icebergQty = icebergQty;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
        .append("symbol", symbol)
        .append("orderId", orderId)
        .append("clientOrderId", clientOrderId)
        .append("price", price)
        .append("origQty", origQty)
        .append("executedQty", executedQty)
        .append("status", status)
        .append("timeInForce", timeInForce)
        .append("type", type)
        .append("side", side)
        .append("stopPrice", stopPrice)
        .append("icebergQty", icebergQty)
        .append("time", time)
        .toString();
  }
}
