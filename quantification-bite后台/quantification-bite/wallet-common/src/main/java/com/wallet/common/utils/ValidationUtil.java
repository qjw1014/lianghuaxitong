package com.wallet.common.utils;

import com.github.pagehelper.util.StringUtil;
import java.util.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * 说明：验证工具
 * @version
 */
public class ValidationUtil {
	/**
	  * 验证邮箱
	  * @param email
	  * @return
	  */
	 public static boolean checkEmail(String email){
	  boolean flag = false;
	  try{
	    String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	    Pattern regex = Pattern.compile(check);
	    Matcher matcher = regex.matcher(email);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	
	 /**
	  * 验证手机号码
	  * @param mobiles
	  * @return
	  */
	 public static boolean checkMobile(String mobile){
	  boolean flag = false;
	  try{
	    Pattern regex = Pattern.compile("^(1[3|4|5|6|7|8|9][0-9]{9})$");
	    Matcher matcher = regex.matcher(mobile);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	 /**
	  * 验证支付密码
	  * @param payPwd
	  * @return
	  */
	 public static boolean checkPayPwd(String payPwd){
	  boolean flag = false;
	  try{
	    Pattern regex = Pattern.compile("^\\d{6}$");
	    Matcher matcher = regex.matcher(payPwd);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	 /**
	  * 验证身份证号码
	  * @param certId 身份证号码
	  * @return
	  */
	 public static boolean checkCertId(String certId){
	  boolean flag = false;
	  try{
	    Pattern regex = Pattern.compile("^([1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2})$");
	    Matcher matcher = regex.matcher(certId);
	    flag = matcher.matches();
	   }catch(Exception e){
		   e.printStackTrace();
	    flag = false;
	   }
	  return flag;
	 }
	
	
	 /**
	  * 验证银行卡号
	  * @param bankCard 银行卡号
	  * @return
	  */
	 public static boolean checkBankCard(String bankCard){
	  boolean flag = false;
	  try{
	    Pattern regex = Pattern.compile("^([1-9]{1})(\\d{14}|\\d{16}|\\d{18})$");
	    Matcher matcher = regex.matcher(bankCard);
	    flag = matcher.matches();
	   }catch(Exception e){
		   e.printStackTrace();
	    flag = false;
	   }
	  return flag;
	 }
	
	 /**
	  * 验证base64是否生成图片
	  * @param imgBase64Str
	  * @return
	  * @throws Exception
	  */
	 public static boolean isImage(String imgBase64Str) {
	        if (StringUtil.isEmpty(imgBase64Str)) {
	            return false;
	        } else {
	            ByteArrayInputStream byteArrayInputStream = null;
	 
	            try {
	            	Base64.Decoder decoder = Base64.getDecoder();
	                byte[] byteArray = decoder.decode(imgBase64Str);
	                byteArrayInputStream = new ByteArrayInputStream(byteArray);
	                BufferedImage bufImg = ImageIO.read(byteArrayInputStream);
	                if (bufImg == null) {
	                    return false;
	                }
	                bufImg = null;
	            }catch (Exception e) {
					// TODO: handle exception
				} finally {
	                if (byteArrayInputStream != null) {
	                    try {
	                    	byteArrayInputStream.close();
						} catch (Exception e2) {
							// TODO: handle exception
						}
	                }
	            }
	        }
	        return true;
	}



	
}
