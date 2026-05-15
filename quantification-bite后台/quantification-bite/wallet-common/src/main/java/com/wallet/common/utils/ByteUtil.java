package com.wallet.common.utils;

public class ByteUtil {

	public static String bytes2Hex(byte[] src) {
        if (src == null || src.length <= 0) {   
            return null;   
        } 
        
        char[] res = new char[src.length * 2]; // 每个byte对应两个字符
        final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        for (int i = 0, j = 0; i < src.length; i++) {
            res[j++] = hexDigits[src[i] >> 4 & 0x0f]; // 先存byte的高4位
            res[j++] = hexDigits[src[i] & 0x0f]; // 再存byte的低4位
        }

        return new String(res);
    }
	public static void main(String[] args) {
		byte[] b={(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff
				,(byte)0xff,(byte)0xff,(byte)0xff}; 
		String str= new String (b);
		// System.out.println(str);
	}
}
