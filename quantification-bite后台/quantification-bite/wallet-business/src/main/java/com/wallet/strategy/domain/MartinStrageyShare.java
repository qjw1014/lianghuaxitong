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
 * 分润统计对象 t_martin_stragey_share
 * 
 * @author wallet
 * @date 2022-10-31
 */
@Table(name = "t_martin_stragey_share")
public class MartinStrageyShare
{
    private static final long serialVersionUID = 1L;


    /** 流水编号 */

	@Id
    @Excel(name = "流水编号")

    private Long id;


    /** 分润账号 */

    @Excel(name = "分润账号")

    private Long accountId;


    /** 策略收益 */

    @Excel(name = "策略收益")

    private BigDecimal currentIncome;


    /** 分润收益 */

    @Excel(name = "分润收益")

    private BigDecimal shareIncome;


    /** 分润比例 */

    @Excel(name = "分润比例")

    private BigDecimal shareRate;


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
    public void setAccountId(Long accountId) 
    {
        this.accountId = accountId;
    }

    public Long getAccountId() 
    {
        return accountId;
    }
    public void setCurrentIncome(BigDecimal currentIncome) 
    {
        this.currentIncome = currentIncome;
    }

    public BigDecimal getCurrentIncome() 
    {
        return currentIncome;
    }
    public void setShareIncome(BigDecimal shareIncome) 
    {
        this.shareIncome = shareIncome;
    }

    public BigDecimal getShareIncome() 
    {
        return shareIncome;
    }
    public void setShareRate(BigDecimal shareRate) 
    {
        this.shareRate = shareRate;
    }

    public BigDecimal getShareRate() 
    {
        return shareRate;
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
            .append("accountId", getAccountId())
            .append("currentIncome", getCurrentIncome())
            .append("shareIncome", getShareIncome())
            .append("shareRate", getShareRate())
            .append("createTime", getCreateTime())
            .toString();
    }
}
