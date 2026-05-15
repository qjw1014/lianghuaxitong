package com.wallet.strategy.domain.vo;


import com.wallet.strategy.domain.StrategyField;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 策略字段关系对象 b_strategy_fieldVO
 * 
 * @author wallet
 * @date 2023-03-14
 */
public class StrategyFieldVO  extends StrategyField
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
