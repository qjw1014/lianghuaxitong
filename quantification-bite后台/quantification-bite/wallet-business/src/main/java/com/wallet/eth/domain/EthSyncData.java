package com.wallet.eth.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

public class EthSyncData {

	private String txid;
	private String toAddress;
	private String fromAddress;
	private String contract;
	private BigInteger blockNumber;
	private BigDecimal amount;
	private Boolean status;
	private String remarks;
	private String tokenId;



	private BigInteger gasPrice;

    private BigInteger gasUsed;

	public BigInteger getGasPrice() {
		return gasPrice;
	}

	public void setGasPrice(BigInteger gasPrice) {
		this.gasPrice = gasPrice;
	}
	public BigInteger getGasUsed() {
		return gasUsed;
	}

	public void setGasUsed(BigInteger gasUsed) {
		this.gasUsed = gasUsed;
	}

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
	
	
	public BigInteger getBlockNumber() {
		return blockNumber;
	}
	public void setBlockNumber(BigInteger blockNumber) {
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

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
}
