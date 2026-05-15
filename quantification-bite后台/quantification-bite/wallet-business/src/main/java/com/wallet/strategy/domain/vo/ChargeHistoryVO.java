package com.wallet.strategy.domain.vo;


import com.wallet.strategy.domain.ChargeHistory;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 充值记录	对象 b_charge_historyVO
 * 
 * @author wallet
 * @date 2023-03-11
 */
public class ChargeHistoryVO  extends ChargeHistory
{

    /**
     * 账户名称
     */
    private String name;

    /** 开始时间 */
    @JSONField(serialize = false)
    // @JsonIgnore
    private String beginTime;
    
    /** 结束时间 */
    @JSONField(serialize = false)
    // @JsonIgnore
    private String endTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
