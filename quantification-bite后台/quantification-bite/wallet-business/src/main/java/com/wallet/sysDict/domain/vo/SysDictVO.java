package com.wallet.sysDict.domain.vo;


import com.wallet.sysDict.domain.SysDict;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 参数配置对象 w_sys_dictVO
 * 
 * @author wallet
 * @date 2022-01-06
 */
public class SysDictVO  extends SysDict
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
