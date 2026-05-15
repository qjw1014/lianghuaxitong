package com.wallet.tron.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wallet.common.utils.JsonUtils;
import org.apache.commons.collections4.map.LinkedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.tron.core.capsule.TransactionCapsule;
import org.tron.core.services.http.JsonFormat;
import org.tron.core.services.http.Util;
import org.tron.protos.Protocol;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wallet.common.constant.Constants;
import com.wallet.common.utils.http.HttpUtils;
import com.wallet.tron.common.ByteArray;
import com.wallet.tron.common.TronUtils;
import com.wallet.tron.domain.GetTransactionSign;
import com.wallet.tron.domain.TriggerSmartContract;
import com.wallet.tron.domain.TronSyncData;
import com.wallet.tron.service.ITronService;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Service
public class TronServiceImpl implements ITronService{
	private final static String METHOD_TRON_GETTRANSACTIONINFO = "/wallet/gettransactioninfobyid";
	private final static String METHOD_TRON_TRIGGERSMARTCONTRACT = "/wallet/triggersmartcontract";
	private final static String METHOD_TRON_GETTRANSACTIONSIGN = "/wallet/gettransactionsign";
	private final static String METHOD_TRON_BROADCASTTRANSACTION = "/wallet/broadcasttransaction";
	private final static String METHOD_TRON_GETBLOCKBYLIMITNEXT = "/wallet/getblockbylimitnext";
	private final static String METHOD_TRON_GETACCOUNT = "/wallet/getaccount";// 获取tron账户
	private final static String METHOD_TRON_GETBLOCKBYNUM = "/wallet/getblockbynum";
	private final static String METHOD_TRON_GETNODEINFO = "/wallet/getnodeinfo";

	private static final Logger logger = LoggerFactory.getLogger(TronServiceImpl.class);


	/**
	 * 根据HASH 获取
	 *
	 * @return
	 */
	public JSONObject getTransactionInfoById(String url, String hashId) {
		url = url + METHOD_TRON_GETTRANSACTIONINFO;
		JSONObject param = new JSONObject();
		param.put("value", hashId);
		String tran = HttpUtils.jsonPost(url, param.toJSONString());
		return JSONObject.parseObject(tran);
	}
	
	public int getBlockNum(String url) {
		url = url   + METHOD_TRON_GETNODEINFO;

		String tran = HttpUtils.sendGet(url, Constants.GBK);
		JSONObject param = JSONObject.parseObject(tran);
		String blockStr = param.getString("block");

		return Integer.valueOf(blockStr.split(",")[0].split(":")[1]);
	}

	public JSONObject getblockbylimitnext(String url, Integer startNum, Integer endNum) {
		url = url + METHOD_TRON_GETBLOCKBYLIMITNEXT;
		JSONObject param = new JSONObject();
		param.put("startNum", startNum);
		param.put("endNum", endNum);
		String tran = HttpUtils.jsonPost(url, param.toJSONString());
		return JSONObject.parseObject(tran);

	}

	public JSONObject getTransactionInfoByBlockNum(String url, Integer num) {
		url = url + METHOD_TRON_GETBLOCKBYLIMITNEXT;
		JSONObject param = new JSONObject();
		param.put("num", num);
		String tran = HttpUtils.jsonPost(url, param.toJSONString());
		return JSONObject.parseObject(tran);

	}

	public TriggerSmartContract.Result triggerSmartContract(String url, TriggerSmartContract.Param param) {
		url = url + METHOD_TRON_TRIGGERSMARTCONTRACT;

		String tran = HttpUtils.jsonPost(url, JSON.toJSONString(param));
		return JSON.parseObject(tran, TriggerSmartContract.Result.class);
	}

	private String signTransaction(String trans, String privateKey) throws Exception {
		String transactionStr = "{\"transaction\":" + trans + ",\"privateKey\":\"" + privateKey + "\"}";
		com.alibaba.fastjson.JSONObject input = com.alibaba.fastjson.JSONObject.parseObject(transactionStr);
		String strTransaction = input.getJSONObject("transaction").toJSONString();
		Protocol.Transaction transaction = Util.packTransaction(strTransaction);
		com.alibaba.fastjson.JSONObject jsonTransaction = com.alibaba.fastjson.JSONObject
				.parseObject(JsonFormat.printToString(transaction));
		input.put("transaction", jsonTransaction);
		Protocol.TransactionSign.Builder build = Protocol.TransactionSign.newBuilder();
		JsonFormat.merge(input.toJSONString(), build);
		TransactionCapsule reply = getTransactionSign(build.build());
		return Util.printTransaction(reply.getInstance());
	}

