package com.wallet.strategy.domain.vo;


import com.wallet.strategy.domain.MartinStrageyShare;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 分润统计对象 t_martin_stragey_shareVO
 * 
 * @author wallet
 * @date 2022-10-31
 */
public class MartinStrageyShareVO  extends MartinStrageyShare
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
