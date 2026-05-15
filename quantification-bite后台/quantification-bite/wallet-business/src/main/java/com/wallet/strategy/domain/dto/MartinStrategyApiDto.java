package com.wallet.strategy.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wallet.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author wallet
 * @date 2022-09-05
 */
@ApiModel("策略控制对象")
public class MartinStrategyApiDto
{


    /** 流水编号 */
    @ApiModelProperty(value = "流水编号")
    private Long id;


    /** 1.开启 2.停止 */

    @ApiModelProperty(value = "1.开启 2.停止")
    private Integer strategyStatus;


    /** 策略类型 */

    @ApiModelProperty(value = "策略类型")
    private Integer strategyType;


    @ApiModelProperty(value = "策略名称")
    private String strategyTypeName;


    /** API对应的id */
    @ApiModelProperty(value = "API对应的id")
    private Long accountId;

    /** 策略编号 */
    @ApiModelProperty(value = "策略编号")
    private Long strategyId;


    /** 首单金额比例 */
    @ApiModelProperty(value = "首单金额比例")
    private BigDecimal baseRate;


    /** 添加时间 */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value= "添加时间")
    private Date crateTime;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String isDelete;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStrategyStatus() {
        return strategyStatus;
    }

    public void setStrategyStatus(Integer strategyStatus) {
        this.strategyStatus = strategyStatus;
    }

    public Integer getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(Integer strategyType) {
        this.strategyType = strategyType;
    }

    public String getStrategyTypeName() {
        return strategyTypeName;
    }

    public void setStrategyTypeName(String strategyTypeName) {
        this.strategyTypeName = strategyTypeName;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public BigDecimal getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(BigDecimal baseRate) {
        this.baseRate = baseRate;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
}