	public static TransactionCapsule getTransactionSign(Protocol.TransactionSign transactionSign) {
		byte[] privateKey = transactionSign.getPrivateKey().toByteArray();
		TransactionCapsule trx = new TransactionCapsule(transactionSign.getTransaction());
		
		trx.sign(privateKey);
		return trx;
	}

	public JSONObject getTransactionSign(String url, GetTransactionSign.Param signParam) {
		url = url + METHOD_TRON_GETTRANSACTIONSIGN;

		String tran = HttpUtils.jsonPost(url, JSON.toJSONString(signParam));
		return JSONObject.parseObject(tran);
	}

	public JSONObject broadcastTransaction(String url, String dt) {
		url = url + METHOD_TRON_BROADCASTTRANSACTION;

		String tran = HttpUtils.jsonPost(url, dt);
		return JSONObject.parseObject(tran);
	}

	public TronSyncData getTransactionToken(String url, String hash, int unit) {
		JSONObject json = getTransactionInfoById(url, hash);
		TronSyncData tronSyncData = new TronSyncData();
		tronSyncData.setTxid(hash);
		tronSyncData.setStatus(false);
		if (json == null || json.isEmpty()) {
			tronSyncData.setRemarks("交易不存在");
			return tronSyncData;
		}
		JSONObject receipt = json.getJSONObject("receipt");
		// System.out.println(receipt);
		if (!"SUCCESS".equals(receipt.getString("result"))) {
			tronSyncData.setRemarks("交易失败");
			 System.out.println("哈希状态是============================================"+receipt.getString("result"));
			return tronSyncData;
		}
		JSONArray jsonArray = json.getJSONArray("log");
		if (CollectionUtils.isEmpty(jsonArray)) {
			tronSyncData.setRemarks("交易数据不存在");
			return tronSyncData;

		}
		tronSyncData.setBlockNumber(json.getIntValue("blockNumber"));
		for(Object array:jsonArray){
			JSONObject data = (JSONObject) array;
			if(!data.containsKey("data")){
				continue;
			}
			tronSyncData.setContract(TronUtils.fromHexAddress("41"+data.getString("address")));
			JSONArray topics = data.getJSONArray("topics");
			if(topics.size()>2){
				tronSyncData.setFromAddress(TronUtils.topics(topics.getString(1)));
				tronSyncData.setToAddress(TronUtils.topics(topics.getString(2)));
				try {
					tronSyncData.setAmount(TronUtils.amout(data.getString("data"), unit));
				}catch (Exception e){
					e.printStackTrace();
					tronSyncData.setAmount(TronUtils.hex16To10(data.getString("data"), unit));
				}
				tronSyncData.setStatus(true);
				BigDecimal fee =json.containsKey("fee")?json.getBigDecimal("fee"):BigDecimal.ZERO;
				tronSyncData.setFee(fee.divide(BigDecimal.valueOf(Math.pow(10,unit))));
				tronSyncData.setBlockTime(new Date(json.getLong("blockTimeStamp")));
				return tronSyncData;
			}
		}
		return null;
	}

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
			String amount, String toAddress, String remark) {
		// System.out.println("contract:" + contract);
		// System.out.println("toAddress" + toAddress);
		// System.out.println("remark" + remark);
		// System.out.println("amount" + amount);
		try {
			String hexFromAddress = TronUtils.castHexAddress(fromAddress);
			String hexToAddress = TronUtils.castHexAddress(toAddress);
			String hexContract = TronUtils.castHexAddress(contract);

			if (remark == null) {
				remark = "";
			}
			TriggerSmartContract.Param param = new TriggerSmartContract.Param();
			param.setOwner_address(hexFromAddress);
			param.setContract_address(hexContract);
			param.setFee_limit(10000000L);
			param.setFunction_selector("transfer(address,uint256)");
			String addressParam = TronUtils.addZero(hexToAddress, 64);
			String amountParam = TronUtils.addZero(
					new BigDecimal(amount).multiply(new BigDecimal("10").pow(6)).toBigInteger().toString(16), 64);
			remark = TronUtils.addZero(remark,64);
			param.setParameter(addressParam + amountParam+remark);
			param.setParameter(addressParam + amountParam);
			// System.out.println("创建交易参数:" + JSONObject.toJSONString(param));
			TriggerSmartContract.Result obj = triggerSmartContract(url, param);
			obj.getTransaction().getRaw_data().put("data", ByteArray.toHexString(remark.getBytes()));
			// System.out.println("创建交易结果:" + JSONObject.toJSONString(obj));
			if (!obj.isSuccess()) {
				logger.error("创建交易失败");
				return null;
			}

			// System.out.println(JSONObject.toJSONString(obj.getTransaction()));
			String dt = signTransaction(JSONObject.toJSONString(obj.getTransaction()), privateKey);
			
			// System.out.println("签名交易结果:" + dt);
			
			// 广播交易
			if (dt != null) {

				JSONObject rea = broadcastTransaction(url, dt);
				// System.out.println("广播交易结果:" + JSONObject.toJSONString(rea));
				// System.out.println(JSONObject.toJSONString(rea));
				if (rea != null) {
					Object result = rea.get("result");
					if (result instanceof Boolean) {
						if ((boolean) result) {
							return (String) rea.get("txid");
						}
					}
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
			logger.error(t.getMessage(), t);
		}
		return null;
	}

	/**
	 * 查询tron币数量
	 *
	 * @param address
	 * @return
	 */
	public BigDecimal balanceOfTron(String url, String address) {
		final BigDecimal decimal = new BigDecimal("1000000");
		final int accuracy = 6;// 六位小数;
		// 通过http接口查询
		url = url + METHOD_TRON_GETACCOUNT;
		JSONObject param = new JSONObject();
		param.put("address", TronUtils.castHexAddress(address));
		JSONObject obj = JSONObject.parseObject(HttpUtils.jsonPost(url, param.toJSONString()));
		// System.out.println(obj.toString());
		if (obj != null || obj.size() != 0) {
			BigInteger balance = obj.getBigInteger("balance");
			return new BigDecimal(balance).divide(decimal, accuracy, RoundingMode.FLOOR);
		}
		return BigDecimal.ZERO;
	}

	/**
	 * 查询合约余额
	 *
	 * @param contract 合约地址
	 * @param address  查询地址
	 * @param accuracy 代币合约精度
	 * @return
	 */
	public BigDecimal balanceOfTrc(String url, String contract, String address, int accuracy) {
		// 按照精度补位
		StringBuffer wei = new StringBuffer();
		wei.append("1");
		for (int i = 0; i < accuracy; i++) {
			wei.append("0");
		}
		final BigDecimal decimal = new BigDecimal(wei.toString());
		String hexAddress = address;
		if (address.startsWith("T")) {
			hexAddress = TronUtils.toHexAddress(address);
		}
		String hexContract = contract;
		if (contract.startsWith("T")) {
			hexContract = TronUtils.toHexAddress(contract);
		}
		TriggerSmartContract.Param param = new TriggerSmartContract.Param();
		param.setContract_address(hexContract);
		param.setOwner_address(hexAddress);
		param.setFunction_selector("balanceOf(address)");
		String addressParam = TronUtils.addZero(hexAddress.substring(2), 64);
		param.setParameter(addressParam);
		// 通过http查询合约余额
		url = url + METHOD_TRON_TRIGGERSMARTCONTRACT;
		String data = HttpUtils.jsonPost(url, JSONObject.toJSONString(param));
		TriggerSmartContract.Result result = JSON.parseObject(data, TriggerSmartContract.Result.class);
		if (result != null && result.isSuccess()) {
			String value = result.getConstantResult(0);
			// System.out.println(value);
			if (value != null) {
				return new BigDecimal(new BigInteger(value, 16)).divide(decimal, accuracy, RoundingMode.FLOOR);

			}
		}
		return BigDecimal.ZERO;
	}

	

	
	 /**
     *	 转账trx
     * @param fromAddress 从那个地址转出
     * @param privateKey 转出地址私钥
     * @param amount 转账金额
     * @param toAddress 转到那个地址
     * @return
     * @throws Throwable 
     */
    public String transferTrx(String url,String fromAddress, String privateKey, String amount, String toAddress) throws Throwable {
    		String hexFromAddress = TronUtils.toHexAddress(fromAddress);
        	String hexToAddress = TronUtils.toHexAddress(toAddress);
            BigInteger a = new BigInteger(amount);
            Map<String, Object> paramMap=new LinkedMap<>();
            paramMap.put("to_address", hexToAddress);
            paramMap.put("owner_address", hexFromAddress);
            paramMap.put("amount", a);
            // System.out.println(JSONObject.toJSONString(paramMap));
            String transaction= HttpUtils.jsonPost(url+"/wallet/createtransaction", JSONObject.toJSONString(paramMap));
            
           
            String dt=signTransaction(transaction,privateKey);
            logger.info("签名交易结果:" + dt);
            // System.out.println("签名交易结果:" + dt);
          //广播交易
            if (dt != null) {
                logger.info("广播交易参数:" + dt);
                //通过http广播交易
                JSONObject rea = JSON.parseObject(HttpUtils.jsonPost(url+"/wallet/broadcasttransaction", dt));
                // System.out.println(JSONObject.toJSONString(rea));
                logger.info("广播交易结果:" + JSONObject.toJSONString(rea));
                if (rea != null) {
                    Object result = rea.get("result");
                    if (result instanceof Boolean) {
                        if ((boolean) result) {
                            return (String) rea.get("txid");
                        }
                    }
                }
            }
		
    	
        return null;
        
    }

	public JSONObject getblockbynum(String url, Integer num) {
		url = url + METHOD_TRON_GETBLOCKBYNUM;
		JSONObject param = new JSONObject();
		param.put("num", num);
		String tran = HttpUtils.jsonPost(url, param.toJSONString());
		return JSONObject.parseObject(tran);

	}

	public List<TronSyncData> syncBlock(String url, Integer num,Map<String, Object> address) {
		List<TronSyncData> list = new ArrayList<TronSyncData>();
		JSONObject block = getblockbynum(url, num);
		// System.out.println(block);
		if (block.get("transactions") == null) {
			return null;
		}
		JSONArray transactions = block.getJSONArray("transactions");
		for (Object object2 : transactions) {
			try {
				JSONObject transactionsjson = (JSONObject) object2;

				JSONObject ret = (JSONObject) transactionsjson.getJSONArray("ret").get(0);
				// System.out.println(ret);
//    	     if(!ret.getString("contractRet").equalsIgnoreCase("SUCCESS")) {
//    	      break;
//    	     }
				String txid = transactionsjson.getString("txID");
				JSONArray contract = transactionsjson.getJSONObject("raw_data").getJSONArray("contract");
				for (Object object3 : contract) {
					JSONObject value=((JSONObject) object3).getJSONObject("parameter").getJSONObject("value");
					String contract_address = value.getString("contract_address");
					
					if (contract_address.equalsIgnoreCase("41a614f803b6fd780986a42c78ec9c7f77e6ded13c")) {
						String to_address = value.getString("data");
						to_address=TronUtils.fromHexAddress(41+to_address.substring(32, 72));
						if(address.get(to_address.toUpperCase())!=null) {
							TronSyncData tronSyncData = getTransactionToken(url, txid, 6);
							if (tronSyncData.getStatus()) {
								if (tronSyncData.getContract().equals("a614f803b6fd780986a42c78ec9c7f77e6ded13c")) {
									list.add(tronSyncData);
								}
							}
						}

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public static void main(String[] args) throws Throwable {
			Web3j web3j = Web3j.build(new HttpService("http://120.24.146.68:8545"));
		TronServiceImpl tronServiceImpl = new TronServiceImpl();

		TronSyncData tronSyncData=tronServiceImpl.getTransactionToken("https://api.trongrid.io","0073c7e532d94ab0e6d4ed974a014a98ec8dd4af79db0f9e208b20730ef07294",6);
//		JSONObject json=tronServiceImpl.getTransactionInfoById("https://api.trongrid.io","df1bceaa871a7c944b808c0541c4ae03a8226ac93ecf20e1272b3b97b6120f38");
//		 System.out.println(JSON.toJSON(tronSyncData));
//		JSONObject json=tronServiceImpl.getTransactionInfoById("https://api.trongrid.io","0073c7e532d94ab0e6d4ed974a014a98ec8dd4af79db0f9e208b20730ef07294");
//		  System.out.println("根据哈希查询交易"+json);
//		TronSyncData json1=tronServiceImpl.getTransactionToken("https://api.trongrid.io","df1bceaa871a7c944b808c0541c4ae03a8226ac93ecf20e1272b3b97b6120f38",6);
		  // System.out.println("转出地址"+json1.getToAddress()+"转入地址"+json1.getFromAddress());
//			// System.out.println(EthDeal(web3j, "0x2b06008076E06668667376F0928D1Eb22B6B4F6f", "0x747e8c7a78576558f40433566daf3612a732521b", "1.9"
//					, "0x33ca9497ac4cec25ca2de129396371ce36ec307e755bbd024beb577b59682943"));

//		String o=tronServiceImpl.sendTokenTransaction("https://api.trongrid.io", "TNzkDnyA7RzfyRLHzyd8JRRCWrWwRqpt1V", "TWgEuYRuXgwfDbcfzZsteu5w5dq1nFR35w", "689598ba433fe8cbd557b22369511a8ac37dceaae603e9a69af324fd3bed6816",
//				"1", "TGPirBtr42YvWtwDNgVEgv3YYhiCkE8hJq", "<a href='https://www.baidu.com/'>goo</a>");
//		String o=tronServiceImpl.sendTokenTransaction("https://api.trongrid.io", "TBKpjryxzHCGaVvMfHVB8u4Vm4qg5ax5zv", "TPrDh8x5jiGW9thVGKnE5TcCDMNffPayLd", "22c551bbfd44c8506f43bfd3708163db30baccf98f833b4117906ae23b231d2e",
//				"1", "TUe5i2VPMVeJ5bJ8nNdNp2iZSB3W4xHKmT", "<a href='https://www.baidu.com/'>goo</a>");
//		CoinWallet coinWallet = new CoinWallet();
//		coinWallet.setUrl("https://api.trongrid.io");
//		String url="https://pay.heepay.com/Phone/SDK/PayInit.aspx?version=1&pay_type=30&agent_id=2132668&agent_bill_id=A20211025190517I&pay_amt=0.1&notify_url=http://www.heepay.com&return_url=http://www.heepay.com&user_ip=192_168_564_123&agent_bill_time=20211025190517&goods_name=%E5%95%86%E5%93%81%E5%90%8D%E7%A7%B0&goods_num=1&remark=%E6%A0%87%E8%AF%86&goods_note=%E6%94%AF%E4%BB%98%E8%AF%B4%E6%98%8E&sign=39c8cc483a95bc424744f5fa0d31a569"; 
//		String tran = HttpUtils.sendGet(url, null);
//		// System.out.println(tran);
		//String txid=tronServiceImpl.transferTrx(coinWallet, "TWgEuYRuXgwfDbcfzZsteu5w5dq1nFR35w", "689598ba433fe8cbd557b22369511a8ac37dceaae603e9a69af324fd3bed6816", "1", "TTU7A9rK8ncRJmNTNqbL6H2N1oeMTqyTHq");
		//// System.out.println(txid);
//		BigDecimal accountBalance = tronServiceImpl.balanceOfTron(coinWallet, "TTU7A9rK8ncRJmNTNqbL6H2N1oeMTqyTHq");
//		// System.out.println(accountBalance);
		//// System.out.println(tronServiceImpl.syncBlock(coinWallet, 32393626));
		/*
		 * String a=
		 * "a9059cbb000000000000000000000000a6f61d0240a295b66ac7472bae9db2477ace438100000000000000000000000000000000000000000000000000000000020167e8";
		 * 
		 * // System.out.println(TronUtils.amout(a, 6));
		 */
		// coinWallet.setUrl("http://47.245.25.82:8545");
//		TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t

//		String txid=tronServiceImpl.sendTokenTransaction
//				(coinWallet, "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t", "TMXbAJk3WJh6xqpJPY4qva22FeqzK7ktpY",
//						"243b9b16a6d966399a25651a1b54f045e8bef8470cfb4891932cb3fb24c5a7f", "59.764705", "TTU7A9rK8ncRJmNTNqbL6H2N1oeMTqyTHq", "");
//		// System.out.println(txid);
//		// System.out.println(TronUtils.toHexAddress("TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t"));
//		 System.out.println(tronServiceImpl.sendTokenTransaction(coinWallet,"TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t", "TEL9epjR6c3wKgw7SBmo393Nynx5wd8GoK",
//    		    "4eb55ad6f4497525f5528e86a1f1ab14c9575468d83ad06ecfa0ee06c088babd", "0.933922", "TWgEuYRuXgwfDbcfzZsteu5w5dq1nFR35w", ""));
//		
		// // System.out.println(tronServiceImpl.getblockbylimitnext(coinWallet, 31386831,
		// 31386832));
		try {
//			TronSyncData tronSyncData = tronServiceImpl.getTransactionToken("https://api.trongrid.io", "2a9cf9f61f3858d70452914cbf712304bed7a5b5e41488f724d645952c42ca1a", 6);
//			// System.out.println(JsonUtils.objectToJson(tronSyncData));
//			JSONObject object = tronServiceImpl.getblockbynum("https://api.trongrid.io",36275426);
//			// System.out.println(object);

//			String to_address = "a9059cbb000000000000000000000000ebccaf9343f9a1d98139aba737286fe0b80b5cea0000000000000000000000000000000000000000000000000000000000007047";
//			to_address =TronUtils.fromHexAddress(41+to_address.substring(32, 72));
//			// System.out.println(to_address);
//			String to =TronUtils.fromHexAddress("41a614f803b6fd780986a42c78ec9c7f77e6ded13c");
//			 System.out.println(to);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
