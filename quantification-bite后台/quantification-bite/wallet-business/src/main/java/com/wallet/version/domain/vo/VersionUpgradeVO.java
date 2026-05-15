package com.wallet.version.domain.vo;


import com.wallet.version.domain.VersionUpgrade;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * app版本升级对象 w_version_upgradeVO
 * 
 * @author wallet
 * @date 2021-11-19
 */
public class VersionUpgradeVO  extends VersionUpgrade
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
