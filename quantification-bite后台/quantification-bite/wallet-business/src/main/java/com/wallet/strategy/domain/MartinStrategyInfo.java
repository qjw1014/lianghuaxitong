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
 * 策略信息对象 t_martin_strategy_info
 * 
 * @author wallet
 * @date 2022-08-31
 */
@Table(name = "t_martin_strategy_info")
public class MartinStrategyInfo
{
    private static final long serialVersionUID = 1L;


    /** 策略编号 */

	@Id
    @Excel(name = "策略编号")

    private Long strategyId;


    /** 策略状态 0未开始 1.开启 2.关闭 */

    @Excel(name = "策略状态 0未开始 1.开启 2.关闭")

    private Integer strategyStatus;


    /** 用户uuid */

    @Excel(name = "用户uuid")

    private String userUuid;


    /** 交易所 */

    @Excel(name = "交易所")

    private String platform;


    /** 交易对 */

    @Excel(name = "交易对")

    private String symbol;


    /** 子账号id */

    @Excel(name = "子账号id")

    private Long apiAccountId;


    /** 币种 */

    @Excel(name = "币种")

    private String coin;


    /** 杠杆倍数 */

    @Excel(name = "杠杆倍数")

    private Integer leverage;


    /** 多方向 True为启动，False为关闭 */

    @Excel(name = "多方向 True为启动，False为关闭")

    private String longOpen;


    /** 多方向次数 "once"为单次，"cycle"为循环 */

    @Excel(name = "多方向次数 once为单次，cycle为循环")

    private String longCount;


    /** 空方向 True为启动，False为关闭 */

    @Excel(name = "空方向 True为启动，False为关闭")

    private String shortOpen;


    /** 空方向次数 "once"为单次，"cycle"为循环 */

    @Excel(name = "空方向次数 once为单次，cycle为循环")

    private String shortCount;


    /** 默认备用 True为启动，False为关闭 */

    @Excel(name = "默认备用 True为启动，False为关闭")

    private String common;


    /** 一键平多比例 */

    @Excel(name = "一键平多比例")

    private BigDecimal sellLongRate;


    /** 一键平空比例 */

    @Excel(name = "一键平空比例")

    private BigDecimal buyShortRate;


    /** 一键开多比例 */

    @Excel(name = "一键开多比例")

    private BigDecimal buyLongRate;


    /** 一键开空比例 */

    @Excel(name = "一键开空比例")

    private BigDecimal sellShortRate;


    /** 添加时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")

    private Date crateTime;


    /** 修改时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")

    private Date updateTime;


    /** 修改时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")

    private Date stopTime;


    /** 是否删除 Y是 N否 */

    @Excel(name = "是否删除 Y是 N否")

    private String isDelete;


    /** 收益数量 */

    @Excel(name = "收益数量")

    private BigDecimal allProfit;


    /** 扣除数量 */

    @Excel(name = "扣除数量")

    private BigDecimal deductProfit;


    /** 周收益 */

    @Excel(name = "周收益")

    private BigDecimal weekProfit;


    /** 周分润比例 */

    @Excel(name = "周分润比例")

    private BigDecimal weekProfitRate;


    /** 已分润数量 */

    @Excel(name = "已分润数量")

    private BigDecimal shareProfit;


    /** 策略类型 1.策略一 */

    @Excel(name = "策略类型 1.策略一")

    private Long strategyType;

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
    public void setUserUuid(String userUuid) 
    {
        this.userUuid = userUuid;
    }

    public String getUserUuid() 
    {
        return userUuid;
    }
    public void setPlatform(String platform) 
    {
        this.platform = platform;
    }

    public String getPlatform() 
    {
        return platform;
    }
    public void setSymbol(String symbol) 
    {
        this.symbol = symbol;
    }

    public String getSymbol() 
    {
        return symbol;
    }
    public void setApiAccountId(Long apiAccountId) 
    {
        this.apiAccountId = apiAccountId;
    }

    public Long getApiAccountId() 
    {
        return apiAccountId;
    }
    public void setCoin(String coin) 
    {
        this.coin = coin;
    }

    public String getCoin() 
    {
        return coin;
    }
    public void setLeverage(Integer leverage)
    {
        this.leverage = leverage;
    }

    public Integer getLeverage()
    {
        return leverage;
    }
    public void setLongOpen(String longOpen) 
    {
        this.longOpen = longOpen;
    }

