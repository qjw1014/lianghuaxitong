package com.wallet.strategy.domain.vo;


import com.wallet.strategy.domain.StrategyInfo;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 策略信息对象 b_strategy_infoVO
 * 
 * @author wallet
 * @date 2023-03-14
 */
public class StrategyInfoVO  extends StrategyInfo
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
