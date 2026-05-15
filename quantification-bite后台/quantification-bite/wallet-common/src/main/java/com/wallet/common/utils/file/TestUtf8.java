package com.wallet.common.utils.file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
 
public class TestUtf8 {
 
	public static void main(String[] args) throws Exception {
		final String ENCODING = "UTF-8";
 
		// System.out.println("运行jdk环境的编码   =   " +  System.getProperty("file.encoding") );
		// System.out.println("字符串<->字节byte 编码   =   " +  ENCODING );
 
		/** 测试1，字符串 转 字节**/
		String testString = "34324";
		String byteHexStr = testStringToBytes(testString, ENCODING);
		// System.out.println(byteHexStr);
		byte[] basy=convertByteFromHex(byteHexStr);
		for (int i = 0; i < basy.length; i++) {
			System.out.print(basy[i]+" ");
		}
		/** 测试2，字节(16进制) 转 字符串 **/
		testBytesHexToString(byteHexStr, ENCODING);
	}
 
	public static String testStringToBytes(final String str, final String ENCODING) throws Exception {
		// System.out.println("//==========================================================");
		// System.out.println("// 测试1，字符串 转 字节，字符集 = " + ENCODING);
		// System.out.println("//       字符串 = " + str);
		// System.out.println("//       字符集编码 = " + ENCODING);
		// System.out.println("//==========================================================");
 
		byte[] bytes = str.getBytes(ENCODING);
		StringBuilder byteHexSB = new StringBuilder();
 
		MyTable table = new MyTable();
		table.addRow("byte.index", "10.value", "16.value", "2.value");
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
 
			int byteVal = b & 0xFF; // 一个byte占1个字节，0xFF是16进制也代表8位==1个字节，位于操作可以防止出现负数。
 
			String byteHexString = convertHexStringFromByte(b);
			String byteBinaryString = convertBinaryStringFromByte(b);
 
			byteHexSB.append(byteHexString);
 
			table.addRow(""+i, ""+byteVal, byteHexString, byteBinaryString);
		}
		table.printTable();
 
		// 字符串的字节数组，16进制表示。字符串中2位代表一个字节byte
		return byteHexSB.toString();
	}
 
	public static void testBytesHexToString(final String byteHexStr, final String ENCODING) throws Exception {
		// System.out.println("//==========================================================");
		// System.out.println("// 测试2，字节(16进制) 转 字符串，字符集 = " + ENCODING);
		// System.out.println("//       16进制byte = " + byteHexStr);
		// System.out.println("//       字符集编码 = " + ENCODING);
		// System.out.println("//==========================================================");
		byte[] convertByte= convertByteFromHex(byteHexStr);
		String newString = new String(convertByte, ENCODING);
		for (int i = 0; i < convertByte.length; i++) {
			System.out.print(convertByte[i]+" ");
		}
		// System.out.println("	转换为字符串：" + newString);
		// System.out.println();
	}
 
	/**
	 * @param byteHexString
	 * @return 将16进制记录下的byte流，翻译成byte[]数组
	 */
	public static byte[] convertByteFromHex(String byteHexString) {
		List<Byte> list = new ArrayList<>();
		int index = 0;
		while(index < byteHexString.length() - 1) {
			byte b = Integer.valueOf(byteHexString.substring(index, index + 2 ), 16).byteValue();
			list.add(b);
 
			index += 2;
		}
 
		byte[] ret = new byte[list.size()];
		IntStream.range(0, list.size()).forEach(i -> {
			ret[i] = list.get(i);
		});
		return ret;
	}
 
	/**
	 * @Description: 字节转成二进制字符串
	 */
	public static String convertBinaryStringFromByte(byte b) {
 
		int byteVal = b & 0xFF; // 一个byte占1个字节，0xFF是16进制也代表8位==1个字节，位于操作可以防止出现负数。
 
		String byteBinaryString = Integer.toBinaryString(byteVal);
		byteBinaryString = flushLeft('0', 16, byteBinaryString);
 
		return byteBinaryString;
	}
 
	/**
	 * @Description: 字节转成十六进制字符串
	 * @param b
	 */
	public static String convertHexStringFromByte(byte b) {
 
		int byteVal = b & 0xFF; // 一个byte占1个字节，0xFF是16进制也代表8位==1个字节，位于操作可以防止出现负数。
 
		String byteHexString = Integer.toHexString(byteVal); // byte整数值得16进制表达，不够2位前面补0
		byteHexString = flushLeft('0', 2, byteHexString);
 
		return byteHexString;
	}
 
	/**
	 * @Description: 左侧不足位 补位
	 * @param c 不足时补位的内容
	 * @param length 补位后的新字符串长度
	 * @param content 待补位的原始字符串
	 */
	private static String flushLeft(char c, int length, String content) {
		int contentLengh = content.length();
 
		if(contentLengh >= length) {
			return content;
		}
		StringBuilder sb = new StringBuilder();
		IntStream.range(0, length - contentLengh).forEach(i -> {
			sb.append(c);
		});
		sb.append(content);
		return sb.toString();
	}
 
 
	static class MyTable {
		private int columnSize;
		private List<List<String>> rows = new ArrayList<>();
		private List<Integer> columnSizeList = new ArrayList<>();
 
		public void addRow(String... column) {
			if (columnSize == 0) {
				columnSize = column.length;
			}
 
			if(columnSize != column.length) {
				throw new RuntimeException(String.format("列数不一致%d != %d ", columnSize, column.length));
			}
 
			List<String> row = Arrays.asList(column);
			rows.add(row);
 
			if(columnSizeList.isEmpty()) {
				IntStream.range(0, columnSize).forEach(i -> {
					int curSize = column[i].length();
					columnSizeList.add(curSize);
				});
			}else if(!columnSizeList.isEmpty()) { // 更新每列的宽度
				IntStream.range(0, columnSize).forEach(i -> {
					int curSize = column[i].length();
 
					if(columnSizeList.get(i) < curSize) {
						columnSizeList.set(i, curSize);
					}
				});
			}
		}
		public void printTable() {
			rows.stream().forEach( row -> {
				// System.out.println(rendderRow(row));
			});
			// System.out.println();
		}
		private String rendderRow(List<String> row) {
			int addSize = 2;
			StringBuilder sb = new StringBuilder();
 
			sb.append("	|");
 
			IntStream.range(0, columnSize).forEach(i -> {
				String column = row.get(i);
				String newColumnContent = flushLeft(' ', addSize + columnSizeList.get(i), column);
				sb.append(newColumnContent)
					.append(" |" );
			});
 
			return sb.toString();
		}
	}
}
