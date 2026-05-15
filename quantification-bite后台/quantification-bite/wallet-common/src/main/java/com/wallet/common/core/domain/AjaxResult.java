package com.wallet.common.core.domain;

import java.util.HashMap;

import cn.hutool.core.util.ObjectUtil;
import com.wallet.common.constant.HttpStatus;
import com.wallet.common.enums.LanguageEnum;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.StringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 操作消息提醒
 * 
 * @author wallet
 */
@Component
public class AjaxResult extends HashMap<String, Object>
{
    private static  RedisUtil redisUtil;
    @Autowired
    public AjaxResult(RedisUtil redisUtil) {
        AjaxResult.redisUtil = redisUtil;
    }

    private static final long serialVersionUID = 1L;
    
    /** 状态码 */
    public static final String CODE_TAG = "code";
    
    /** 返回内容 */
    public static final String MSG_TAG = "msg";
    
    /** 数据对象 */
    public static final String DATA_TAG = "data";
    
    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public AjaxResult()
    {
    }
    
    /**
     * 初始化一个新创建的 AjaxResult 对象
     * 
     * @param code 状态码
     * @param msg 返回内容
     */
    public AjaxResult(int code, String msg)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }
    
    /**
     * 初始化一个新创建的 AjaxResult 对象
     * 
     * @param code 状态码
     * @param msg 返回内容
     * @param data 数据对象
     */
    public AjaxResult(int code, String msg, Object data)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data))
        {
            super.put(DATA_TAG, data);
        }
    }
    
    /**
     * 返回成功消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static AjaxResult success(String msg, Object data)
    {
        return new AjaxResult(HttpStatus.SUCCESS, msg, data);
    }
    
    /**
     * 返回错误消息
     * 
     * @return
     */
    public static AjaxResult error()
    {
        return AjaxResult.error(getLanguageMsg(ErrMessage.CODE_FAIL));
    }
    
    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult error(String msg)
    {
        return AjaxResult.error(msg, null);
    }
    
    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult errorNotData()
    {
        return AjaxResult.error("查无数据", null);
    }
    
    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static AjaxResult error(String msg, Object data)
    {
        return new AjaxResult(HttpStatus.ERROR, msg, data);
    }
    
    /**
     * 返回错误消息
     * 
     * @param code 状态码
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult error(int code, String msg)
    {
        return new AjaxResult(code, msg, null);
    }
    
    /**
     * 返回成功消息
     * 
     * @param msg 内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg)
    {
        AjaxResult json = new AjaxResult();
        json.put("msg", msg);
        json.put("code", 200);
        return json;
    }
    
    /**
     * 返回成功消息
     * 
     * @return 成功消息
     */
    public static AjaxResult success()
    {
        return AjaxResult.success(getLanguageMsg(ErrMessage.CODE_SUCCESS));
    }

    public static AjaxResult successData(int code, Object value)
    {
        AjaxResult json = new AjaxResult();
        json.put("code", code);
        json.put("data", value);
        return json;
    }

    public static AjaxResult success(Integer code)
    {
        AjaxResult json = new AjaxResult();
        json.put("code", ErrMessage.getAsArray(ErrMessage.CODE_SUCCESS)[0]);
        json.put("msg", getLanguageMsg(code));
        return json;
    }
    
    /**
     * 返回成功消息
     * 
     * @param key 键值
     * @param value 内容
     * @return 成功消息
     */
    @Override
    public AjaxResult put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }
    
    public static AjaxResult createErrResult(Object[] error)
    {
        AjaxResult json = new AjaxResult();
        if (null == error || 2 > error.length)
            return json;
        json.put("code", error[0]);
        json.put("msg", error[1]);
        
        return json;
    }
    
    public static AjaxResult success(Object data, String msg)
    {
        AjaxResult json = new AjaxResult();
        json.put("code", ErrMessage.getAsArray(ErrMessage.CODE_SUCCESS)[0]);
        json.put("msg", msg);
        json.put("data", data);
        return json;
    }

    public static AjaxResult success(Object data, Integer code)
    {
        AjaxResult json = new AjaxResult();
        json.put("code", ErrMessage.getAsArray(ErrMessage.CODE_SUCCESS)[0]);
        json.put("msg", getLanguageMsg(code));
        json.put("data", data);
        return json;
    }
    
    public static AjaxResult success(Object data)
    {
        AjaxResult json = new AjaxResult();
        json.put("code", ErrMessage.getAsArray(ErrMessage.CODE_SUCCESS)[0]);
        json.put("msg", getLanguageMsg(ErrMessage.CODE_SUCCESS));
        json.put("data", data);
        return json;
    }


    public static AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }
    
    public static AjaxResult error(Integer errCode)
    {
        return createErrResult(ErrMessage.getAsArray(errCode));
    }

    /**
     * 先默认是返回英文信息
     * @param code
     * @return
     */
    public static String getLanguageMsg(Integer code){
        return ErrMessage.get(code);

//        Object language = redisUtil.get("language");
//        if(ObjectUtil.isNull(language)){
//            return ErrMessage_ZH.get(code);
//        }
//        String type = language.toString();
//        if(type.equalsIgnoreCase(LanguageEnum.en_US.getInfo())){
//            //英文
//            return ErrMessage.get(code);
//
//        }else if(type.equalsIgnoreCase(LanguageEnum.ru_RU.getInfo())){
//            //俄语
//            return ErrMessage_RU.get(code);
//
//        }else if(type.equalsIgnoreCase(LanguageEnum.zh_Hant.getInfo())){
//            //繁体
//            return ErrMessage_ZH_HANT.get(code);
//
//        }else if(type.equalsIgnoreCase(LanguageEnum.zh_Hans.getInfo())){
//            //中文
//            return ErrMessage_ZH.get(code);
//        }else{
//            return ErrMessage_ZH.get(code);
//        }
    }

}