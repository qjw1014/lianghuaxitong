package com.wallet.strategy.domain.vo;


import com.wallet.strategy.domain.StatisticsAccount;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 统计账户信息对象 b_statistics_accountVO
 * 
 * @author wallet
 * @date 2023-03-11
 */
public class StatisticsAccountVO  extends StatisticsAccount
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
