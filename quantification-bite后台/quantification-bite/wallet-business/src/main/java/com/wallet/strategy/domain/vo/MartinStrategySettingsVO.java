package com.wallet.strategy.domain.vo;


import com.wallet.strategy.domain.MartinStrategySettings;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 策略比例设置对象 t_martin_strategy_settingsVO
 * 
 * @author wallet
 * @date 2022-08-31
 */
public class MartinStrategySettingsVO  extends MartinStrategySettings
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
