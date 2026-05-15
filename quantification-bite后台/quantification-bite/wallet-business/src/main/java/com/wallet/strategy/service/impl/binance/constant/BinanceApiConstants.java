package com.wallet.strategy.service.impl.binance.constant;

import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Value;

/**
 * Constants used throughout Binance's API.
 */
public class BinanceApiConstants {

  @Value("${platform.binance_api_base_url}")
  private static String binanceApiBaseUrl;

  @Value("${platform.binance_default_receiving_window}")
  private static Long binanceDefaultReceivingWindow;

  /**
   * REST API base URL./*"https://api.binance.com"SystemFileConfig.getValue("binance_api_base_url")
   */
  public static final String API_BASE_URL ="https://api.binance.com";

  /**
   * Streaming API base URL.
   */
  public static final String WS_API_BASE_URL = "wss://stream.binance.com:9443/ws";

  /**
   * Asset info base URL.
   */
  public static final String ASSET_INFO_API_BASE_URL = "https://www.binance.com/";

  /**
   * HTTP Header to be used for API-KEY authentication.
   */
  public static final String API_KEY_HEADER = "X-MBX-APIKEY";

  /**
   * Decorator to indicate that an endpoint requires an API key.
   */
  public static final String ENDPOINT_SECURITY_TYPE_APIKEY = "APIKEY";
  public static final String ENDPOINT_SECURITY_TYPE_APIKEY_HEADER = ENDPOINT_SECURITY_TYPE_APIKEY + ": #";

  /**
   * Decorator to indicate that an endpoint requires a signature.
   */
  public static final String ENDPOINT_SECURITY_TYPE_SIGNED = "SIGNED";
  public static final String ENDPOINT_SECURITY_TYPE_SIGNED_HEADER = ENDPOINT_SECURITY_TYPE_SIGNED + ": #";

  /**
   * Default receiving window.
   */
  //public static final long DEFAULT_RECEIVING_WINDOW = 6_000_000L;
  public static final long DEFAULT_RECEIVING_WINDOW = 5000L/*5000LLong.valueOf(SystemFileConfig.getIntValue("binance_default_receiving_window"))*/;
  /**
   * Default ToStringStyle used by toString methods. 
   * Override this to change the output format of the overridden toString methods. 
   *  - Example ToStringStyle.JSON_STYLE
   */
  public static ToStringStyle TO_STRING_BUILDER_STYLE = ToStringStyle.SHORT_PREFIX_STYLE;
}
