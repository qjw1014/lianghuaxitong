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
 * 策略订单信息对象 t_martin_strategy_order
 * 
 * @author wallet
 * @date 2022-10-28
 */
@Table(name = "t_martin_strategy_order")
public class MartinStrategyOrder
{
    private static final long serialVersionUID = 1L;


    /** 流水编号 */

	@Id
    @Excel(name = "流水编号")

    private Long serialId;


    /** 交易对 */

    @Excel(name = "交易对")

    private String symbol;


    /** 平台交易id */

    @Excel(name = "平台交易id")

    private Long id;


    /** 订单编号 */

    @Excel(name = "订单编号")

    private String orderId;


    /** 买卖方向 BUY SELL */

    @Excel(name = "买卖方向 BUY SELL")

    private String side;


    /** 成交价 */

    @Excel(name = "成交价")

    private BigDecimal price;


    /** 成交量 */

    @Excel(name = "成交量")

    private BigDecimal qty;


    /** 实现盈亏 */

    @Excel(name = "实现盈亏")

    private BigDecimal realizedPnl;


    /** 保证金币种 */

    @Excel(name = "保证金币种")

    private String marginAsset;


    /** 成交额 */

    @Excel(name = "成交额")

    private BigDecimal quoteQty;


    /** 手续费 */

    @Excel(name = "手续费")

    private BigDecimal commission;


    /** 手续费计价币 */

    @Excel(name = "手续费计价币")

    private String commissionAsset;


    /** 交易时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date orderTime;


    /** 持仓方向 */

    @Excel(name = "持仓方向")

    private String positionSide;


    /** 是否是买方 */

    @Excel(name = "是否是买方")

    private Integer buyer;


    /** 是否是挂单方 */

    @Excel(name = "是否是挂单方")

    private Integer maker;


    /** 子账号编号 */

    @Excel(name = "子账号编号")

    private Long accountId;


    /** 是否已结算 */

    @Excel(name = "是否已结算")

    private String isSettled;

    public void setSerialId(Long serialId) 
    {
        this.serialId = serialId;
    }

    public Long getSerialId() 
    {
        return serialId;
    }
    public void setSymbol(String symbol) 
    {
        this.symbol = symbol;
    }

    public String getSymbol() 
    {
        return symbol;
    }
    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setSide(String side) 
    {
        this.side = side;
    }

    public String getSide() 
    {
        return side;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setQty(BigDecimal qty) 
    {
        this.qty = qty;
    }

    public BigDecimal getQty() 
    {
        return qty;
    }
    public void setRealizedPnl(BigDecimal realizedPnl) 
    {
        this.realizedPnl = realizedPnl;
    }

    public BigDecimal getRealizedPnl() 
    {
        return realizedPnl;
    }
    public void setMarginAsset(String marginAsset) 
    {
        this.marginAsset = marginAsset;
    }

    public String getMarginAsset() 
    {
        return marginAsset;
    }
    public void setQuoteQty(BigDecimal quoteQty) 
    {
        this.quoteQty = quoteQty;
    }

    public BigDecimal getQuoteQty() 
    {
        return quoteQty;
    }
    public void setCommission(BigDecimal commission) 
    {
        this.commission = commission;
    }

    public BigDecimal getCommission() 
    {
        return commission;
    }
    public void setCommissionAsset(String commissionAsset) 
    {
        this.commissionAsset = commissionAsset;
    }

    public String getCommissionAsset() 
    {
        return commissionAsset;
    }
    public void setOrderTime(Date orderTime) 
    {
        this.orderTime = orderTime;
    }

    public Date getOrderTime() 
    {
        return orderTime;
    }
    public void setPositionSide(String positionSide) 
    {
        this.positionSide = positionSide;
    }

    public String getPositionSide() 
    {
        return positionSide;
    }
    public void setBuyer(Integer buyer) 
    {
        this.buyer = buyer;
    }

    public Integer getBuyer() 
    {
        return buyer;
    }
    public void setMaker(Integer maker) 
    {
        this.maker = maker;
    }

    public Integer getMaker() 
    {
        return maker;
    }
    public void setAccountId(Long accountId) 
    {
        this.accountId = accountId;
    }

    public Long getAccountId() 
    {
        return accountId;
    }
    public void setIsSettled(String isSettled) 
    {
        this.isSettled = isSettled;
    }

    public String getIsSettled() 
    {
        return isSettled;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("serialId", getSerialId())
            .append("symbol", getSymbol())
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("side", getSide())
            .append("price", getPrice())
            .append("qty", getQty())
            .append("realizedPnl", getRealizedPnl())
            .append("marginAsset", getMarginAsset())
            .append("quoteQty", getQuoteQty())
            .append("commission", getCommission())
            .append("commissionAsset", getCommissionAsset())
            .append("orderTime", getOrderTime())
            .append("positionSide", getPositionSide())
            .append("buyer", getBuyer())
            .append("maker", getMaker())
            .append("accountId", getAccountId())
            .append("isSettled", getIsSettled())
            .toString();
    }
}
