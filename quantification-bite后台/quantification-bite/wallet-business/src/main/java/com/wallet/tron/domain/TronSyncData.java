package com.wallet.tron.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel
public class TronSyncData {

	@ApiModelProperty(value = "trix哈希")
	private String txid;
	@ApiModelProperty(value = "接收地址")
	private String toAddress;
	@ApiModelProperty(value = "转出地址")
	private String fromAddress;
	@ApiModelProperty(value = "合约地址")
	private String contract;
	@ApiModelProperty(value = "区块高度")
	private Integer blockNumber;
	@ApiModelProperty(value = "交易数量")
	private BigDecimal amount;
	@ApiModelProperty(value = "状态")
	private Boolean status;
	@ApiModelProperty(value = "备注")
	private String remarks;
	@ApiModelProperty(value = "手续费")
	private BigDecimal fee;
	@ApiModelProperty(value = "区块时间")
	private Date blockTime;

	public String getTxid() {
		return txid;
	}
	public void setTxid(String txid) {
		this.txid = txid;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getBlockNumber() {
		return blockNumber;
	}
	public void setBlockNumber(Integer blockNumber) {
		this.blockNumber = blockNumber;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Date getBlockTime() {
		return blockTime;
	}

	public void setBlockTime(Date blockTime) {
		this.blockTime = blockTime;
	}
}
