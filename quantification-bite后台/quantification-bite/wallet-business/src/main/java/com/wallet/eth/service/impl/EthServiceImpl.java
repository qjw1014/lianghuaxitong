package com.wallet.eth.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.wallet.chain.domain.ChainConfigUrl;
import com.wallet.common.core.domain.AjaxResult;
import com.wallet.common.enums.Enum;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.JsonUtils;
import com.wallet.common.utils.StringUtils;
import com.wallet.eth.domain.HoldNftDTO;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wallet.chain.domain.Chain;
import com.wallet.common.utils.http.HttpUtils;
import com.wallet.eth.common.EthUtils;
import com.wallet.eth.domain.EthSyncData;
import com.wallet.eth.service.IEthService;
@Service
public class EthServiceImpl implements IEthService {
	private static final Logger logger = LoggerFactory.getLogger(EthServiceImpl.class);
	private final static String RESULT = "result";
	private final static String LATEST = "latest";
	private final static String METHOD_ETH_GETTRANSACTIONRECEIPT = "eth_getTransactionReceipt";
	// 获取区块高度
	private final static String METHOD_ETH_BLOCKNUMBER = "eth_blockNumber";
	// 获取钱包余额
	private final static String METHOD_ETH_GETBALANCE = "eth_getBalance";
	// 获取指定编号区块 的交易数量
	private final static String METHOD_ETH_GETBLOCKTRANSACTIONCOUNTBYNUMBER = "eth_getBlockTransactionCountByNumber";

	private final static String METHOD_ETH_CALL = "eth_call";
	// 方法对密码库中指定地址对应的密钥进行解密。当使用Geth的JavaScript
	// 获取当前的gas价格。
	private final static String METHOD_ETH_GASPRICE = "eth_gasPrice";
	// 根据高度获取区块
	private final static String METHOD_ETH_GETBLOCKBYNUMBER = "eth_getBlockByNumber";
	//根据地址获取交易笔数
	private final static String  ETH_GETTRANSACTIONCOUNT="eth_getTransactionCount";
	//根据哈希查询主币交易
	private final static String METHOD_ETH_GETTRANSACTIONBYHASH="eth_getTransactionByHash";

	public final String USDT_UNITSTR = "1000000";// usdt
	public final String USDT_CONTRACT="0xdac17f958d2ee523a2206206994597c13d831ec7";

	//private static final String chainUrl = "http://47.245.28.103:8000";
	private static final String ethChainUrl = "http://47.245.25.82:8545";
	@Autowired
	private RedisUtil redis;

	@Autowired
	private ChainConfigUrl.NodeUrl nodeUrl;

	@Override
	public BigInteger blockNumber(Chain chain) {
		JSONObject json = doRequest(METHOD_ETH_BLOCKNUMBER, chain);
		if (EthUtils.isError(json)) {
			logger.error("获取区块高度失败:{}", json.get("error"));
			return new BigInteger("0");
		}
		return EthUtils.decodeHex(json.getString(RESULT));
	}



	/**
	 * 获取钱包余额
	 *
	 * @param chain
	 * @return
	 * @throws Exception
	 */
	@Override
	public BigDecimal getBalance(Chain chain, String address) {
		JSONObject json = doRequest(METHOD_ETH_GETBALANCE, chain, address, "latest");
		if (EthUtils.isError(json)) {
			logger.error("获取钱包余额失败:{}", json.get("error"));
			return new BigDecimal(0);
		}

		return new BigDecimal(EthUtils.decodeHex(json.getString("result")).toString()).divide(new BigDecimal(EthUtils.UNITSTR), 8,
				BigDecimal.ROUND_DOWN);
	}

	@Override
	public JSONObject getTransactionReceipt( String hash,String chainName) {
		JSONObject json = newDoRequest(METHOD_ETH_GETTRANSACTIONRECEIPT,chainName, hash);
		if (EthUtils.isError(json)) {
			logger.error("根据hash获取交易失败:{}", json.get("error"));
			return json;
		}
		return json.getJSONObject(RESULT);
	}
	@Override
	public JSONObject getTransactionByHash( String hash,String chainName) {
		JSONObject json = newDoRequest(METHOD_ETH_GETTRANSACTIONBYHASH,chainName, hash);
		if (EthUtils.isError(json)) {
			logger.error("根据hash获取交易失败:{}", json.get("error"));
			return json;
		}
		return json.getJSONObject(RESULT);
	}

	@Override
	public BigDecimal callHash(Chain chain, String contractAddress, String tokenId) {
		HashMap<String, Object> parmas = new HashMap<String, Object>();
		String method = "0x8b9f6ab9";
		parmas.put("to", contractAddress);
		parmas.put("data", method + EthUtils.fill_zero(EthUtils.encodeDec(new BigDecimal(tokenId))));
		JSONObject json = doRequest(METHOD_ETH_CALL, chain, parmas, LATEST);
		if (EthUtils.isError(json)) {
			logger.error("太坊代币余额代币失败:{}", json.get("error"));
			return null;
		}
		return EthUtils.realValue(EthUtils.decodeHex(json.getString(RESULT)).toString(),EthUtils.UNITSTR);
	}

	/**
	 * 查询token图片
	 * @param chain
	 * @param contractAddress
	 * @param tokenId
	 * @return
	 */
	@Override
	public String callTokenImage(Chain chain, String contractAddress, String tokenId) {
		HashMap<String, Object> parmas = new HashMap<String, Object>();
		String method = "0xc87b56dd";
		parmas.put("to", contractAddress);
		parmas.put("data", method + EthUtils.fill_zero(EthUtils.encodeDec(new BigDecimal(tokenId))));
		JSONObject json = doRequest(METHOD_ETH_CALL, chain, parmas, LATEST);
		if (EthUtils.isError(json)) {
			logger.error("查询token图片失败:{}", json.get("error"));
			return null;
		}
		return  EthUtils.getTokenImage(json.getString(RESULT));
	}

