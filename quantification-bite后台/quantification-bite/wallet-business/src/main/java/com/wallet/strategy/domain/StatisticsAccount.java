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
 * 统计账户信息对象 b_statistics_account
 * 
 * @author wallet
 * @date 2023-03-11
 */
@Table(name = "b_statistics_account")
public class StatisticsAccount
{
    private static final long serialVersionUID = 1L;


    /** 子账号 */

	@Id
    @Excel(name = "子账号")

    private Long apiAccountId;


    /** 本金 */

    @Excel(name = "本金")

    private BigDecimal initUsdt;


    /** 充提（负值为提币） */

    @Excel(name = "充提", readConverterExp = "负=值为提币")

    private BigDecimal charge;


    /** 最新净值 */

    @Excel(name = "最新净值")

    private BigDecimal currentUsdt;


    /** 7天收益 */

    @Excel(name = "7天收益")

    private BigDecimal days7Profit;


    /** 7日年化收益率 */

    @Excel(name = "7日年化收益率")

    private BigDecimal days7ProfitFloat;


    /** 30天收益 */

    @Excel(name = "30天收益")

    private BigDecimal days30Profit;


    /** 30日年化收益率 */

    @Excel(name = "30日年化收益率")

    private BigDecimal days30ProfitFloat;


    /** 累计收益 */

    @Excel(name = "累计收益")

    private BigDecimal totalProfit;


    /** 累计年化收益率 */

    @Excel(name = "累计年化收益率")

    private BigDecimal totalProfitFloat;


    /** 修改时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date updateTime;

    public void setApiAccountId(Long apiAccountId) 
    {
        this.apiAccountId = apiAccountId;
    }

    public Long getApiAccountId() 
    {
        return apiAccountId;
    }
    public void setInitUsdt(BigDecimal initUsdt) 
    {
        this.initUsdt = initUsdt;
    }

    public BigDecimal getInitUsdt() 
    {
        return initUsdt;
    }
    public void setCharge(BigDecimal charge) 
    {
        this.charge = charge;
    }

    public BigDecimal getCharge() 
    {
        return charge;
    }
    public void setCurrentUsdt(BigDecimal currentUsdt) 
    {
        this.currentUsdt = currentUsdt;
    }

    public BigDecimal getCurrentUsdt() 
    {
        return currentUsdt;
    }
    public void setDays7Profit(BigDecimal days7Profit) 
    {
        this.days7Profit = days7Profit;
    }

    public BigDecimal getDays7Profit() 
    {
        return days7Profit;
    }
    public void setDays7ProfitFloat(BigDecimal days7ProfitFloat) 
    {
        this.days7ProfitFloat = days7ProfitFloat;
    }

    public BigDecimal getDays7ProfitFloat() 
    {
        return days7ProfitFloat;
    }
    public void setDays30Profit(BigDecimal days30Profit) 
    {
        this.days30Profit = days30Profit;
    }

    public BigDecimal getDays30Profit() 
    {
        return days30Profit;
    }
    public void setDays30ProfitFloat(BigDecimal days30ProfitFloat) 
    {
        this.days30ProfitFloat = days30ProfitFloat;
    }

    public BigDecimal getDays30ProfitFloat() 
    {
        return days30ProfitFloat;
    }
    public void setTotalProfit(BigDecimal totalProfit) 
    {
        this.totalProfit = totalProfit;
    }

    public BigDecimal getTotalProfit() 
    {
        return totalProfit;
    }
    public void setTotalProfitFloat(BigDecimal totalProfitFloat) 
    {
        this.totalProfitFloat = totalProfitFloat;
    }

    public BigDecimal getTotalProfitFloat() 
    {
        return totalProfitFloat;
    }
    public void setUpdateTime(Date updateTime) 
    {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() 
    {
        return updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("apiAccountId", getApiAccountId())
            .append("initUsdt", getInitUsdt())
            .append("charge", getCharge())
            .append("currentUsdt", getCurrentUsdt())
            .append("days7Profit", getDays7Profit())
            .append("days7ProfitFloat", getDays7ProfitFloat())
            .append("days30Profit", getDays30Profit())
            .append("days30ProfitFloat", getDays30ProfitFloat())
            .append("totalProfit", getTotalProfit())
            .append("totalProfitFloat", getTotalProfitFloat())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
