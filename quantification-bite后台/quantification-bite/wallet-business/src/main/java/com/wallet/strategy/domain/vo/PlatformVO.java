package com.wallet.strategy.domain.vo;


import com.wallet.strategy.domain.Platform;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 交易平台对象 t_platformVO
 * 
 * @author wallet
 * @date 2022-09-01
 */
public class PlatformVO  extends Platform
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