	/**
	 * 获取主币交易信息
	 * @param hash
	 * @param chainName
	 * @param unit
	 * @return
	 */
	@Override
	public HashMap<String,Object> getMainTransactionByHash(String hash, String chainName, int unit) {
		HashMap<String,Object> map = new HashMap<>();
		try {
			EthSyncData result = new EthSyncData();
			result.setStatus(false);
			JSONObject data = getTransactionByHash(hash,chainName);
			if(data==null) {
				return null;
			}
			if (data.containsKey("error")) {//说明是异常
				map.put("error","异常");
			}else{
				BigInteger amount = hexToBigInteger(data.getString("value"));
				BigDecimal decimal = new BigDecimal(amount);
				decimal=decimal.divide(new BigDecimal(Math.pow(10, unit)), 8, BigDecimal.ROUND_DOWN);
				result.setAmount(decimal);
				result.setToAddress(data.getString("to"));
				result.setFromAddress(data.getString("from"));
				result.setStatus(true);
				map.put("data",result);
			}
		}catch (Exception e){
			e.printStackTrace();
			map.put("error","转换数据异常");
		}
		return map;
	}

	/**
	 * 获取指定编号区块 的交易数量
	 */
	@Override
	public BigInteger getBlockTransactionCountByNumber(Chain chain, int index) {
		JSONObject json = doRequest(METHOD_ETH_GETBLOCKTRANSACTIONCOUNTBYNUMBER, chain,
				EthUtils.encodeDec(new BigDecimal(index)));
		if (EthUtils.isError(json)) {
			logger.error("获取指定编号区块 的交易数量失败:{}", json.get("error"));
			return new BigInteger("0");
		}

		return EthUtils.decodeHex(json.getString(RESULT));
	}

	public static void main(String[] args) throws ParseException, InterruptedException {
		EthServiceImpl eth=new EthServiceImpl();
		Chain chain=new Chain();
		chain.setChainNodeUrl("http://47.245.28.103:8000");
		try{

		}catch (Exception e){
			e.printStackTrace();
		}
	}
    //获取手续费
    public static  Double getTransactionFee(String hash){
		EthServiceImpl eth=new EthServiceImpl();
		Chain chain=new Chain();
		chain.setChainNodeUrl("http://47.245.28.103:8000");
		EthSyncData data=eth.getTransactionReceiptToken(hash,6,"MBC");
		BigInteger fee=data.getGasPrice().multiply(data.getGasUsed());
		 System.out.println(fee);
		Double b=fee.doubleValue()/Math.pow(10, 18);
        return  b;
	}




    public static BigInteger hexToBigInteger(String strHex) {
        if (strHex.length() > 2) {
            if (strHex.charAt(0) == '0' && (strHex.charAt(1) == 'X' || strHex.charAt(1) == 'x')) {
                strHex = strHex.substring(2);
            }
            BigInteger bigInteger = new BigInteger(strHex, 16);
            return bigInteger;
        }
        return null;
    }

	/**
	 * 获取以太坊代币余额代币
	 *
	 * @param chain
	 * @return
	 */
	@Override
	public BigDecimal call(Chain chain, String contractAddress, String address,String unitstr) {
		HashMap<String, Object> parmas = new HashMap<String, Object>();
		String method = "0x70a08231";
		parmas.put("to", contractAddress);
		parmas.put("data", method + EthUtils.fill_zero(address));
		JSONObject json = doRequest(METHOD_ETH_CALL, chain, parmas, LATEST);
		if (EthUtils.isError(json)) {
			logger.error("太坊代币余额代币失败:{}", json.get("error"));
			return null;
		}
		return EthUtils.realValue(EthUtils.decodeHex(json.getString(RESULT)).toString(),unitstr);
	}



	/**
	 * 获取消耗价格
	 *
	 * @param chain
	 * @return
	 */
	@Override
	public String gasPrice(Chain chain) {

		JSONObject json = doRequest(METHOD_ETH_GASPRICE, chain);
		if (EthUtils.isError(json)) {
			logger.error("获取消耗价格失败:{}", json.get("error"));
			return null;
		}

		return json.getString(RESULT);
	}


	//根据地址获取交易笔数
	public   BigInteger  getTransactionCount(String address,String chainUrl){
		Chain chain=new Chain();
		chain.setChainNodeUrl(chainUrl);
		JSONObject json = doRequest(ETH_GETTRANSACTIONCOUNT, chain,address, "pending");
		if(json.getString("result")!=null){
			BigInteger result=	hexToBigInteger(json.getString("result"));
			return  result;
		}
		return  null;
	}


	/**
	 * 手续费
	 *
	 * @param chain
	 * @return
	 */
	@Override
	public Double fee(Chain chain,long gas) {

		JSONObject json = doRequest(METHOD_ETH_GASPRICE, chain);
		if (EthUtils.isError(json)) {
			logger.error("获取消耗价格失败:{}", json.get("error"));
			return null;
		}

		Long gasprice = Long.parseLong( json.getString("result").substring(2),16);
		return gasprice*gas/Math.pow(10, 18) ;
	}

	/**
	 * 获取指定区块数据
	 *
	 * @param chain
	 * @param latest
	 * @return
	 */
	@Override
	public String getBlockByNumber(Chain chain, int latest) {

		JSONObject json = doRequest(METHOD_ETH_GETBLOCKBYNUMBER, chain, EthUtils.encodeDec(new BigDecimal(latest)), true);
		if (EthUtils.isError(json)) {
			logger.error("获取指定区块数据失败:{}", json.get("error"));
			return null;
		}

		return json.getString(RESULT);
	}

