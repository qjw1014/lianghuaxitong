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
 * 策略仓位对象 b_stragey_position_info
 * 
 * @author wallet
 * @date 2023-03-11
 */
@Table(name = "b_stragey_position_info")
public class StrageyPositionInfo
{
    private static final long serialVersionUID = 1L;


    /** 流水编号 */

	@Id
    @Excel(name = "流水编号")

    private Long id;


    /** 子账号 */

    @Excel(name = "子账号")

    private Long apiAccountId;


    /** 交易所 */

    @Excel(name = "交易所")

    private String exchange;


    /** 交易对 */

    @Excel(name = "交易对")

    private String symbol;


    /** 方向 buy sell */

    @Excel(name = "方向 buy sell")

    private String positionSide;


    /** 数量 */

    @Excel(name = "数量")

    private BigDecimal quantity;


    /** 开始价格 */

    @Excel(name = "开始价格")

    private BigDecimal openPrice;


    /** 当前价格 */

    @Excel(name = "当前价格")

    private BigDecimal currenPrice;


    /** 清算价格 */

    @Excel(name = "清算价格")

    private BigDecimal liquidationPrice;


    /** adl指标 */

    @Excel(name = "adl指标")

    private Long adl;


    /** 等级 */

    @Excel(name = "等级")

    private Long leverage;


    /** 期货价值增损 */

    @Excel(name = "期货价值增损")

    private BigDecimal positionValue;


    /** 添加时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date cretaeTime;

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
    public void setExchange(String exchange) 
    {
        this.exchange = exchange;
    }

    public String getExchange() 
    {
        return exchange;
    }
    public void setSymbol(String symbol) 
    {
        this.symbol = symbol;
    }

    public String getSymbol() 
    {
        return symbol;
    }
    public void setPositionSide(String positionSide) 
    {
        this.positionSide = positionSide;
    }

    public String getPositionSide() 
    {
        return positionSide;
    }
    public void setQuantity(BigDecimal quantity) 
    {
        this.quantity = quantity;
    }

    public BigDecimal getQuantity() 
    {
        return quantity;
    }
    public void setOpenPrice(BigDecimal openPrice) 
    {
        this.openPrice = openPrice;
    }

    public BigDecimal getOpenPrice() 
    {
        return openPrice;
    }
    public void setCurrenPrice(BigDecimal currenPrice) 
    {
        this.currenPrice = currenPrice;
    }

    public BigDecimal getCurrenPrice() 
    {
        return currenPrice;
    }
    public void setLiquidationPrice(BigDecimal liquidationPrice) 
    {
        this.liquidationPrice = liquidationPrice;
    }

    public BigDecimal getLiquidationPrice() 
    {
        return liquidationPrice;
    }
    public void setAdl(Long adl) 
    {
        this.adl = adl;
    }

    public Long getAdl() 
    {
        return adl;
    }
    public void setLeverage(Long leverage) 
    {
        this.leverage = leverage;
    }

    public Long getLeverage() 
    {
        return leverage;
    }
    public void setPositionValue(BigDecimal positionValue) 
    {
        this.positionValue = positionValue;
    }

    public BigDecimal getPositionValue() 
    {
        return positionValue;
    }
    public void setCretaeTime(Date cretaeTime) 
    {
        this.cretaeTime = cretaeTime;
    }

    public Date getCretaeTime() 
    {
        return cretaeTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("apiAccountId", getApiAccountId())
            .append("exchange", getExchange())
            .append("symbol", getSymbol())
            .append("positionSide", getPositionSide())
            .append("quantity", getQuantity())
            .append("openPrice", getOpenPrice())
            .append("currenPrice", getCurrenPrice())
            .append("liquidationPrice", getLiquidationPrice())
            .append("adl", getAdl())
            .append("leverage", getLeverage())
            .append("positionValue", getPositionValue())
            .append("cretaeTime", getCretaeTime())
            .toString();
    }
}
