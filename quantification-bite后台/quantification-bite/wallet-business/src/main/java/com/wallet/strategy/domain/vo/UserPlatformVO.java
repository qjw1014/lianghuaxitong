package com.wallet.strategy.domain.vo;


import com.wallet.strategy.domain.UserPlatform;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 用户平台私钥信息对象 t_user_platformVO
 * 
 * @author wallet
 * @date 2022-09-01
 */
public class UserPlatformVO  extends UserPlatform
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