	@Override
	public  String ethTokenDeal(String from, String to, String value, String privateKey,
							   String contractAddress, int decimal){
		try {
			Web3j web3j=Web3j.build(new HttpService(nodeUrl.getEthChainUrl()));
			// 转账的凭证，需要传入私钥
			Credentials credentials = Credentials.create(privateKey);
			// 获取交易笔数
			BigInteger nonce;
			EthGetTransactionCount ethGetTransactionCount = web3j
					.ethGetTransactionCount(from, DefaultBlockParameterName.PENDING).send();
			if (ethGetTransactionCount == null) {
				return null;
			}
			nonce = ethGetTransactionCount.getTransactionCount();
			while (!redis.setBizOperation("nonce:"+from+":"+nonce,20l)){
				ethGetTransactionCount = web3j.ethGetTransactionCount(from, DefaultBlockParameterName.PENDING).send();
				if (ethGetTransactionCount == null) {
					return null;
				}
				nonce = ethGetTransactionCount.getTransactionCount();
			}
			// 手续费
			BigInteger gasPrice;
			EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
			if (ethGasPrice == null) {
				return null;
			}
			// 注意手续费的设置，这块很容易遇到问题
			BigInteger gasLimit = BigInteger.valueOf(60000L);
			gasPrice = ethGasPrice.getGasPrice();
			BigInteger val = new BigDecimal(value).multiply(new BigDecimal("10").pow(decimal)).toBigInteger();// 单位换算
			Function function = new Function("transfer", Arrays.asList(new Address(to), new Uint256(val)),
					Collections.singletonList(new TypeReference<Type>() {
					}));
			// 创建交易对象
			String encodedFunction = FunctionEncoder.encode(function);
			RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, contractAddress,
					encodedFunction);

			// 进行签名操作

			byte[] signMessage = TransactionEncoder.signMessage(rawTransaction,1, credentials);

			String hexValue = Numeric.toHexString(signMessage);
			// 发起交易
			EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
			String hash = ethSendTransaction.getTransactionHash();
			if (hash != null) {
				// 执行业务
				return hash;
			}else{
                redis.del("nonce:"+from+":"+nonce);
            }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;




	}


