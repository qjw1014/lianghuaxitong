package com.wallet.strategy.domain.vo;

import com.wallet.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 添加策略字段关系对象 b_strategy_field
 * 
 * @author wallet
 * @date 2023-03-14
 */
public class StrategyFieldAddVo
{

    private Long[] fields;

    /** 策略类型id */
    private Long strategyTypeId;

    public Long[] getFields() {
        return fields;
    }

    public void setFields(Long[] fields) {
        this.fields = fields;
    }

    public Long getStrategyTypeId() {
        return strategyTypeId;
    }

    public void setStrategyTypeId(Long strategyTypeId) {
        this.strategyTypeId = strategyTypeId;
    }
}
