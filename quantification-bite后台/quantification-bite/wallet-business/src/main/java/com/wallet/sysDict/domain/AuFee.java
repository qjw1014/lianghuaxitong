package com.wallet.sysDict.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wallet.common.annotation.Excel;
import javax.persistence.*;

/**
 * 手续费配置对象 w_au_fee
 * 
 * @author wallet
 * @date 2022-02-24
 */
@Table(name = "w_au_fee")
@ApiModel
public class AuFee
{
    private static final long serialVersionUID = 1L;


    /** 编号 */

	@Id
    @Excel(name = "编号")
    @ApiModelProperty(value = "编号")
    private Long id;


    /** 类型：release释放，nftSale 出售nft */

    @Excel(name = "类型：release释放，nftSale 出售nft")
    @ApiModelProperty(value = "类型：release释放，nftSale 出售nft")
    private String feeType;


    /** 类型：pen：按笔，percentage：百分比 */

    @Excel(name = "类型：pen：按笔，percentage：百分比")
    @ApiModelProperty(value = "类型：pen：按笔，percentage：百分比")
    private String type;


    /** 手续费 */

    @Excel(name = "手续费")
    @ApiModelProperty(value = "手续费,如果type是按笔，那就固定扣除该数。如果是百分比，则扣除该数的百分比")
    private BigDecimal fee;


    /** 最小数量 */

    @Excel(name = "最小数量")

    private BigDecimal minNums;


    /** 最大数量 */

    @Excel(name = "最大数量")

    private BigDecimal maxNums;


    /** 创建时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date createTime;


    /** 更新时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")

    private Date updateTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFeeType(String feeType) 
    {
        this.feeType = feeType;
    }

    public String getFeeType() 
    {
        return feeType;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setFee(BigDecimal fee) 
    {
        this.fee = fee;
    }

    public BigDecimal getFee() 
    {
        return fee;
    }
    public void setMinNums(BigDecimal minNums) 
    {
        this.minNums = minNums;
    }

    public BigDecimal getMinNums() 
    {
        return minNums;
    }
    public void setMaxNums(BigDecimal maxNums) 
    {
        this.maxNums = maxNums;
    }

    public BigDecimal getMaxNums() 
    {
        return maxNums;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("feeType", getFeeType())
            .append("type", getType())
            .append("fee", getFee())
            .append("minNums", getMinNums())
            .append("maxNums", getMaxNums())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
