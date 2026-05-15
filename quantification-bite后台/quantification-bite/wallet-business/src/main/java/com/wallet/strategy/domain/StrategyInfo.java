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
 * 策略信息对象 b_strategy_info
 * 
 * @author wallet
 * @date 2023-03-14
 */
@Table(name = "b_strategy_info")
public class StrategyInfo
{
    private static final long serialVersionUID = 1L;


    /** 策略编号 */

	@Id
    @Excel(name = "策略编号")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long strategyId;


    /** 状态0等待开启，1开启，2停止，3已完成 */

    @Excel(name = "状态0等待开启，1开启，2停止，3已完成")

    private Integer strategyStatus;


    @Excel(name = "策略类型id")
    private Long strategyType;


    /** 用户编号 */

    @Excel(name = "用户编号")

    private Long userUuid;


    /** 交易所 */

    @Excel(name = "交易所")

    private String exchange;


    /** 子账号编号 */

    @Excel(name = "子账号编号")

    private Long apiAccountId;


    /** 交易对 */

    @Excel(name = "交易对")

    private String symbol;

    /** 是否删除 */

    @Excel(name = "是否删除 Y N")

    private String isDelete;


    /** 添加时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date createTime;


    /** 修改时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date updateTime;


    /** 执行币种 */

    @Excel(name = "执行币种")

    private String coin;


    /** 买方（现货spot，杠杆margin，coinswap币本位合约，  usdtswap U本位 合约） */

    @Excel(name = "买方", readConverterExp = "现=货spot，杠杆margin，coinswap币本位合约，,u=sdtswap,U=本位,合=约")

    private String buyer;


    /** 卖方 */

    @Excel(name = "卖方")

    private String seller;


    /** 每张对应的面值 */

    @Excel(name = "每张对应的面值")
    @Column(name = "`value`")
    private BigDecimal value;


    /** 每笔下单量 */

    @Excel(name = "每笔下单量")

    private Long everyAmount;


    /** 总下单量（张） */

    @Excel(name = "总下单量", readConverterExp = "张=")

    private Long totalAmount;


    /** 下单次数 */

    @Excel(name = "下单次数")

    private Long tradeCount;


    /** 是否一边maker一边taker（1是，0否） */

    @Excel(name = "是否一边maker一边taker", readConverterExp = "1=是，0否")

    private String isMaker;


    /** 杠杆倍数 */

    @Excel(name = "杠杆倍数")

    private Long leverage;


    /** 投入的本金 */

    @Excel(name = "投入的本金")

    private BigDecimal initUsdt;


    /** 每一档的开仓金额（两个币各开仓: every_volume * leverage / price） */

    @Excel(name = "每一档的开仓金额", readConverterExp = "两=个币各开仓:,e=very_volume,*=,l=everage,/=,p=rice")

    private BigDecimal everyVolume;


    /** 币种1 */

    @Excel(name = "币种1")
    @Column(name = "`symbol_1`")
    private String symbol1;


    /** 币种2 */

    @Excel(name = "币种2")
    @Column(name = "`symbol_2`")
    private String symbol2;


    /** 币1的下单数量精度 */

    @Excel(name = "币1的下单数量精度")
    @Column(name = "`amount_decimal_1`")
    private Long amountDecimal1;


    /** 币2的下单数量精度 */

    @Excel(name = "币2的下单数量精度")
    @Column(name = "`amount_decimal_2`")
    private Long amountDecimal2;


    /** 币1的下单价格精度 */

    @Excel(name = "币1的下单价格精度")
    @Column(name = "`price_decimal_1`")
    private Long priceDecimal1;


    /** 币2的下单价格精度 */

    @Excel(name = "币2的下单价格精度")
    @Column(name = "`price_decimal_2`")
    private Long priceDecimal2;


    /** 是否一键全平 no否 yes是 */

    @Excel(name = "是否一键全平 no否 yes是")

    private String isCloseAll;


    /** K线粒度，如15m */

    @Excel(name = "K线粒度，如15m")

