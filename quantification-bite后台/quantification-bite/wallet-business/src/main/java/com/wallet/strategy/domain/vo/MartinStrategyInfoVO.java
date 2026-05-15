package com.wallet.strategy.domain.vo;


import com.wallet.strategy.domain.MartinStrategyInfo;
import com.alibaba.fastjson.annotation.JSONField;
import com.wallet.strategy.domain.MartinStrategySettings;

import java.util.List;

/**
 * 策略信息对象 t_martin_strategy_infoVO
 * 
 * @author wallet
 * @date 2022-08-31
 */
public class MartinStrategyInfoVO  extends MartinStrategyInfo
{
    /** 开始时间 */
    @JSONField(serialize = false)
    // @JsonIgnore
    private String beginTime;
    
    /** 结束时间 */
    @JSONField(serialize = false)
    // @JsonIgnore
    private String endTime;

    /**
     * 补仓设置数据
     */
    private List<MartinStrategySettings> rowList;

    /**
     * 临时币种
     */
    private String coinFixed;

    public String getCoinFixed() {
        return coinFixed;
    }

    public void setCoinFixed(String coinFixed) {
        this.coinFixed = coinFixed;
    }

    public List<MartinStrategySettings> getRowList() {
        return rowList;
    }

    public void setRowList(List<MartinStrategySettings> rowList) {
        this.rowList = rowList;
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
