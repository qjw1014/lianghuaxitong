package com.wallet.eth.common;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.wallet.common.utils.StringHelper;

/**
 * utls
 *
 * @Autor Shadow 2020/10/22
 * @Date 2020-10-22 16:05:10
 */
public class EthUtils {
	public final static String UNITSTR = "1000000000000000000";// 默认以太坊
	public static String encodeDec(BigDecimal num) {
		String strPart = StringHelper.toUnsignedString(num, 16);
		return "0x" + strPart;
	}
	public static BigInteger decodeHex(String hex_num) {
		hex_num = hex_num.replace("0x", "");
		if ("".equals(hex_num)) {
			return new BigInteger("0");
		}
//		System.out.println(new String(hex_num.getBytes(),16));
		BigInteger bigInterger = new BigInteger(hex_num, 16);
		return bigInterger;
	}

	public static boolean isError(JSONObject json) {
		if (json == null || (StringUtils.isNotEmpty(json.getString("error")) && json.get("error") != "null")) {
			return true;
		}
		return false;
	}

	public static BigDecimal realValue(String input, String unit) {
		BigDecimal bigDecimal = new BigDecimal(input);
		if (StringHelper.isEmpty(unit)) {
			unit = UNITSTR;
		}
		bigDecimal = bigDecimal.divide(new BigDecimal(unit), 8, BigDecimal.ROUND_HALF_DOWN);
		return bigDecimal;
	}
	public static String hexStringToString(String s) {
		if (s == null || s.equals("")) {
			return null;
		}
		s = s.replace(" ", "");
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "gbk");
			new String();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}
	public static String fill_zero(String input) {
		String str = input.replace("0x", "");
		int strLen = input.length();
		StringBuffer sb = null;
		while (strLen < 64) {
			sb = new StringBuffer();
			sb.append("0").append(str);// 左补0
			str = sb.toString();
			strLen = str.length();
		}
		return str;
	}
    public static String getAddressFromeInpuData(String inputData){
        if(StringUtils.isEmpty(inputData)){
            return null;
        }
        try{
        	String toAddress = inputData.substring(10,74);
        	toAddress = "0x"+toAddress.substring(toAddress.length()-40,toAddress.length());
            return toAddress;
        }catch (Exception e){
            return null;
        }

    }
    public static BigDecimal getValueFromData(String data,String decimal){
        if(StringUtils.isEmpty(data)){
            return BigDecimal.ZERO;
        }
        try{
            String  str = data.substring(74,138);
            long a = new BigInteger(str,16).longValue();
            BigDecimal val=new BigDecimal(a);
            return val.divide(new BigDecimal(decimal), 8, BigDecimal.ROUND_DOWN);
            		
        }catch (Exception e){
        	e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }
	public static boolean checkWalletAddress(String address) {
		address = address.replace(" ", "");
		// 判断地址
		if (StringHelper.isEmpty(address)) {
			return false;
		}
		if (address.length() != 42) {
			return false;
		}
		if (!address.startsWith("0x")) {
			return false;
		}
		return true;
	}
	 public static String getridof_zero_address(String input) {
			if (input.length() < 40) {
				return null;
			}
			String str = input.substring(input.length() - 40, input.length());
			return "0x" + str;
	 }

	/**
	 * 转换tokenId,十进制转为十六进制数据
	 * @return
	 */
	public static String tenTransfer(Integer tokenId){
		 return fill_zero(String.format("%08x", tokenId));
	 }

	/**
	 * 图片对象没添加参数
	 * @param input
	 * @return
	 */
	public static String getTokenImage(String input){
		if(StringUtils.isBlank(input)){
			return null;
		}
		try{
			String  str = input.split("0000000004e")[1];
			String name = hexStringToString(str);
			return name.trim();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		Integer tokenId = 379;
		String imageUrl = "000000000000000000000000000000000000000000000000000000000000009c";
		System.out.println(tenTransfer(tokenId));
		//1000000000  55597
	}

}
