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
 * 收益曲线对象 b_revenue_curve
 * 
 * @author wallet
 * @date 2023-03-11
 */
@Table(name = "b_revenue_curve")
public class RevenueCurve
{
    private static final long serialVersionUID = 1L;


    /** 流水编号 */

	@Id
    @Excel(name = "流水编号")

    private Long id;


    /** 子账号 */

    @Excel(name = "子账号")

    private Long apiAccountId;


    /** 日期年月日 */

    @Excel(name = "日期年月日")

    private String dateTime;


    /** 资产净值 */

    @Excel(name = "资产净值")

    private BigDecimal netValue;


    /** 添加时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date createTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setApiAccountId(Long apiAccountId) 
    {
        this.apiAccountId = apiAccountId;
    }

    public Long getApiAccountId() 
    {
        return apiAccountId;
    }
    public void setDataTime(String dateTime)
    {
        this.dateTime = dateTime;
    }

    public String getDataTime() 
    {
        return dateTime;
    }
    public void setNetValue(BigDecimal netValue) 
    {
        this.netValue = netValue;
    }

    public BigDecimal getNetValue() 
    {
        return netValue;
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
            .append("apiAccountId", getApiAccountId())
            .append("dateTime", getDataTime())
            .append("netValue", getNetValue())
            .append("createTime", getCreateTime())
            .toString();
    }
}
