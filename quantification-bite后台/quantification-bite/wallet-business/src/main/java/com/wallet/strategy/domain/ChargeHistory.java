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
 * 充值记录	对象 b_charge_history
 * 
 * @author wallet
 * @date 2023-03-11
 */
@Table(name = "b_charge_history")
public class ChargeHistory
{
    private static final long serialVersionUID = 1L;


    /** 流水编号 */

	@Id
    @Excel(name = "流水编号")

    private Long id;


    /** 子账号 */

    @Excel(name = "子账号")

    private Long apiAccountId;


    /** 操作时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date createTime;


    /** 操作类型 in：充值，out：提币 */

    @Excel(name = "操作类型 in：充值，out：提币")

    private String actionType;


    /** 数量 */

    @Excel(name = "数量")

    private BigDecimal amount;


    /** 币种 */

    @Excel(name = "币种")

    private String coin;

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
    public void setCreateTime(Date createTime) 
    {
        this.createTime = createTime;
    }

    public Date getCreateTime() 
    {
        return createTime;
    }
    public void setActionType(String actionType) 
    {
        this.actionType = actionType;
    }

    public String getActionType() 
    {
        return actionType;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setCoin(String coin) 
    {
        this.coin = coin;
    }

    public String getCoin() 
    {
        return coin;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("apiAccountId", getApiAccountId())
            .append("createTime", getCreateTime())
            .append("actionType", getActionType())
            .append("amount", getAmount())
            .append("coin", getCoin())
            .toString();
    }
}
