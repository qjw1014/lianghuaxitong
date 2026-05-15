package com.wallet.sysDict.domain.vo;


import com.wallet.sysDict.domain.AuFee;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 手续费配置对象 w_au_feeVO
 * 
 * @author wallet
 * @date 2022-02-24
 */
public class AuFeeVO  extends AuFee
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