	/**
	 * 自己链转账合约币
	 * @param from
	 * @param to
	 * @param value
	 * @param privateKey
	 * @param contractAddress
	 * @param decimal
	 * @return
	 */
	@Override
	public String tokenDeal(String from, String to, String value, String privateKey,
							String contractAddress, int decimal) {
		try {
			Web3j web3j=Web3j.build(new HttpService(nodeUrl.getChainUrl()));
			// 转账的凭证，需要传入私钥
			Credentials credentials = Credentials.create(privateKey);
			// 获取交易笔数
			BigInteger nonce;
			EthGetTransactionCount ethGetTransactionCount = web3j
					.ethGetTransactionCount(from, DefaultBlockParameterName.PENDING).send();
			if (ethGetTransactionCount == null) {
				return null;
			}
			nonce = ethGetTransactionCount.getTransactionCount();
			while (!redis.setBizOperation("nonce:"+from+":"+nonce,20l)){
				ethGetTransactionCount = web3j.ethGetTransactionCount(from, DefaultBlockParameterName.PENDING).send();
				if (ethGetTransactionCount == null) {
					return null;
				}
				nonce = ethGetTransactionCount.getTransactionCount();
			}
			// 手续费
			BigInteger gasPrice;
			EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
			if (ethGasPrice == null) {
				return null;
			}
			gasPrice = ethGasPrice.getGasPrice();
			// 注意手续费的设置，这块很容易遇到问题
			BigInteger gasLimit = BigInteger.valueOf(60000L);

			BigInteger val = new BigDecimal(value).multiply(new BigDecimal("10").pow(decimal)).toBigInteger();// 单位换算
			Function function = new Function("transfer", Arrays.asList(new Address(to), new Uint256(val)),
					Collections.singletonList(new TypeReference<Type>() {
					}));
			// 创建交易对象
			String encodedFunction = FunctionEncoder.encode(function);
			RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, contractAddress,
					encodedFunction);

			// 进行签名操作

			byte[] signMessage = TransactionEncoder.signMessage(rawTransaction,nodeUrl.getChainId(), credentials);

			String hexValue = Numeric.toHexString(signMessage);
			// 发起交易
			EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
			String hash = ethSendTransaction.getTransactionHash();
			if (hash != null) {
				// 执行业务
				return hash;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 自己链转账主币
	 * @param from
	 * @param to
	 * @param value
	 * @param privateKey
	 * @return
	 */
	@Override
	public String EthDeal( String from, String to, String value, String privateKey) {
		try {
			Web3j web3j=Web3j.build(new HttpService(nodeUrl.getChainUrl()));
			// 转账的凭证，需要传入私钥
			Credentials credentials = Credentials.create(privateKey);
			// 获取交易笔数
			BigInteger nonce;
			EthGetTransactionCount ethGetTransactionCount = web3j
					.ethGetTransactionCount(from, DefaultBlockParameterName.PENDING).send();
			if (ethGetTransactionCount == null) {
				return null;
			}
			nonce = ethGetTransactionCount.getTransactionCount();
			while (!redis.setBizOperation("nonce:"+from+":"+nonce,20L)){
				ethGetTransactionCount = web3j.ethGetTransactionCount(from, DefaultBlockParameterName.PENDING).send();
				if (ethGetTransactionCount == null) {
					return null;
				}
				nonce = ethGetTransactionCount.getTransactionCount();
			}
			// 手续费
			BigInteger gasPrice;
			EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
			if (ethGasPrice == null) {
				return null;
			}
			gasPrice = ethGasPrice.getGasPrice();
			// 注意手续费的设置，这块很容易遇到问题
			BigInteger gasLimit = BigInteger.valueOf(60000L);

			BigInteger val = new BigDecimal(value).multiply(new BigDecimal("10").pow(18)).toBigInteger();// 单位换算

			RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit, to, val);
			// 进行签名操作
			byte[] signMessage = TransactionEncoder.signMessage(rawTransaction,nodeUrl.getChainId(), credentials);
			String hexValue = Numeric.toHexString(signMessage);
			// 发起交易
			EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
			String hash = ethSendTransaction.getTransactionHash();
			if (hash != null) {
				// 执行业务
				return hash;
			}else{
                redis.del("nonce:"+from+":"+nonce);
            }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}




	@Override
	public EthSyncData getTransactionReceiptToken(String hash,int unit,String chainName ) {
		JSONObject hashDataJson=getTransactionReceipt(hash, chainName);
		if(hashDataJson==null) {
			return null;
		}
		EthSyncData ethSyncData=new EthSyncData();
		ethSyncData.setStatus(false);

		if(hashDataJson.getString("status")==null) {
			ethSyncData.setRemarks("状态不存在");
			return ethSyncData;
		}
		String contract = hashDataJson.getString("to").toLowerCase();
		 System.out.println(EthUtils.decodeHex(hashDataJson.getString("status")).intValue());
		if(EthUtils.decodeHex(hashDataJson.getString("status")).intValue()<1) {
			ethSyncData.setRemarks("交易失败");
			return ethSyncData;
		}

		JSONArray logsArray = hashDataJson.getJSONArray("logs");
		if(CollectionUtils.isEmpty(logsArray)) {
			ethSyncData.setRemarks("交易logs数据不存在");
			return ethSyncData;
		}
		JSONObject logs = logsArray.getJSONObject(0);
		JSONArray topics = logs.getJSONArray("topics");
		if (CollectionUtils.isEmpty(topics)|| topics.size() < 3) {
			ethSyncData.setRemarks("交易topics数据不存在");
			return ethSyncData;
		}
		ethSyncData.setToAddress(EthUtils.getridof_zero_address(topics.getString(2)).toLowerCase());
		ethSyncData.setFromAddress(EthUtils.getridof_zero_address(topics.getString(1)).toLowerCase());
		BigDecimal amount=new BigDecimal(EthUtils.decodeHex(logs.getString("data")));
		amount=amount.divide(new BigDecimal(Math.pow(10, unit)), 8, BigDecimal.ROUND_DOWN);
		ethSyncData.setAmount(amount);
		ethSyncData.setBlockNumber(EthUtils.decodeHex(hashDataJson.getString("blockNumber")));
		ethSyncData.setTxid(hash);
		ethSyncData.setContract(contract);
		ethSyncData.setStatus(true);
		ethSyncData.setGasUsed(EthUtils.decodeHex(hashDataJson.getString("gasUsed")));
		ethSyncData.setGasPrice(EthUtils.decodeHex(hashDataJson.getString("effectiveGasPrice")));
		return ethSyncData;
	}

	/**
	 * 获取NFT交易信息
	 * @param hash
	 * @param chainName
	 * @return
	 */
	@Override
	public HashMap<String,Object> getTransactionReceiptTokenNFT(String hash, String chainName) {
		HashMap<String,Object> map = new HashMap<>();
		List<EthSyncData> list = new ArrayList<>();
		JSONObject hashDataJson=getTransactionReceipt(hash, chainName);
		if(hashDataJson==null) {
			return map;
		}
		if(hashDataJson.getString("status")==null) {
			map.put("error","状态不存在");
			return map;
		}
		if(EthUtils.decodeHex(hashDataJson.getString("status")).intValue()<1) {
			map.put("error","交易失败");
			return map;
		}
		JSONArray logsArray = hashDataJson.getJSONArray("logs");
		if(CollectionUtils.isEmpty(logsArray)) {
			map.put("error","交易logs数据不存在");
			return map;
		}
		JSONObject logs = logsArray.getJSONObject(0);
		JSONArray topics = logs.getJSONArray("topics");
		if (CollectionUtils.isEmpty(topics)|| topics.size() < 4) {
			map.put("error","交易logs数据不存在");
			return map;
		}
		String contract = hashDataJson.getString("to").toLowerCase();
		for(Object json:logsArray){
			JSONObject dataJson = (JSONObject) json;
			EthSyncData ethSyncData = new EthSyncData();
			JSONArray topicsArray = dataJson.getJSONArray("topics");
			ethSyncData.setToAddress(EthUtils.getridof_zero_address(topicsArray.getString(2)).toLowerCase());
			ethSyncData.setFromAddress(EthUtils.getridof_zero_address(topicsArray.getString(1)).toLowerCase());
			ethSyncData.setBlockNumber(EthUtils.decodeHex(hashDataJson.getString("blockNumber")));
			ethSyncData.setTxid(hash);
			ethSyncData.setContract(contract);
			ethSyncData.setStatus(true);
			String token = topics.getString(3);
			ethSyncData.setTokenId(EthUtils.decodeHex(token).toString());
			list.add(ethSyncData);
		}
		map.put("list",list);
		return map;
	}

	/**
	 * 获取合约交易
	 * @param hash
	 * @param chainName
	 * @return
	 */
	@Override
	public HashMap<String, Object> getTransactionReceiptContract(String hash, String chainName,int unit) {
		HashMap<String,Object> map = new HashMap<>();
		List<EthSyncData> list = new ArrayList<>();
		JSONObject hashDataJson=getTransactionReceipt(hash, chainName);
		if(hashDataJson==null) {
			return map;
		}
		if(hashDataJson.getString("status")==null) {
			map.put("error","状态不存在");
			return map;
		}
		if(EthUtils.decodeHex(hashDataJson.getString("status")).intValue()<1) {
			map.put("error","交易失败");
			return map;
		}
		JSONArray logsArray = hashDataJson.getJSONArray("logs");
		if(CollectionUtils.isEmpty(logsArray)) {
			map.put("error","交易logs数据不存在");
			return map;
		}
		JSONObject logs = logsArray.getJSONObject(0);
		JSONArray topics = logs.getJSONArray("topics");
		if (CollectionUtils.isEmpty(topics)|| topics.size() < 2) {
			map.put("error","交易logs数据不存在");
			return map;
		}
		String contract = hashDataJson.getString("to").toLowerCase();
		HashMap<String,BigDecimal> contractMap = new HashMap<>();
		for(Object json:logsArray){
			JSONObject dataJson = (JSONObject) json;
			EthSyncData ethSyncData = new EthSyncData();
			JSONArray topicsArray = dataJson.getJSONArray("topics");
			BigDecimal amount=new BigDecimal(EthUtils.decodeHex(logs.getString("data")));
			amount=amount.divide(new BigDecimal(Math.pow(10, unit)), 8, BigDecimal.ROUND_DOWN);
			ethSyncData.setBlockNumber(EthUtils.decodeHex(hashDataJson.getString("blockNumber")));
			ethSyncData.setTxid(hash);
			ethSyncData.setAmount(amount);
			if(topicsArray.size()>2){//说明是合约交易
				ethSyncData.setToAddress(EthUtils.getridof_zero_address(topicsArray.getString(2)).toLowerCase());
				ethSyncData.setFromAddress(EthUtils.getridof_zero_address(topicsArray.getString(1)).toLowerCase());
				ethSyncData.setContract(contract);
				ethSyncData.setStatus(true);
				contractMap.put(amount.stripTrailingZeros().toPlainString(),amount);
				list.add(ethSyncData);
			}
		}
		map.put("list",list);
		return map;
	}

	public String getNftHoldAddress(String tokenId,String contractAddress){
		HashMap<String, Object> parmas = new HashMap<String, Object>();

		String method = "0x6352211e";
		parmas.put("to", contractAddress);
		parmas.put("data", method + EthUtils.fill_zero(EthUtils.encodeDec(new BigDecimal(tokenId))));
		JSONObject json = newDoRequest(METHOD_ETH_CALL, "MBC",parmas, LATEST);
		if (EthUtils.isError(json)) {
			logger.error("{}获取tokenId持有者失败:{}",tokenId, json.get("error"));
			return null;
		}
		return  EthUtils.getridof_zero_address(json.getString(RESULT));
	}

	public JSONObject newDoRequest(String method,String chainName, Object... params) {
		JSONObject param = new JSONObject();
		param.put("id", System.currentTimeMillis() + "");
		param.put("jsonrpc", "2.0");
		param.put("method", method);
		param.put("id", "1");
		if (params != null) {
			param.put("params", params);
		}
		String resp = "";
		Map<String, String> headers = new HashMap<String, String>(2);
		try {
			if(chainName.equalsIgnoreCase(Enum.transfer_type.eth.toString())){
				resp = HttpUtils.jsonPost(nodeUrl.getEthChainUrl(), headers, param.toJSONString());
			}else {
				resp = HttpUtils.jsonPost(nodeUrl.getChainUrl(), headers, param.toJSONString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			resp = "{}";
			logger.error("=====>调用钱包失败：", e);
		}
//		logger.info("=====>调用钱包返回： " + resp);
		// 插入请求日志
		return JSONObject.parseObject(resp);
	}




	public JSONObject doRequest(String method, Chain chain, Object... params) {
		JSONObject param = new JSONObject();
		param.put("id", System.currentTimeMillis() + "");
		param.put("jsonrpc", "2.0");
		param.put("method", method);
		param.put("id", "1");
		if (params != null) {
			param.put("params", params);
		}
		String creb = Base64.encodeBase64String((chain.getUserName() + ":" + chain.getPassword()).getBytes());
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Authorization", "Basic " + creb);
		String resp = "";
		try {
			resp = HttpUtils.jsonPost(chain.getChainNodeUrl(), headers, param.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
			resp = "{}";

			logger.error("=====>调用钱包失败：", e);
		}
		logger.info("=====>调用钱包返回： " + resp);
		// 插入请求日志
		return JSONObject.parseObject(resp);
	}

	/**
	 * 铸造nft
	 */
	@Override
	public  String mintToken(String toAddress,String fromAddress,String contractAddress,String privateKey,Integer tokenId,String images,BigDecimal price,int unit,Long chainId) {
		try {
//			price = new BigDecimal("1");
//			unit=18;
//			tokenId = 9;
//		    toAddress = "0x989523D2B03dd3D98787fe70Bb1aC6102D122B02";
//		    fromAddress = "0x989523D2B03dd3D98787fe70Bb1aC6102D122B02";
//		    contractAddress="0xc60B69985Db9bb6D16b10c592a926c7f40b9CC6C";
//		    privateKey = "2fbbdfe526c12ea8f3bedb95a46f5b76229074feb415394ba496df77ee6cc4cf";
//		    chainId = 77777l;
//		    images = "http://ipfs.weirui0755.com//ipfs/QmTevDiU36XFVydM4R99wt4TJkTYyrA5F86Y8DNywbxzoi";

			Web3j web3j=Web3j.build(new HttpService(nodeUrl.getChainUrl()));
			BigInteger nonce;
			EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.PENDING).send();
			if (ethGetTransactionCount == null) {
				return null;
			}
			nonce = ethGetTransactionCount.getTransactionCount();
			while (!redis.setBizOperation("nonce:"+fromAddress+":"+nonce,20l)){
				ethGetTransactionCount = web3j.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.PENDING).send();
				if (ethGetTransactionCount == null) {
					return null;
				}
				nonce = ethGetTransactionCount.getTransactionCount();
			}
			// 手续费
			BigInteger gasPrice;
			EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
			if (ethGasPrice == null) {
				return null;
			}
			gasPrice = ethGasPrice.getGasPrice();
			// 注意手续费的设置，这块很容易遇到问题
			BigInteger gasLimit = BigInteger.valueOf(3000000l);
			BigInteger value = BigInteger.ZERO;
			//token转账参数
			String methodName = "mint";
			List<Type> inputParameters = new ArrayList<>();
			List<TypeReference<?>> outputParameters = new ArrayList<>();
			Address tAddress = new Address(toAddress);
			Uint256 tokenIds = new Uint256(tokenId);
			Uint256 tokenValue = new Uint256(price.multiply(BigDecimal.TEN.pow(unit)).toBigInteger());
			Utf8String tokenImage = new Utf8String(images);
			inputParameters.add(tAddress);
			inputParameters.add(tokenIds);
			inputParameters.add(tokenImage);
			inputParameters.add(tokenValue);
			TypeReference<Bool> typeReference = new TypeReference<Bool>() {
			};
			outputParameters.add(typeReference);
			Function function = new Function(methodName, inputParameters, outputParameters);
			// 转账的凭证，需要传入私钥
			Credentials credentials = Credentials.create(privateKey);
			String encodedFunction = FunctionEncoder.encode(function);
			RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, contractAddress,
					encodedFunction);
			// 进行签名操作
			byte[] signMessage = TransactionEncoder.signMessage(rawTransaction,chainId, credentials);
			String signedData = Numeric.toHexString(signMessage);
			if (signedData != null) {
				EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(signedData).send();
				String hash =ethSendTransaction.getTransactionHash();
				if(hash==null){
                    redis.del("nonce:"+fromAddress+":"+nonce);
                }else{
				    return hash;
                }
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("铸造异常",e);
		}
		return null;
	}

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
	@Override
	public  String safeTransferFrom(String fromAddress, String toAddress, String contractAddress, String privateKey, Integer tokenId, Long chainId) {
		try {
			Web3j web3j=Web3j.build(new HttpService(nodeUrl.getChainUrl()));
			BigInteger nonce;
			EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.PENDING).send();
			if (ethGetTransactionCount == null) {
				return null;
			}
			nonce = ethGetTransactionCount.getTransactionCount();
			while (!redis.setBizOperation("nonce:"+fromAddress+":"+nonce,20l)){
				ethGetTransactionCount = web3j.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.PENDING).send();
				if (ethGetTransactionCount == null) {
					return null;
				}
				nonce = ethGetTransactionCount.getTransactionCount();
			}
			// 手续费
			BigInteger gasPrice;
			EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
			if (ethGasPrice == null) {
				return null;
			}
			gasPrice = ethGasPrice.getGasPrice();
			// 注意手续费的设置，这块很容易遇到问题
			BigInteger gasLimit = BigInteger.valueOf(3000000l);
			BigInteger value = BigInteger.ZERO;
			//token转账参数
			String methodName = "safeTransferFrom";
			List<Type> inputParameters = new ArrayList<>();
			List<TypeReference<?>> outputParameters = new ArrayList<>();
			Address fAddress = new Address(fromAddress);
			Address tAddress = new Address(toAddress);
			Uint256 tokenIds = new Uint256(tokenId);
			inputParameters.add(fAddress);
			inputParameters.add(tAddress);
			inputParameters.add(tokenIds);
			TypeReference<Bool> typeReference = new TypeReference<Bool>() {
			};
			outputParameters.add(typeReference);
			Function function = new Function(methodName, inputParameters, outputParameters);

			// 转账的凭证，需要传入私钥
			Credentials credentials = Credentials.create(privateKey);
			String encodedFunction = FunctionEncoder.encode(function);
			RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, contractAddress,
					encodedFunction);
			// 进行签名操作
			byte[] signMessage = TransactionEncoder.signMessage(rawTransaction,chainId, credentials);
			String signedData = Numeric.toHexString(signMessage);
			if (signedData != null) {
				EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(signedData).send();
				String hash = ethSendTransaction.getTransactionHash();
                if(hash==null){
                    redis.del("nonce:"+fromAddress+":"+nonce);
                }else{
                   return hash;
                }
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("转换铸造异常",e);
		}
		return null;
	}

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
	@Override
	public HashMap<String,String> transferFrom(String castAddress,String fromAddress, String toAddress, String contractAddress, String privateKey, Integer tokenId, Long chainId) {
		HashMap<String,String> map = new HashMap<>();
		try {
			Web3j web3j=Web3j.build(new HttpService(nodeUrl.getChainUrl()));
			BigInteger nonce;
			EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(castAddress, DefaultBlockParameterName.PENDING).send();
			if (ethGetTransactionCount == null) {
				return null;
			}
			nonce = ethGetTransactionCount.getTransactionCount();
			while (!redis.setBizOperation("nonce:"+castAddress+":"+nonce,20l)){
				ethGetTransactionCount = web3j.ethGetTransactionCount(castAddress, DefaultBlockParameterName.PENDING).send();
				if (ethGetTransactionCount == null) {
					return null;
				}
				nonce = ethGetTransactionCount.getTransactionCount();
			}
			BigInteger nonce1= this.getTransactionCount(castAddress,nodeUrl.getChainUrl());
			logger.info("转移NFT所有权(最高权限)方法nonce值"+nonce+"===="+nonce1);
			// 手续费
			BigInteger gasPrice;
			EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
			if (ethGasPrice == null) {
				return null;
			}
			gasPrice = ethGasPrice.getGasPrice();
			// 注意手续费的设置，这块很容易遇到问题
			BigInteger gasLimit = BigInteger.valueOf(3000000l);
			BigInteger value = BigInteger.ZERO;
			//token转账参数
			String methodName = "transferFrom";
			List<Type> inputParameters = new ArrayList<>();
			List<TypeReference<?>> outputParameters = new ArrayList<>();
			Address fAddress = new Address(fromAddress);
			Address tAddress = new Address(toAddress);
			Uint256 tokenIds = new Uint256(tokenId);
			inputParameters.add(fAddress);
			inputParameters.add(tAddress);
			inputParameters.add(tokenIds);
			TypeReference<Bool> typeReference = new TypeReference<Bool>() {
			};
			outputParameters.add(typeReference);
			Function function = new Function(methodName, inputParameters, outputParameters);

			// 转账的凭证，需要传入私钥
			Credentials credentials = Credentials.create(privateKey);
			String encodedFunction = FunctionEncoder.encode(function);
			RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, contractAddress,
					encodedFunction);
			// 进行签名操作
			byte[] signMessage = TransactionEncoder.signMessage(rawTransaction,chainId, credentials);
			String signedData = Numeric.toHexString(signMessage);
			if (signedData != null) {
				EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(signedData).send();
				String hash = ethSendTransaction.getTransactionHash();
				if(StringUtils.isNotBlank(hash)){
					map.put("hash",hash);
				}else{
					map.put("error",ethSendTransaction.getError().getMessage());
                    redis.del("nonce:"+fromAddress+":"+nonce);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("转换铸造异常",e);
		}
		return map;
	}

	@Override
	public String burn(String toAddress, String fromAddress, String contractAddress, String privateKey, Integer tokenId, Long chainId) {
		try {
			Web3j web3j=Web3j.build(new HttpService(nodeUrl.getChainUrl()));
			BigInteger nonce;
			EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.PENDING).send();
			if (ethGetTransactionCount == null) {
				return null;
			}
			nonce = ethGetTransactionCount.getTransactionCount();
			while (!redis.setBizOperation("nonce:"+fromAddress+":"+nonce,20l)){
				ethGetTransactionCount = web3j.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.PENDING).send();
				if (ethGetTransactionCount == null) {
					return null;
				}
				nonce = ethGetTransactionCount.getTransactionCount();
			}
			// 手续费
			BigInteger gasPrice;
			EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
			if (ethGasPrice == null) {
				return null;
			}
			gasPrice = ethGasPrice.getGasPrice();
			// 注意手续费的设置，这块很容易遇到问题
			BigInteger gasLimit = BigInteger.valueOf(3000000l);
			BigInteger value = BigInteger.ZERO;
			//token转账参数
			String methodName = "burn";
			List<Type> inputParameters = new ArrayList<>();
			List<TypeReference<?>> outputParameters = new ArrayList<>();
			Uint256 tokenIds = new Uint256(tokenId);
			inputParameters.add(tokenIds);
			TypeReference<Bool> typeReference = new TypeReference<Bool>() {
			};
			outputParameters.add(typeReference);
			Function function = new Function(methodName, inputParameters, outputParameters);

			// 转账的凭证，需要传入私钥
			Credentials credentials = Credentials.create(privateKey);
			String encodedFunction = FunctionEncoder.encode(function);
			RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, contractAddress,
					encodedFunction);
			// 进行签名操作
			byte[] signMessage = TransactionEncoder.signMessage(rawTransaction,chainId, credentials);
			String signedData = Numeric.toHexString(signMessage);
			if (signedData != null) {
				EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(signedData).send();
				String hash = ethSendTransaction.getTransactionHash();
                if(hash==null){
                    redis.del("nonce:"+fromAddress+":"+nonce);
                }else{
                    return hash;
                }
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("转换铸造异常",e);
		}
		return null;
	}


