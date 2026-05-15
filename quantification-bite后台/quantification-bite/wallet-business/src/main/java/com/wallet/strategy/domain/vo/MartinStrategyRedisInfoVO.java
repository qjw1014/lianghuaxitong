package com.wallet.strategy.domain.vo;


import com.wallet.strategy.domain.MartinStrategySettings;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 策略信息对象 t_martin_strategy_infoVO
 * 
 * @author wallet
 * @date 2022-08-31
 */
@ApiModel("redis存放对象")
public class MartinStrategyRedisInfoVO
{
    /** 策略编号 */
    @ApiModelProperty(value = "策略编号")
    private Long strategyId;


    /** 策略状态 0未开始 1.开启 2.关闭 */
    @ApiModelProperty(value = "策略状态 0未开始 1.开启 2.关闭")
    private Integer strategyStatus;


    /** 用户uuid */
    @ApiModelProperty(value = "用户uuid")
    private String userUuid;


    /** 交易所 */
    @ApiModelProperty(value = "交易所")
    private String platform;


    /** 交易对 */
    @ApiModelProperty(value = "交易对")
    private String symbol;


    /** 子账号id */
    @ApiModelProperty(value = "子账号id")
    private Long apiAccountId;


    /** 币种 */
    @ApiModelProperty(value ="币种")
    private String coin;


    /** 杠杆倍数 */
    @ApiModelProperty(value = "杠杆倍数")
    private Integer leverage;


    /** 多方向 True为启动，False为关闭 */
    @ApiModelProperty(value = "多方向 True为启动，False为关闭")
    private String longOpen;


    /** 多方向次数 "once"为单次，"cycle"为循环 */
    @ApiModelProperty(value = "多方向次数 once为单次，cycle为循环")
    private String longCount;


    /** 空方向 True为启动，False为关闭 */
    @ApiModelProperty(value = "空方向 True为启动，False为关闭")
    private String shortOpen;


    /** 空方向次数 "once"为单次，"cycle"为循环 */
    @ApiModelProperty(value = "空方向次数 once为单次，cycle为循环")
    private String shortCount;


    /** 默认备用 True为启动，False为关闭 */
    @ApiModelProperty(value = "默认备用 True为启动，False为关闭")
    private String common;


    /** 一键平多比例 */
    @ApiModelProperty(value = "一键平多比例")
    private BigDecimal sellLongRate;


    /** 一键平空比例 */
    @ApiModelProperty(value = "一键平空比例")
    private BigDecimal buyShortRate;


    /** 一键开多比例 */

    @ApiModelProperty(value = "一键开多比例")
    private BigDecimal buyLongRate;


    /** 一键开空比例 */
    @ApiModelProperty(value = "一键开空比例")
    private BigDecimal sellShortRate;

    @ApiModelProperty(value = "修改时间")
    private Long updateTime;

    /** 策略类型 1.策略一 */
    @ApiModelProperty(value = "策略类型 1.策略一")
    private Long strategyType;

    /**
     * 补仓配置
     */
    private List<MartinStrategySettingsPythonVO> addSet;

    public List<MartinStrategySettingsPythonVO> getAddSet() {
        return addSet;
    }

    public void setAddSet(List<MartinStrategySettingsPythonVO> addSet) {
        this.addSet = addSet;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getStrategyStatus() {
        return strategyStatus;
    }

    public void setStrategyStatus(Integer strategyStatus) {
        this.strategyStatus = strategyStatus;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getApiAccountId() {
        return apiAccountId;
    }

    public void setApiAccountId(Long apiAccountId) {
        this.apiAccountId = apiAccountId;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public Integer getLeverage() {
        return leverage;
    }

    public void setLeverage(Integer leverage) {
        this.leverage = leverage;
    }

    public String getLongOpen() {
        return longOpen;
    }

    public void setLongOpen(String longOpen) {
        this.longOpen = longOpen;
    }

    public String getLongCount() {
        return longCount;
    }

    public void setLongCount(String longCount) {
        this.longCount = longCount;
    }

    public String getShortOpen() {
        return shortOpen;
    }

    public void setShortOpen(String shortOpen) {
        this.shortOpen = shortOpen;
    }

    public String getShortCount() {
        return shortCount;
    }

    public void setShortCount(String shortCount) {
        this.shortCount = shortCount;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public BigDecimal getSellLongRate() {
        return sellLongRate;
    }

    public void setSellLongRate(BigDecimal sellLongRate) {
        this.sellLongRate = sellLongRate;
    }

    public BigDecimal getBuyShortRate() {
        return buyShortRate;
    }

    public void setBuyShortRate(BigDecimal buyShortRate) {
        this.buyShortRate = buyShortRate;
    }

    public BigDecimal getBuyLongRate() {
        return buyLongRate;
    }

    public void setBuyLongRate(BigDecimal buyLongRate) {
        this.buyLongRate = buyLongRate;
    }

    public BigDecimal getSellShortRate() {
        return sellShortRate;
    }

    public void setSellShortRate(BigDecimal sellShortRate) {
        this.sellShortRate = sellShortRate;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(Long strategyType) {
        this.strategyType = strategyType;
    }
}