    public String getLongOpen() 
    {
        return longOpen;
    }
    public void setLongCount(String longCount) 
    {
        this.longCount = longCount;
    }

    public String getLongCount() 
    {
        return longCount;
    }
    public void setShortOpen(String shortOpen) 
    {
        this.shortOpen = shortOpen;
    }

    public String getShortOpen() 
    {
        return shortOpen;
    }
    public void setShortCount(String shortCount) 
    {
        this.shortCount = shortCount;
    }

    public String getShortCount() 
    {
        return shortCount;
    }
    public void setCommon(String common) 
    {
        this.common = common;
    }

    public String getCommon() 
    {
        return common;
    }
    public void setSellLongRate(BigDecimal sellLongRate) 
    {
        this.sellLongRate = sellLongRate;
    }

    public BigDecimal getSellLongRate() 
    {
        return sellLongRate;
    }
    public void setBuyShortRate(BigDecimal buyShortRate) 
    {
        this.buyShortRate = buyShortRate;
    }

    public BigDecimal getBuyShortRate() 
    {
        return buyShortRate;
    }
    public void setBuyLongRate(BigDecimal buyLongRate) 
    {
        this.buyLongRate = buyLongRate;
    }

    public BigDecimal getBuyLongRate() 
    {
        return buyLongRate;
    }
    public void setSellShortRate(BigDecimal sellShortRate) 
    {
        this.sellShortRate = sellShortRate;
    }

    public BigDecimal getSellShortRate() 
    {
        return sellShortRate;
    }
    public void setCrateTime(Date crateTime) 
    {
        this.crateTime = crateTime;
    }

    public Date getCrateTime() 
    {
        return crateTime;
    }
    public void setUpdateTime(Date updateTime) 
    {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() 
    {
        return updateTime;
    }
    public void setStopTime(Date stopTime) 
    {
        this.stopTime = stopTime;
    }

    public Date getStopTime() 
    {
        return stopTime;
    }
    public void setIsDelete(String isDelete) 
    {
        this.isDelete = isDelete;
    }

    public String getIsDelete() 
    {
        return isDelete;
    }
    public void setAllProfit(BigDecimal allProfit) 
    {
        this.allProfit = allProfit;
    }

    public BigDecimal getAllProfit() 
    {
        return allProfit;
    }
    public void setDeductProfit(BigDecimal deductProfit) 
    {
        this.deductProfit = deductProfit;
    }

    public BigDecimal getDeductProfit() 
    {
        return deductProfit;
    }
    public void setWeekProfit(BigDecimal weekProfit) 
    {
        this.weekProfit = weekProfit;
    }

    public BigDecimal getWeekProfit() 
    {
        return weekProfit;
    }
    public void setWeekProfitRate(BigDecimal weekProfitRate) 
    {
        this.weekProfitRate = weekProfitRate;
    }

    public BigDecimal getWeekProfitRate() 
    {
        return weekProfitRate;
    }
    public void setShareProfit(BigDecimal shareProfit) 
    {
        this.shareProfit = shareProfit;
    }

    public BigDecimal getShareProfit() 
    {
        return shareProfit;
    }
    public void setStrategyType(Long strategyType)
    {
        this.strategyType = strategyType;
    }

    public Long getStrategyType()
    {
        return strategyType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("strategyId", getStrategyId())
            .append("strategyStatus", getStrategyStatus())
            .append("userUuid", getUserUuid())
            .append("platform", getPlatform())
            .append("symbol", getSymbol())
            .append("apiAccountId", getApiAccountId())
            .append("coin", getCoin())
            .append("leverage", getLeverage())
            .append("longOpen", getLongOpen())
            .append("longCount", getLongCount())
            .append("shortOpen", getShortOpen())
            .append("shortCount", getShortCount())
            .append("common", getCommon())
            .append("sellLongRate", getSellLongRate())
            .append("buyShortRate", getBuyShortRate())
            .append("buyLongRate", getBuyLongRate())
            .append("sellShortRate", getSellShortRate())
            .append("crateTime", getCrateTime())
            .append("updateTime", getUpdateTime())
            .append("stopTime", getStopTime())
            .append("isDelete", getIsDelete())
            .append("allProfit", getAllProfit())
            .append("deductProfit", getDeductProfit())
            .append("weekProfit", getWeekProfit())
            .append("weekProfitRate", getWeekProfitRate())
            .append("shareProfit", getShareProfit())
            .append("strategyType", getStrategyType())
            .toString();
    }
}
