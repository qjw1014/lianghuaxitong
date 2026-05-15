package com.wallet.tron.common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * utls
 *
 * @Autor Shadow 2020/10/22
 * @Date 2020-10-22 16:05:10
 */
public class TronUtils {
	static int ADDRESS_SIZE = 21;
	private static byte addressPreFixByte = (byte) 0x41; // 41 + address (byte) 0xa0; //a0 + address

	public static String toHexAddress(String tAddress) {

		return ByteArray.toHexString(decodeFromBase58Check(tAddress));
	}

	public static BigDecimal amout(String date, int t) {
		long dec_num = Long.parseLong(date, 16);
		BigDecimal val = new BigDecimal(dec_num);
		return val.divide(new BigDecimal(Math.pow(10, t)), 6, BigDecimal.ROUND_DOWN);
	}

	

	private static byte[] decodeFromBase58Check(String addressBase58) {
		if (StringUtils.isEmpty(addressBase58)) {
			return null;
		}
		byte[] address = decode58Check(addressBase58);
		if (!addressValid(address)) {
			return null;
		}
		return address;
	}

	private static byte[] decode58Check(String input) {
		byte[] decodeCheck = Base58.decode(input);
		if (decodeCheck.length <= 4) {
			return null;
		}
		byte[] decodeData = new byte[decodeCheck.length - 4];
		System.arraycopy(decodeCheck, 0, decodeData, 0, decodeData.length);
		byte[] hash0 = Sha256Hash.hash(true, decodeData);
		byte[] hash1 = Sha256Hash.hash(true, hash0);
		if (hash1[0] == decodeCheck[decodeData.length] && hash1[1] == decodeCheck[decodeData.length + 1]
				&& hash1[2] == decodeCheck[decodeData.length + 2] && hash1[3] == decodeCheck[decodeData.length + 3]) {
			return decodeData;
		}
		return null;
	}
	
	public static String encode58Check(byte[] input) {
	    byte[] hash0 = hash(input);
	    byte[] hash1 = hash(hash0);
	    byte[] inputCheck = new byte[input.length + 4];
	    System.arraycopy(input, 0, inputCheck, 0, input.length);
	    System.arraycopy(hash1, 0, inputCheck, input.length, 4);
	    return Base58.encode(inputCheck);
	  }
	private static byte[] hash(byte[] input) {
		return hash(input, 0, input.length);
	}

	private static byte[] hash(byte[] input, int offset, int length) {
		
			MessageDigest digest = newDigest();
			digest.update(input, offset, length);
			return digest.digest();
		
	}
	private static MessageDigest newDigest() {
		try {
			return MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e); // Can't happen.
		}
	}
	private static boolean addressValid(byte[] address) {
		if (ArrayUtils.isEmpty(address)) {
			return false;
		}
		if (address.length != ADDRESS_SIZE) {
			return false;
		}
		byte preFixbyte = address[0];
		return preFixbyte == getAddressPreFixByte();
		// Other rule;
	}
	public static String fromHexAddress(String address){
	       return encode58Check(ByteArray.fromHexString(address));
	    }
	public static String topics(String data) {
	     String hexAddress="41"+data.substring( data.length()-40, data.length());
	     return fromHexAddress(hexAddress);
	     
	    }
	private static byte getAddressPreFixByte() {
		return addressPreFixByte;
	}
	  /**
     * 补充0到64个字节
     *
     * @param dt
     * @return
     */
	public static String addZero(String dt, int length) {
        StringBuilder builder = new StringBuilder();
        final int count = length;
        int zeroAmount = count - dt.length();
        for (int i = 0; i < zeroAmount; i++) {
            builder.append("0");
        }
        builder.append(dt);
        return builder.toString();
    }

    

	public static String castHexAddress(String address) {
        if (address.startsWith("T")) {
            return TronUtils.toHexAddress(address);
        }
        return address;
    }

    public static BigDecimal hex16To10(String strHex,int t){
		BigInteger bigInteger = new BigInteger(strHex,16);
		BigDecimal val = new BigDecimal(bigInteger.toString());
		return val.divide(new BigDecimal(Math.pow(10, t)), 6, BigDecimal.ROUND_DOWN);
	}

	public static void main(String args[]) {
		String address="0000000000000000000000000000000000000000000000000000000000000000";
		 System.out.println(TronUtils.topics("41"+address));
//		// System.out.println(Long.parseLong("0000000000000000000000000000000000000000000000018a07babf82877dcc", 16));
		// System.out.println(hex16To10("0000000000000000000000000000000000000000000000018a07babf82877dcc",18));
	}

}
