package com.wallet.chain.domain.vo;


import com.wallet.chain.domain.Chain;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 链对象 w_chainVO
 * 
 * @author wallet
 * @date 2021-10-25
 */
public class ChainVO  extends Chain
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
