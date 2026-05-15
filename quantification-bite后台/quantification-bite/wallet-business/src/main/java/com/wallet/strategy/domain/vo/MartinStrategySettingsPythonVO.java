package com.wallet.strategy.domain.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

/**
 * 策略比例设置对象 t_martin_strategy_settings
 * 
 * @author wallet
 * @date 2022-08-31
 */
@ApiModel("策略比例设置对象")
public class MartinStrategySettingsPythonVO
{

    /** 策略编号 */
    @ApiModelProperty(value = "策略编号")
    private Long strategyId;


    /** 补仓倍数 */
    @ApiModelProperty(value = "补仓倍数")
    private BigDecimal add_amount_multiple;


    /** 补仓比例 */
    @ApiModelProperty(value = "补仓比例")
    private BigDecimal add_price_rate;


    /** 多单止盈比例 */
    @ApiModelProperty(value = "多单止盈比例")
    private BigDecimal long_stop_rate;


    /** 空单止盈比例 */
    @ApiModelProperty(value = "空单止盈比例")
    private BigDecimal short_stop_rate;


    /** 止损比例 */
    @ApiModelProperty(value = "止损比例")
    private BigDecimal loss_rate;

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public BigDecimal getAdd_amount_multiple() {
        return add_amount_multiple;
    }

    public void setAdd_amount_multiple(BigDecimal add_amount_multiple) {
        this.add_amount_multiple = add_amount_multiple;
    }

    public BigDecimal getAdd_price_rate() {
        return add_price_rate;
    }

    public void setAdd_price_rate(BigDecimal add_price_rate) {
        this.add_price_rate = add_price_rate;
    }

    public BigDecimal getLong_stop_rate() {
        return long_stop_rate;
    }

    public void setLong_stop_rate(BigDecimal long_stop_rate) {
        this.long_stop_rate = long_stop_rate;
    }

    public BigDecimal getShort_stop_rate() {
        return short_stop_rate;
    }

    public void setShort_stop_rate(BigDecimal short_stop_rate) {
        this.short_stop_rate = short_stop_rate;
    }

    public BigDecimal getLoss_rate() {
        return loss_rate;
    }

    public void setLoss_rate(BigDecimal loss_rate) {
        this.loss_rate = loss_rate;
    }
}
