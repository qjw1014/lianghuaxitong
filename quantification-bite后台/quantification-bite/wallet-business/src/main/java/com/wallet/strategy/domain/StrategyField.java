package com.wallet.strategy.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wallet.common.annotation.Excel;
import javax.persistence.*;
import com.wallet.common.core.domain.BaseEntity;

/**
 * 策略字段关系对象 b_strategy_field
 * 
 * @author wallet
 * @date 2023-03-14
 */
@Table(name = "b_strategy_field")
public class StrategyField
{
    private static final long serialVersionUID = 1L;


    /** 流水编号 */

	@Id
    @Excel(name = "流水编号")

    private Long id;


    /** 字段id */

    @Excel(name = "字段id")

    private Long field;


    /** 策略类型id */

    @Excel(name = "策略类型id")

    private Long strategyTypeId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setField(Long field) 
    {
        this.field = field;
    }

    public Long getField() 
    {
        return field;
    }
    public void setStrategyTypeId(Long strategyTypeId) 
    {
        this.strategyTypeId = strategyTypeId;
    }

    public Long getStrategyTypeId() 
    {
        return strategyTypeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("field", getField())
            .append("strategyTypeId", getStrategyTypeId())
            .toString();
    }
}
