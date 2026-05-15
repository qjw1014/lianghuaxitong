package com.wallet.strategy.domain.vo;


import com.wallet.strategy.domain.MartinStrategyApi;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * api对应策略信息对象 t_martin_strategy_apiVO
 * 
 * @author wallet
 * @date 2022-09-05
 */
public class MartinStrategyApiVO  extends MartinStrategyApi
{
    /** 开始时间 */
    @JSONField(serialize = false)
    // @JsonIgnore
    private String beginTime;
    
    /** 结束时间 */
    @JSONField(serialize = false)
    // @JsonIgnore
    private String endTime;
    
    public String getBeginTime()
    {
        return beginTime;
    }
    
    public void setBeginTime(String beginTime)
    {
        this.beginTime = beginTime;
    }
    
    public String getEndTime()
    {
        return endTime;
    }
    
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }
}
