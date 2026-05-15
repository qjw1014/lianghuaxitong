package com.wallet.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Util
{
    
    /*****
     * 
     * 加密
     * 
     * @param str
     * @return
     */
    @SuppressWarnings("restriction")
    public static String encodeGetBase64(String str)
    {
        byte[] b = null;
        String s = null;
        try
        {
            b = str.getBytes("utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        if (b != null)
        {
            s = Base64.getEncoder().encodeToString(b);
        }
        return s;
    }
    
    /***
     * 
     * 解密
     * 
     * @param s
     * @return
     */
    @SuppressWarnings("restriction")
    public static String decodeFromBase64(String s)
    {
        byte[] b = null;
        String result = null;
        if (s != null)
        {
            @SuppressWarnings("restriction")
            Base64.Decoder decoder = Base64.getDecoder();
            try
            {
                b = decoder.decode(s);
                result = new String(b, "utf-8");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public static void main(String[] args)
    {
        // System.out.println(decodeFromBase64("V2l0aG91dFlvdQ=="));
        // System.out.println(encodeGetBase64("WithoutYou"));
        // System.out.println(decodeFromBase64("V2l0aG91dFlvdQ=="));
        
        String str = "withoutYou";
        try
        {
            str = new String(Base64.getEncoder().encode(str.getBytes()), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        // System.out.println(Base64Utils.encodeToString(str.getBytes()));
//        try
//        {
//            // System.out.println(new String(Base64Utils.decodeFromString(str), "utf-8"));
//        }
//        catch (UnsupportedEncodingException e)
//        {
//            e.printStackTrace();
//        }
        
    }
}
