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
 * 收益明细	对象 b_revenue_detail
 * 
 * @author wallet
 * @date 2023-03-11
 */
@Table(name = "b_revenue_detail")
public class RevenueDetail
{
    private static final long serialVersionUID = 1L;


    /** 订单流水 */

	@Id
    @Excel(name = "订单流水")

    private Long id;


    /** 子账号 */

    @Excel(name = "子账号")

    private Long apiAccountId;


    /** 日期年月日 */

    @Excel(name = "日期年月日")

    private String dateTime;


    /** 收益 */

    @Excel(name = "收益")

    private BigDecimal profit;


    /** 收益币种 */

    @Excel(name = "收益币种")

    private String profitCoin;


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
    public void setProfit(BigDecimal profit) 
    {
        this.profit = profit;
    }

    public BigDecimal getProfit() 
    {
        return profit;
    }
    public void setProfitCoin(String profitCoin) 
    {
        this.profitCoin = profitCoin;
    }

    public String getProfitCoin() 
    {
        return profitCoin;
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
            .append("profit", getProfit())
            .append("profitCoin", getProfitCoin())
            .append("createTime", getCreateTime())
            .toString();
    }
}
