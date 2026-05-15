package com.wallet.tron.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.wallet.tron.domain.GetTransactionSign;
import com.wallet.tron.domain.TriggerSmartContract;
import com.wallet.tron.domain.TronSyncData;

public interface ITronService {
	public JSONObject getTransactionInfoById(String url, String hashId);

	public int getBlockNum(String url);

	public JSONObject getblockbylimitnext(String url, Integer startNum, Integer endNum) ;

	public JSONObject getTransactionInfoByBlockNum(String url, Integer num);

	public TriggerSmartContract.Result triggerSmartContract(String url, TriggerSmartContract.Param param) ;



	public JSONObject getTransactionSign(String url, GetTransactionSign.Param signParam);

	public JSONObject broadcastTransaction(String url, String dt);

	public TronSyncData getTransactionToken(String url, String hash, int unit) ;

	/**
	 * 代币转账 trc20
	 *
	 * @param contract
	 * @param fromAddress
	 * @param privateKey  fromAddress的私钥
	 * @param amount
	 * @param toAddress
	 * @param remark
	 * @return
	 */
	public String sendTokenTransaction(String url, String contract, String fromAddress, String privateKey,
			String amount, String toAddress, String remark);

	/**
	 * 查询tron币数量
	 *
	 * @param address
	 * @return
	 */
	public BigDecimal balanceOfTron(String url, String address) ;

	/**
	 * 查询合约余额
	 *
	 * @param contract 合约地址
	 * @param address  查询地址
	 * @param accuracy 代币合约精度
	 * @return
	 */
	public BigDecimal balanceOfTrc(String url, String contract, String address, int accuracy);

	

	
	 /**
     *	 转账trx
     * @param fromAddress 从那个地址转出
     * @param privateKey 转出地址私钥
     * @param amount 转账金额
     * @param toAddress 转到那个地址
     * @return
     * @throws Throwable 
     */
    public String transferTrx(String url,String fromAddress, String privateKey, String amount, String toAddress) throws Throwable;

	public JSONObject getblockbynum(String url, Integer num);

	public List<TronSyncData> syncBlock(String url, Integer num,Map<String, Object> address) ;
}
