package com.wallet.strategy.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wallet.common.annotation.Excel;
import javax.persistence.*;
import com.wallet.common.core.domain.BaseEntity;

/**
 * 策略比例设置对象 t_martin_strategy_settings
 * 
 * @author wallet
 * @date 2022-08-31
 */
@Table(name = "t_martin_strategy_settings")
public class MartinStrategySettings
{
    private static final long serialVersionUID = 1L;


    /** 比例设置 */

	@Id
    @Excel(name = "比例设置")

    private Long id;


    /** 策略编号 */

    @Excel(name = "策略编号")

    private Long strategyId;


    /** 补仓倍数 */

    @Excel(name = "补仓倍数")

    private BigDecimal addAmountMultiple;


    /** 补仓比例 */

    @Excel(name = "补仓比例")

    private BigDecimal addPriceRate;


    /** 多单止盈比例 */

    @Excel(name = "多单止盈比例")

    private BigDecimal longStopRate;


    /** 空单止盈比例 */

    @Excel(name = "空单止盈比例")

    private BigDecimal shortStopRate;


    /** 止损比例 */

    @Excel(name = "止损比例")

    private BigDecimal lossRate;


    /** 添加时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")

    private Date createTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStrategyId(Long strategyId) 
    {
        this.strategyId = strategyId;
    }

    public Long getStrategyId() 
    {
        return strategyId;
    }
    public void setAddAmountMultiple(BigDecimal addAmountMultiple) 
    {
        this.addAmountMultiple = addAmountMultiple;
    }

    public BigDecimal getAddAmountMultiple() 
    {
        return addAmountMultiple;
    }
    public void setAddPriceRate(BigDecimal addPriceRate) 
    {
        this.addPriceRate = addPriceRate;
    }

    public BigDecimal getAddPriceRate() 
    {
        return addPriceRate;
    }
    public void setLongStopRate(BigDecimal longStopRate) 
    {
        this.longStopRate = longStopRate;
    }

    public BigDecimal getLongStopRate() 
    {
        return longStopRate;
    }
    public void setShortStopRate(BigDecimal shortStopRate) 
    {
        this.shortStopRate = shortStopRate;
    }

    public BigDecimal getShortStopRate() 
    {
        return shortStopRate;
    }
    public void setLossRate(BigDecimal lossRate) 
    {
        this.lossRate = lossRate;
    }

    public BigDecimal getLossRate() 
    {
        return lossRate;
    }
    public void setCreateTime(Date createTime) 
    {
        this.createTime = createTime;
    }

    public Date getCreateTime() 
    {
        return createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("strategyId", getStrategyId())
            .append("addAmountMultiple", getAddAmountMultiple())
            .append("addPriceRate", getAddPriceRate())
            .append("longStopRate", getLongStopRate())
            .append("shortStopRate", getShortStopRate())
            .append("lossRate", getLossRate())
            .append("createTime", getCreateTime())
            .toString();
    }
}
