package com.wallet.common.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {
	private static final char[] HEXES = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	private static final Pattern REPLACE_VARIABLE_PATTERN = Pattern.compile("\\$\\{\\s*(\\w|\\.|-|_|\\$)+\\s*\\}", 2);
	public static final String EMPTY_STRING = "";

	public static String trim(String value) {
		return value == null ? null : value.trim();
	}

	public static boolean isEmpty(String value) {
		int length;
		if ((value == null) || ((length = value.length()) == 0))
			return true;
		for (int index = 0; index < length; index++) {
			char ch = value.charAt(index);
			if ((ch != ' ') && (ch != ' ') && (!Character.isISOControl(ch))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isSuperEmpty(String value) {
		if (!isEmpty(value)) {
			if (value.equals("null")) {
				return true;
			}
		}
		return false;
	}

	

	public static void filterHTML(Appendable writer, String str) throws IOException {
		if (isEmpty(str)) {
			return;
		}

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!Character.isISOControl(ch)) {
				switch (ch) {
				case '"':
				case '&':
				case '\'':
				case '<':
				case '>':
					writer.append("&#");
					writer.append(Integer.toString(ch));
					writer.append(';');
					break;
				default:
					writer.append(ch);
				}
			}
		}
	}

	public static void filterQuoter(Appendable writer, String str) throws IOException {
		if (isEmpty(str)) {
			return;
		}

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch == '"') {
				writer.append('\\');
			}
			writer.append(ch);
		}
	}

	public static void filterSingleQuoter(Appendable writer, String str) throws IOException {
		if (isEmpty(str)) {
			return;
		}

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch == '\'') {
				writer.append('\\');
			}
			writer.append(ch);
		}
	}

	public static String digest(String content) throws Throwable {
		if (isEmpty(content)) {
			return content;
		}
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		byte[] ciphertext = digest.digest(content.getBytes());
		char[] chars = new char[ciphertext.length + ciphertext.length];
		int i = 0;
		for (byte element : ciphertext) {
			chars[(i++)] = HEXES[(element & 0xF)];
			chars[(i++)] = HEXES[(element >> 4 & 0xF)];
		}
		return new String(chars);
	}

	private static String[] SAFELESS = { "<script", // 需要拦截的JS字符关键字
			"</script", "<iframe", "</iframe", "<frame", "</frame", "set-cookie", "%3cscript", "%3c/script",
			"%3ciframe", "%3c/iframe", "%3cframe", "%3c/frame", "src=\"javascript:", "<body", "</body", "%3cbody",
			"%3c/body",
			// "<",
			// ">",
			// "</",
			// "/>",
			// "%3c",
			// "%3e",
			// "%3c/",
			// "/%3e"
	};

	public static boolean formatChikcZr(String content) {
		if (null != content && content.length() > 0) {
			for (String s : SAFELESS) {
				if (content.toLowerCase().contains(s)) {
					return false;
				}
			}
		}
		return true;
	}

	

	public static String truncation(String string, int maxLength) {
		if (isEmpty(string))
			return "";
		try {
			StringBuilder out = new StringBuilder();
			truncation(out, string, maxLength, null);
			return out.toString();
		} catch (IOException e) {
		}
		return "";
	}

	public static String truncation(String string, int maxLength, String replace) {
		if (isEmpty(string))
			return "";
		try {
			StringBuilder out = new StringBuilder();
			truncation(out, string, maxLength, replace);
			return out.toString();
		} catch (IOException e) {
		}
		return "";
	}

	public static void truncation(Appendable out, String string, int maxLength) throws IOException {
		truncation(out, string, maxLength, null);
	}

	public static void truncation(Appendable out, String string, int maxLength, String replace) throws IOException {
		if ((isEmpty(string)) || (maxLength <= 0)) {
			return;
		}
		if (isEmpty(replace)) {
			replace = "...";
		}
		int index = 0;
		int end = Math.min(string.length(), maxLength);
		for (; index < end; index++) {
			out.append(string.charAt(index));
		}
		if (string.length() > maxLength)
			out.append(replace);
	}

	/**
	 * 隐藏手机中四位
	 */
	public static void truncationMpno(Appendable out, String string) throws IOException {
		if ((isEmpty(string) || string.length() != 11)) {
			//判断是否是邮箱
			if(string.indexOf("@")!=-1) {
				String[] strs= string.split("@");
				String res = "";
				int len = strs[0].length();
				if(len <= 4) {
					res="****";
				}
				else{
					res = strs[0].substring(0,len-4) + "****";
				}
				out.append(res);
				return;
			}
			return;
		}
		String replace = "****";
		int index = 0;
		int end = Math.min(string.length(), 3);
		for (; index < end; index++) {
			out.append(string.charAt(index));
		}
		out.append(replace);
		out.append(string.substring(7, 11));
	}

	/**
	 * 隐藏手机中四位
	 */
	public static String truncation(String string) throws IOException {
		StringBuilder s = new StringBuilder();
		if ((isEmpty(string) || string.length() != 11)) {
			return "";
		}
		String replace = "****";
		int index = 0;
		int end = Math.min(string.length(), 3);
		for (; index < end; index++) {
			s.append(string.charAt(index));
		}
		s.append(replace);
		s.append(string.substring(7, 11));
		return s.toString();
	}

	/**
	 * 数值转中文大写
	 */
	public static String arabia2CN(String num) {
		for (int i = num.length() - 1; i >= 0; i--) {
			num = num.replaceAll(",", ""); // 替换tomoney()中的“,”

			num = num.replaceAll(" ", ""); // 替换tomoney()中的空格
		}
		num = num.replaceAll("￥", ""); // 替换掉可能出现的￥字符

		try {
			Double.parseDouble(num);
		} catch (Exception e) { // 验证输入的字符是否为数字
			return "请检查小写金额是否正确";
		}

		// ---字符处理完毕，开始转换，转换采用前后两部分分别转换---//
		String[] part = num.split("\\.");
		String newchar = "";
		// 小数点前进行转化
		for (int i = part[0].length() - 1; i >= 0; i--) {
			if (part[0].length() > 10) {
				return "位数过大，无法计算";
			} // 若数量超过拾亿单位，提示
			String tmpnewchar = "";
			char perchar = part[0].charAt(i);
			switch (perchar) {
			case '0':
				tmpnewchar = "零" + tmpnewchar;
				break;
			case '1':
				tmpnewchar = "壹" + tmpnewchar;
				break;
			case '2':
				tmpnewchar = "贰" + tmpnewchar;
				break;
			case '3':
				tmpnewchar = "叁" + tmpnewchar;
				break;
			case '4':
				tmpnewchar = "肆" + tmpnewchar;
				break;
			case '5':
				tmpnewchar = "伍" + tmpnewchar;
				break;
			case '6':
				tmpnewchar = "陆" + tmpnewchar;
				break;
			case '7':
				tmpnewchar = "柒" + tmpnewchar;
				break;
			case '8':
				tmpnewchar = "捌" + tmpnewchar;
				break;
			case '9':
				tmpnewchar = "玖" + tmpnewchar;
				break;
			}
			switch (part[0].length() - i - 1) {
			case 0:
				tmpnewchar = tmpnewchar + "元";
				break;
			case 1:
				if (perchar != 0)
					tmpnewchar = tmpnewchar + "拾";
				break;
			case 2:
				if (perchar != 0)
					tmpnewchar = tmpnewchar + "佰";
				break;
			case 3:
				if (perchar != 0)
					tmpnewchar = tmpnewchar + "仟";
				break;
			case 4:
				tmpnewchar = tmpnewchar + "万";
				break;
			case 5:
				if (perchar != 0)
					tmpnewchar = tmpnewchar + "拾";
				break;
			case 6:
				if (perchar != 0)
					tmpnewchar = tmpnewchar + "佰";
				break;
			case 7:
				if (perchar != 0)
					tmpnewchar = tmpnewchar + "仟";
				break;
			case 8:
				tmpnewchar = tmpnewchar + "亿";
				break;
			case 9:
				tmpnewchar = tmpnewchar + "拾";
				break;
			}
			newchar = tmpnewchar + newchar;
		}
		// 小数点之后进行转化

		if (num.indexOf(".") != -1) {
			if (part[1].length() > 2) {
				// alert("小数点之后只能保留两位,系统将自动截段");
				part[1] = part[1].substring(0, 2);
			}
			for (int i = 0; i < part[1].length(); i++) {
				String tmpnewchar = "";
				char perchar = part[1].charAt(i);
				switch (perchar) {
				case '0':
					tmpnewchar = "零" + tmpnewchar;
					break;
				case '1':
					tmpnewchar = "壹" + tmpnewchar;
					break;
				case '2':
					tmpnewchar = "贰" + tmpnewchar;
					break;
				case '3':
					tmpnewchar = "叁" + tmpnewchar;
					break;
				case '4':
					tmpnewchar = "肆" + tmpnewchar;
					break;
				case '5':
					tmpnewchar = "伍" + tmpnewchar;
					break;
				case '6':
					tmpnewchar = "陆" + tmpnewchar;
					break;
				case '7':
					tmpnewchar = "柒" + tmpnewchar;
					break;
				case '8':
					tmpnewchar = "捌" + tmpnewchar;
					break;
				case '9':
					tmpnewchar = "玖" + tmpnewchar;
					break;
				}
				if (i == 0) {
					tmpnewchar = tmpnewchar + "角";
				}
				if (i == 1) {
					tmpnewchar = tmpnewchar + "分";
				}
				newchar = newchar + tmpnewchar;
			}
		}
		// 替换所有无用汉字

		while (newchar.indexOf("零零") != -1)
			newchar = newchar.replaceAll("零零", "零");

		newchar = newchar.replaceAll("零亿", "亿");
		newchar = newchar.replaceAll("亿万", "亿");
		newchar = newchar.replaceAll("零万", "万");
		newchar = newchar.replaceAll("零佰", "");
		newchar = newchar.replaceAll("零元", "元");
		newchar = newchar.replaceAll("零角", "");
		newchar = newchar.replaceAll("零分", "");
		newchar = newchar.replaceAll("零拾", "零");
		newchar = newchar.replaceAll("零元", "元");

		if (newchar.charAt(newchar.length() - 1) == '元' || newchar.charAt(newchar.length() - 1) == '角') {
			newchar = newchar + "整";
		}
		return newchar;
	}
	
	final static char[] digits = {    
			'0' , '1' , '2' , '3' , '4' , '5' ,    
			'6' , '7' , '8' , '9' , 'a' , 'b' ,    
			'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,    
			'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,    
			'o' , 'p' , 'q' , 'r' , 's' , 't' ,    
			'u' , 'v' , 'w' , 'x' , 'y' , 'z'    
	};    

	public static String toUnsignedString(BigDecimal bigDecimal, int shift) {    
		BigDecimal divisor = new BigDecimal(shift);    
		StringBuilder builder = new StringBuilder();    
		do {    
			BigDecimal[] ba = bigDecimal.divideAndRemainder(divisor);    
			bigDecimal = ba[0];    
			builder.append(digits[ba[1].intValue()]);    
		} while (bigDecimal.compareTo(BigDecimal.ZERO) > 0);    
		return builder.reverse().toString();    
	}    
	
}