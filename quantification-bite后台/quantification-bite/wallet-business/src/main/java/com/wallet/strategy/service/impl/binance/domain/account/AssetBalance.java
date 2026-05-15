package com.wallet.strategy.service.impl.binance.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wallet.strategy.service.impl.binance.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * An asset balance in an Account.
 *
 * @see Account
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetBalance {

  /**
   * Asset symbol.
   */
  private String asset;//币种名称

  /**
   * Available balance.
   */
  private String free;//可用资产

  /**
   * Locked by open orders.
   */
  private String locked;//锁定资产

  public String getAsset() {
    return asset;
  }

  public void setAsset(String asset) {
    this.asset = asset;
  }

  public String getFree() {
    return free;
  }

  public void setFree(String free) {
    this.free = free;
  }

  public String getLocked() {
    return locked;
  }

  public void setLocked(String locked) {
    this.locked = locked;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
        .append("asset", asset)
        .append("free", free)
        .append("locked", locked)
        .toString();
  }
}
