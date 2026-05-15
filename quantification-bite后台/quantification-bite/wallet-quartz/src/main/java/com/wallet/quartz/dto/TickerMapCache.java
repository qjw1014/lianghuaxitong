package com.wallet.quartz.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All rights Reserved, Designed By www.bitzone.zone
 * 返回数据集合
 */
public class TickerMapCache implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 存放数据集合
     */
    private static Map<String, MarketDto> tickerMap = new HashMap<>();


    public static void tickerMapPull(String code, MarketDto dto){
        tickerMap.put(code,dto);
    }

    public static Map<String, MarketDto> getTickerMap(){
        Map<String, MarketDto> newTickerMap = new HashMap<>();
        newTickerMap.putAll(tickerMap);
        return newTickerMap;
    }


}
