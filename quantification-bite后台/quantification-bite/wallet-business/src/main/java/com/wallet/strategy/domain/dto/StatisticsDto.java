package com.wallet.strategy.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 用户平台私钥信息对象
 * 
 * @author wallet
 * @date 2022-09-01
 */
@ApiModel("用户平台私钥信息对象")
public class StatisticsDto
{

    @ApiModelProperty(value ="总收益")
    private BigDecimal totalIncome;

    @ApiModelProperty(value = "当前收益")
    private BigDecimal currentIncome;

    @ApiModelProperty(value ="要分润收益")
    private BigDecimal shareIncome;

    @ApiModelProperty(value = "当前资金")
    private BigDecimal currentUsdt;

    public BigDecimal getCurrentIncome() {
        return currentIncome;
    }

    public void setCurrentIncome(BigDecimal currentIncome) {
        this.currentIncome = currentIncome;
    }

    public BigDecimal getCurrentUsdt() {
        return currentUsdt;
    }

    public void setCurrentUsdt(BigDecimal currentUsdt) {
        this.currentUsdt = currentUsdt;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getShareIncome() {
        return shareIncome;
    }

    public void setShareIncome(BigDecimal shareIncome) {
        this.shareIncome = shareIncome;
    }
}
