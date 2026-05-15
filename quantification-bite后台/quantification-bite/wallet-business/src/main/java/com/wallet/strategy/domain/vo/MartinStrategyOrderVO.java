package com.wallet.strategy.domain.vo;


import com.wallet.strategy.domain.MartinStrategyOrder;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 策略订单信息对象 t_martin_strategy_orderVO
 * 
 * @author wallet
 * @date 2022-10-28
 */
public class MartinStrategyOrderVO  extends MartinStrategyOrder
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
