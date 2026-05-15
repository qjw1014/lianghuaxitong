package com.wallet.sysDict.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 手续费配置对象 w_au_fee
 * @author wallet
 * @date 2022-02-24
 */
@ApiModel(value = "手续费配置对象")
public class AuFeeDTO
{
    private static final long serialVersionUID = 1L;


    /** 类型：release释放，nftSale 出售nft */
    @ApiModelProperty(value = "类型：release释放，nftSale 出售nft customized定制   ")
    private String feeType;

    /** 类型：pen：按笔，percentage：百分比 */
    @ApiModelProperty(value = "类型：pen：按笔，percentage：百分比")
    private String type;

    /** 手续费 */
    @ApiModelProperty(value  = "手续费率")
    private BigDecimal fee;

    /** 最小数量 */
    @ApiModelProperty(value =  "最小数量")
    private BigDecimal minNums;

    /** 最大数量 */
    @ApiModelProperty(value  = "最大数量")
    private BigDecimal maxNums;

    /** 真实手续费 */
    @ApiModelProperty(value  = "真实手续费")
    private BigDecimal realFee;

    /** 价值币种*/
    @ApiModelProperty(value = "价值币种")
    private String valueCoin;

    /** 手续费币种*/
    @ApiModelProperty(value = "手续费币种")
    private String feeCoin;

    /** 收款地址*/
    @ApiModelProperty(value = "收款地址")
    private String collectionAddress;

    @ApiModelProperty(value = "合约地址")
    private String contractAddress;

    @ApiModelProperty(value = "最新时间")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getCollectionAddress() {
        return collectionAddress;
    }

    public void setCollectionAddress(String collectionAddress) {
        this.collectionAddress = collectionAddress;
    }

    public BigDecimal getRealFee() {
        return realFee;
    }

    public void setRealFee(BigDecimal realFee) {
        this.realFee = realFee;
    }

    public String getValueCoin() {
        return valueCoin;
    }

    public void setValueCoin(String valueCoin) {
        this.valueCoin = valueCoin;
    }

    public String getFeeCoin() {
        return feeCoin;
    }

    public void setFeeCoin(String feeCoin) {
        this.feeCoin = feeCoin;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getMinNums() {
        return minNums;
    }

    public void setMinNums(BigDecimal minNums) {
        this.minNums = minNums;
    }

    public BigDecimal getMaxNums() {
        return maxNums;
    }

    public void setMaxNums(BigDecimal maxNums) {
        this.maxNums = maxNums;
    }
}
