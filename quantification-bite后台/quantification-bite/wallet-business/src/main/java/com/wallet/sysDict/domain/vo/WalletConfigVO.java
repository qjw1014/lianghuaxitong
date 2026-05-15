package com.wallet.sysDict.domain.vo;


import com.wallet.sysDict.domain.WalletConfig;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 钱包配置信息对象 w_wallet_configVO
 * 
 * @author wallet
 * @date 2022-03-02
 */
public class WalletConfigVO  extends WalletConfig
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