	@Override
	public String burnAndRecast(String toAddress, String fromAddress, String contractAddress, String privateKey, Integer tokenId, Long chainId,BigDecimal castNum) {
		try {
			Web3j web3j=Web3j.build(new HttpService(nodeUrl.getChainUrl()));
			BigInteger nonce;
			EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.PENDING).send();
			if (ethGetTransactionCount == null) {
				return null;
			}
			nonce = ethGetTransactionCount.getTransactionCount();
			while (!redis.setBizOperation("nonce:"+fromAddress+":"+nonce,20l)){
				ethGetTransactionCount = web3j.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.PENDING).send();
				if (ethGetTransactionCount == null) {
					return null;
				}
				nonce = ethGetTransactionCount.getTransactionCount();
			}
			// 手续费
			BigInteger gasPrice;
			EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
			if (ethGasPrice == null) {
				return null;
			}
			gasPrice = ethGasPrice.getGasPrice();
			// 注意手续费的设置，这块很容易遇到问题
			BigInteger gasLimit = BigInteger.valueOf(3000000l);
			BigInteger value = BigInteger.ZERO;
			//token转账参数
			String methodName = "burn";
			List<Type> inputParameters = new ArrayList<>();
			List<TypeReference<?>> outputParameters = new ArrayList<>();
			Uint256 tokenIds = new Uint256(tokenId);
			inputParameters.add(tokenIds);
			TypeReference<Bool> typeReference = new TypeReference<Bool>() {
			};
			outputParameters.add(typeReference);
			Function function = new Function(methodName, inputParameters, outputParameters);