    private String klinePeriod;


    /** 初始均线， coin1/coin2的价格比例在初始均线之上则coin1做空，coin2做多，在下则反之 */

    @Excel(name = "初始均线， coin1/coin2的价格比例在初始均线之上则coin1做空，coin2做多，在下则反之")

    private BigDecimal initLine;


    /** 网格间距 */

    @Excel(name = "网格间距")

    private BigDecimal gridGap;


    /** 价格上限 */

    @Excel(name = "价格上限")

    private BigDecimal lowLimitPrice;


    /** 价格下限 */

    @Excel(name = "价格下限")

    private BigDecimal highLimitPrice;


    /** 其他参数（拓展用） */

    @Excel(name = "其他参数", readConverterExp = "拓=展用")

    private String detailInfo;


    /**  开平仓，open开仓，close平仓 */

    @Excel(name = " 开平仓，open开仓，close平仓")
    @Column(name = "`action`")
    private String action;

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(Long strategyType) {
        this.strategyType = strategyType;
    }

    public void setStrategyId(Long strategyId)
    {
        this.strategyId = strategyId;
    }

    public Long getStrategyId() 
    {
        return strategyId;
    }
    public void setStrategyStatus(Integer strategyStatus)
    {
        this.strategyStatus = strategyStatus;
    }

    public Integer getStrategyStatus()
    {
        return strategyStatus;
    }
    public void setUserUuid(Long userUuid) 
    {
        this.userUuid = userUuid;
    }

    public Long getUserUuid() 
    {
        return userUuid;
    }
    public void setExchange(String exchange) 
    {
        this.exchange = exchange;
    }

    public String getExchange() 
    {
        return exchange;
    }
    public void setApiAccountId(Long apiAccountId) 
    {
        this.apiAccountId = apiAccountId;
    }

    public Long getApiAccountId() 
    {
        return apiAccountId;
    }
    public void setSymbol(String symbol) 
    {
        this.symbol = symbol;
    }

    public String getSymbol() 
    {
        return symbol;
    }
    public void setCreateTime(Date createTime) 
    {
        this.createTime = createTime;
    }

