package com.wallet.strategy.domain.vo;


import com.wallet.strategy.domain.StrategyType;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 策略类型对象 t_strategy_typeVO
 * 
 * @author wallet
 * @date 2022-09-01
 */
public class StrategyTypeVO  extends StrategyType
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
