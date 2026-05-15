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
 * api对应策略信息对象 t_martin_strategy_api
 * 
 * @author wallet
 * @date 2022-09-05
 */
@Table(name = "t_martin_strategy_api")
public class MartinStrategyApi
{
    private static final long serialVersionUID = 1L;


    /** 流水编号 */

	@Id
    @Excel(name = "流水编号")

    private Long id;


    /** 1.开启 2.停止 */

    @Excel(name = "1.开启 2.停止")

    private Integer strategyStatus;


    /** 策略类型 */

    @Excel(name = "策略类型")

    private Long strategyType;


    /** API对应的id */

    @Excel(name = "API对应的id")

    private Long accountId;

    /** 策略编号 */
    @Excel(name = "策略编号")
    private Long strategyId;


    /** 首单金额比例 */

    @Excel(name = "首单金额比例")

    private BigDecimal baseRate;


    /** 添加时间 */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")

    private Date crateTime;

    /** 策略编号 */
    @Excel(name = "策略编号")
    private String isDelete;

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStrategyStatus(Integer strategyStatus)
    {
        this.strategyStatus = strategyStatus;
    }

    public Integer getStrategyStatus()
    {
        return strategyStatus;
    }
    public void setStrategyType(Long strategyType)
    {
        this.strategyType = strategyType;
    }

    public Long getStrategyType()
    {
        return strategyType;
    }
    public void setAccountId(Long accountId)
    {
        this.accountId = accountId;
    }

    public Long getAccountId() 
    {
        return accountId;
    }
    public void setBaseRate(BigDecimal baseRate) 
    {
        this.baseRate = baseRate;
    }

    public BigDecimal getBaseRate() 
    {
        return baseRate;
    }
    public void setCrateTime(Date crateTime) 
    {
        this.crateTime = crateTime;
    }

    public Date getCrateTime() 
    {
        return crateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("strategyStatus", getStrategyStatus())
            .append("strategyType", getStrategyType())
            .append("accountId", getAccountId())
            .append("baseRate", getBaseRate())
            .append("crateTime", getCrateTime())
            .toString();
    }
}