    public Date getCreateTime() 
    {
        return createTime;
    }
    public void setUpdateTime(Date updateTime) 
    {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() 
    {
        return updateTime;
    }
    public void setCoin(String coin) 
    {
        this.coin = coin;
    }

    public String getCoin() 
    {
        return coin;
    }
    public void setBuyer(String buyer) 
    {
        this.buyer = buyer;
    }

    public String getBuyer() 
    {
        return buyer;
    }
    public void setSeller(String seller) 
    {
        this.seller = seller;
    }

    public String getSeller() 
    {
        return seller;
    }
    public void setValue(BigDecimal value) 
    {
        this.value = value;
    }

    public BigDecimal getValue() 
    {
        return value;
    }
    public void setEveryAmount(Long everyAmount) 
    {
        this.everyAmount = everyAmount;
    }

    public Long getEveryAmount() 
    {
        return everyAmount;
    }
    public void setTotalAmount(Long totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public Long getTotalAmount() 
    {
        return totalAmount;
    }
    public void setTradeCount(Long tradeCount) 
    {
        this.tradeCount = tradeCount;
    }

    public Long getTradeCount() 
    {
        return tradeCount;
    }
    public void setIsMaker(String isMaker) 
    {
        this.isMaker = isMaker;
    }

    public String getIsMaker() 
    {
        return isMaker;
    }
    public void setLeverage(Long leverage) 
    {
        this.leverage = leverage;
    }

    public Long getLeverage() 
    {
        return leverage;
    }
    public void setInitUsdt(BigDecimal initUsdt) 
    {
        this.initUsdt = initUsdt;
    }

    public BigDecimal getInitUsdt() 
    {
        return initUsdt;
    }
    public void setEveryVolume(BigDecimal everyVolume) 
    {
        this.everyVolume = everyVolume;
    }

    public BigDecimal getEveryVolume() 
    {
        return everyVolume;
    }
    public void setSymbol1(String symbol1) 
    {
        this.symbol1 = symbol1;
    }

    public String getSymbol1() 
    {
        return symbol1;
    }
    public void setSymbol2(String symbol2) 
    {
        this.symbol2 = symbol2;
    }

    public String getSymbol2() 
    {
        return symbol2;
    }
    public void setAmountDecimal1(Long amountDecimal1) 
    {
        this.amountDecimal1 = amountDecimal1;
    }

    public Long getAmountDecimal1() 
    {
        return amountDecimal1;
    }
    public void setAmountDecimal2(Long amountDecimal2) 
    {
        this.amountDecimal2 = amountDecimal2;
    }

    public Long getAmountDecimal2() 
    {
        return amountDecimal2;
    }
    public void setPriceDecimal1(Long priceDecimal1) 
    {
        this.priceDecimal1 = priceDecimal1;
    }

    public Long getPriceDecimal1() 
    {
        return priceDecimal1;
    }
    public void setPriceDecimal2(Long priceDecimal2) 
    {
        this.priceDecimal2 = priceDecimal2;
    }

    public Long getPriceDecimal2() 
    {
        return priceDecimal2;
    }
    public void setIsCloseAll(String isCloseAll) 
    {
        this.isCloseAll = isCloseAll;
    }

    public String getIsCloseAll() 
    {
        return isCloseAll;
    }
    public void setKlinePeriod(String klinePeriod) 
    {
        this.klinePeriod = klinePeriod;
    }

    public String getKlinePeriod() 
    {
        return klinePeriod;
    }
    public void setInitLine(BigDecimal initLine) 
    {
        this.initLine = initLine;
    }

    public BigDecimal getInitLine() 
    {
        return initLine;
    }
    public void setGridGap(BigDecimal gridGap) 
    {
        this.gridGap = gridGap;
    }

    public BigDecimal getGridGap() 
    {
        return gridGap;
    }
    public void setLowLimitPrice(BigDecimal lowLimitPrice) 
    {
        this.lowLimitPrice = lowLimitPrice;
    }

    public BigDecimal getLowLimitPrice() 
    {
        return lowLimitPrice;
    }
    public void setHighLimitPrice(BigDecimal highLimitPrice) 
    {
        this.highLimitPrice = highLimitPrice;
    }

    public BigDecimal getHighLimitPrice() 
    {
        return highLimitPrice;
    }
    public void setDetailInfo(String detailInfo) 
    {
        this.detailInfo = detailInfo;
    }

    public String getDetailInfo() 
    {
        return detailInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("strategyId", getStrategyId())
            .append("strategyStatus", getStrategyStatus())
            .append("userUuid", getUserUuid())
            .append("exchange", getExchange())
            .append("apiAccountId", getApiAccountId())
            .append("symbol", getSymbol())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("coin", getCoin())
            .append("buyer", getBuyer())
            .append("seller", getSeller())
            .append("value", getValue())
            .append("everyAmount", getEveryAmount())
            .append("totalAmount", getTotalAmount())
            .append("tradeCount", getTradeCount())
            .append("isMaker", getIsMaker())
            .append("leverage", getLeverage())
            .append("initUsdt", getInitUsdt())
            .append("everyVolume", getEveryVolume())
            .append("symbol1", getSymbol1())
            .append("symbol2", getSymbol2())
            .append("amountDecimal1", getAmountDecimal1())
            .append("amountDecimal2", getAmountDecimal2())
            .append("priceDecimal1", getPriceDecimal1())
            .append("priceDecimal2", getPriceDecimal2())
            .append("isCloseAll", getIsCloseAll())
            .append("klinePeriod", getKlinePeriod())
            .append("initLine", getInitLine())
            .append("gridGap", getGridGap())
            .append("lowLimitPrice", getLowLimitPrice())
            .append("highLimitPrice", getHighLimitPrice())
            .append("detailInfo", getDetailInfo())
            .toString();
    }
}