			// 转账的凭证，需要传入私钥
			Credentials credentials = Credentials.create(privateKey);
			String encodedFunction = FunctionEncoder.encode(function);
			RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, contractAddress,
					encodedFunction);
			// 进行签名操作
			byte[] signMessage = TransactionEncoder.signMessage(rawTransaction,chainId, credentials);
			String signedData = Numeric.toHexString(signMessage);
			if (signedData != null) {
				EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(signedData).send();
				//验证哈希
				JSONObject json = getTransactionByHash(ethSendTransaction.getTransactionHash(), Enum.chain_name.mbc.getCode());
				Boolean rel = true;
				while(rel){
					if (json == null) {
						rel=true;
					}else {
						rel=false;
					}
				}
				if (json.containsKey("error")) {
					return null;
				}
				String  hash=mintToken(toAddress,fromAddress,contractAddress,privateKey,tokenId,"",castNum,18,chainId);
				hash=ethSendTransaction.getTransactionHash()+","+hash;
				return hash;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("转换铸造异常",e);
		}
		return null;
	}

	/**
	 *  共用合约（修改-铸造-转让-销毁-修改）VNFT toeknId方法类
	 * @param dto
	 * @return
	 */
	@Override
	public HashMap<String,String> holdNft(HoldNftDTO dto) {
		HashMap<String,String> map = new HashMap<>();
		try {
			Web3j web3j=Web3j.build(new HttpService(nodeUrl.getChainUrl()));
			BigInteger nonce;
			EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(dto.getCastAddress(), DefaultBlockParameterName.PENDING).send();
			if (ethGetTransactionCount == null) {
				map.put("error","调用holdNft错误，ethGetTransactionCount为null");
				return map;
			}
			nonce = ethGetTransactionCount.getTransactionCount();
			while (!redis.setBizOperation("nonce:"+dto.getCastAddress()+":"+nonce,20l)){
				ethGetTransactionCount = web3j.ethGetTransactionCount(dto.getCastAddress(), DefaultBlockParameterName.PENDING).send();
				if (ethGetTransactionCount == null) {
					map.put("error","调用holdNft错误，ethGetTransactionCount为null");
					return map;
				}
				nonce = ethGetTransactionCount.getTransactionCount();
			}
			// 手续费
			BigInteger gasPrice;
			EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
			if (ethGasPrice == null) {
				map.put("error","调用holdNft错误，ethGasPricet为null");
				return map;
			}
			gasPrice = ethGasPrice.getGasPrice();
			// 注意手续费的设置，这块很容易遇到问题
			BigInteger gasLimit = BigInteger.valueOf(3000000l);
			int unit = dto.getUnit()==null?18:dto.getUnit();
			//token转账参数
			String methodName = dto.getMethod();
			List<Type> inputParameters = new ArrayList<>();
			List<TypeReference<?>> outputParameters = new ArrayList<>();
			Uint256 sellTokenId;
			Uint256 buyTokenId;
			Uint256 exPrice;
			Uint256 exTokenID;
			Utf8String extokenURI;
			Utf8String selltokenURI;
			Utf8String buytokenURI;
			Address fAddress;
			Address toAddress;
			switch (dto.getMethod()){
				case "allHoldNft"://买卖双方都已拥有VNF
					sellTokenId = new Uint256(Integer.valueOf(dto.getSellTokenId()));
					buyTokenId = new Uint256(Integer.valueOf(dto.getBuyTokenId()));
					exPrice = new Uint256(dto.getExPrice().multiply(BigDecimal.TEN.pow(unit)).toBigInteger());
					exTokenID = new Uint256(Integer.valueOf(dto.getExTokenID()));
					extokenURI = new Utf8String(dto.getExtokenURI());
					selltokenURI = new Utf8String(dto.getSelltokenURI());
					buytokenURI = new Utf8String(dto.getBuytokenURI());
					inputParameters.add(sellTokenId);
					inputParameters.add(buyTokenId);
					inputParameters.add(exPrice);
					inputParameters.add(exTokenID);
					inputParameters.add(extokenURI);
					inputParameters.add(selltokenURI);
					inputParameters.add(buytokenURI);
					break;
				case "oneHoldNft":
					exPrice = new Uint256(dto.getExPrice().multiply(BigDecimal.TEN.pow(unit)).toBigInteger());
					exTokenID = new Uint256(Integer.valueOf(dto.getExTokenID()));
					extokenURI = new Utf8String(dto.getExtokenURI());
					fAddress = new Address(dto.getFromAddress());
					Uint256 type = new Uint256(dto.getType());
					if(dto.getType()==1){//说明买方要重新修改（销毁原来的。新铸造新的）
						buyTokenId = new Uint256(Integer.valueOf(dto.getBuyTokenId()));
						buytokenURI = new Utf8String(dto.getBuytokenURI());
						inputParameters.add(buyTokenId);
						inputParameters.add(exPrice);
						inputParameters.add(exTokenID);
						inputParameters.add(extokenURI);
						inputParameters.add(buytokenURI);
						inputParameters.add(fAddress);
						inputParameters.add(type);
					}else if(dto.getType()==2 || dto.getType()==3){//卖方修改(减小),买方收到转移的（2.不用重新销毁铸造 3.重新铸造）
						sellTokenId = new Uint256(Integer.valueOf(dto.getSellTokenId()));
						selltokenURI = new Utf8String(dto.getSelltokenURI());
						inputParameters.add(sellTokenId);
						inputParameters.add(exPrice);
						inputParameters.add(exTokenID);
						inputParameters.add(extokenURI);
						inputParameters.add(selltokenURI);
						inputParameters.add(fAddress);
						inputParameters.add(type);
					}
					break;
				case "noOneHoldNft"://from转到to
					exPrice = new Uint256(dto.getExPrice().multiply(BigDecimal.TEN.pow(unit)).toBigInteger());
					exTokenID = new Uint256(Integer.valueOf(dto.getExTokenID()));
					extokenURI = new Utf8String(dto.getExtokenURI());
					fAddress = new Address(dto.getFromAddress());
					toAddress = new Address(dto.getToAddress());
					inputParameters.add(exPrice);
					inputParameters.add(exTokenID);
					inputParameters.add(extokenURI);
					inputParameters.add(fAddress);
					inputParameters.add(toAddress);
					break;

				case "resetTokenInfo"://from转到to
					exPrice = new Uint256(dto.getExPrice().multiply(BigDecimal.TEN.pow(unit)).toBigInteger());
					exTokenID = new Uint256(Integer.valueOf(dto.getExTokenID()));
					extokenURI = new Utf8String(dto.getExtokenURI());
					inputParameters.add(exTokenID);
					inputParameters.add(extokenURI);
					inputParameters.add(exPrice);
					break;
				default:
					logger.error("方法参数异常");
					map.put("error","方法参数异常");
					return map;
			}
			TypeReference<Bool> typeReference = new TypeReference<Bool>() {
			};
			outputParameters.add(typeReference);
			Function function = new Function(methodName, inputParameters, outputParameters);
			// 转账的凭证，需要传入私钥
			Credentials credentials = Credentials.create(dto.getPrivateKey());
			String encodedFunction = FunctionEncoder.encode(function);
			RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, dto.getContractAddress(),
					encodedFunction);
			// 进行签名操作
			byte[] signMessage = TransactionEncoder.signMessage(rawTransaction,dto.getChainId(), credentials);
			String signedData = Numeric.toHexString(signMessage);
			if (signedData != null) {
				EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(signedData).send();
				String hash = ethSendTransaction.getTransactionHash();
				if(StringUtils.isNotBlank(hash)){
					map.put("hash",hash);
				}else{
					map.put("error",ethSendTransaction.getError().getMessage());
                    redis.del("nonce:"+dto.getCastAddress()+":"+nonce);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新铸造方法异常",e);
			map.put("error","调用holdNft错误");
		}
		return map;
	}
}
