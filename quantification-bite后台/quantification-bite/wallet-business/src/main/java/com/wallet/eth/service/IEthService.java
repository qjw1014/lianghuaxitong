package com.wallet.eth.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.wallet.chain.domain.Chain;
import com.wallet.eth.domain.EthSyncData;
import com.wallet.eth.domain.HoldNftDTO;

public interface IEthService {
	public BigInteger blockNumber(Chain chain);
	public BigDecimal getBalance(Chain chain, String address);
	public BigInteger getBlockTransactionCountByNumber(Chain chain, int index);
	public BigDecimal call(Chain chain, String contractAddress, String address,String unitstr);
	public String gasPrice(Chain chain);
	public Double fee(Chain chain,long gas);
	public String getBlockByNumber(Chain chain, int latest);
	public String tokenDeal(String from, String to, String value, String privateKey,
			String contractAddress, int decimal);
	public String ethTokenDeal(String from, String to, String value, String privateKey,
							String contractAddress, int decimal);
	public String EthDeal( String from, String to, String value, String privateKey);
	public EthSyncData getTransactionReceiptToken( String hash,int unit,String chainName);
	public JSONObject getTransactionReceipt(String hash,String chainName);
	public JSONObject doRequest(String method, Chain chain, Object... params);
	public JSONObject newDoRequest(String method,String chainName, Object... params);


	public BigInteger getTransactionCount(String address,String chainUrl);
	public JSONObject getTransactionByHash( String hash,String chainName);

	public BigDecimal callHash(Chain chain, String contractAddress,String tokenId);

	public String callTokenImage(Chain chain, String contractAddress,String tokenId);



	/**
	 * 获取主币交易信息
	 * @param hash
	 * @param chainName
	 * @return
	 */
	public HashMap<String,Object> getMainTransactionByHash(String hash, String chainName,int unit);

	/**
	 * 获取合约交易信息
	 * @param hash
	 * @param chainName
	 * @return
	 */
	public HashMap<String,Object> getTransactionReceiptContract(String hash, String chainName,int unit);

	/**
	 * 获取NFT交易信息
	 * @param hash
	 * @param chainName
	 * @return
	 */
	public HashMap<String,Object> getTransactionReceiptTokenNFT(String hash, String chainName);


	/**
	 * 获取NFT持有地址
	 * @param chainName
	 * @return
	 */
	public String getNftHoldAddress(String tokenId, String contractAddress);

	/**
	 * 铸造nft
 	 * @param toAddress 转入地址
	 * @param fromAddress 转出地址
	 * @param contractAddress 链合约地址
	 * @param privateKey 私钥
	 * @param tokenId 唯一编号
	 * @param images nft图片
	 * @param price 价值（如没有，则是0）
	 * @param unit 主链精度
	 * @param chainId 链编号（链的，ETH是1）
	 */
	public String mintToken(String toAddress,String fromAddress,String contractAddress,String privateKey,Integer tokenId,String images,BigDecimal price,int unit,Long chainId);


	/**
	 * 转移NFT所有权(需要钱包秘钥)
	 * @param fromAddress 转出钱包地址
	 * @param toAddress 转入地址
	 * @param contractAddress 合约地址
	 * @param privateKey 转出钱包地址私钥
	 * @param tokenId 转出tokenId
	 * @param chainId 转出链编号
	 * @return
	 */
	public String safeTransferFrom(String fromAddress,String toAddress,String contractAddress,String privateKey,Integer tokenId,Long chainId);


	/**
	 * 转移NFT所有权(最高权限)
	 * @param castAddress 主钱包地址（高级权限钱包地址）
	 * @param fromAddress 转出钱包地址
	 * @param toAddress 转入地址
	 * @param contractAddress 合约地址
	 * @param privateKey 高级权限钱包地址私钥
	 * @param tokenId 转出tokenId
	 * @param chainId 转出链编号
	 * @return
	 */
	public HashMap<String,String> transferFrom(String castAddress,String fromAddress,String toAddress,String contractAddress,String privateKey,Integer tokenId,Long chainId);

	/**
	 * 销毁
	 * @param toAddress 转入地址
	 * @param fromAddress 转出地址
	 * @param contractAddress 链合约地址
	 * @param privateKey 私钥
	 * @param tokenId 唯一编号
	 * @param chainId 链编号（链的，ETH是1）
	 */
	public String burn(String toAddress,String fromAddress,String contractAddress,String privateKey,Integer tokenId,Long chainId);



	/**
	 * 销毁然后铸造
	 */
	public String burnAndRecast(String toAddress,String fromAddress,String contractAddress,String privateKey,Integer tokenId,Long chainId,BigDecimal castNum);


	/**
	 * 共用合约（修改-铸造-转让-销毁-修改）VNFT toeknId方法
	 * @return
	 */
	public HashMap<String,String> holdNft(HoldNftDTO dto);


}
