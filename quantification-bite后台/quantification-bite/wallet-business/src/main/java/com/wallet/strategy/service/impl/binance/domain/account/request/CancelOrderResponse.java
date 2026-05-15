package com.wallet.strategy.service.impl.binance.domain.account.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wallet.strategy.service.impl.binance.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 撤单返回信息
 *
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelOrderResponse {

  private String symbol;//交易对

  private String origClientOrderId;

  private String orderId;//编号

  private String clientOrderId;//订单号
  
  private String status;
  
  

  public String getStatus() {
	return status;
  }

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSymbol() {
    return symbol;
  }

  public CancelOrderResponse setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public String getOrigClientOrderId() {
    return origClientOrderId;
  }

  public CancelOrderResponse setOrigClientOrderId(String origClientOrderId) {
    this.origClientOrderId = origClientOrderId;
    return this;
  }

  public String getOrderId() {
    return orderId;
  }

  public CancelOrderResponse setOrderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

  public String getClientOrderId() {
    return clientOrderId;
  }

  public CancelOrderResponse setClientOrderId(String clientOrderId) {
    this.clientOrderId = clientOrderId;
    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
        .append("symbol", symbol)
        .append("origClientOrderId", origClientOrderId)
        .append("orderId", orderId)
        .append("clientOrderId", clientOrderId)
        .toString();
  }
}
