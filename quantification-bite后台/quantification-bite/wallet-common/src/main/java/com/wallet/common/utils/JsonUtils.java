package com.wallet.common.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * json工具类
 *
 * @author wupengfei wupf86@126.com
 */
@SuppressWarnings("rawtypes")
public abstract class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static Logger log= LoggerFactory.getLogger(JsonUtils.class);

    static {
        MAPPER.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.setDateFormat(new SimpleDateFormat(DateUtils.YYYY_MM_DD_HH_MM_SS));
    }

    /**
     * 获取数组对应的实体类
     *
     * @param collectionClass 集合类
     * @param elementClasses  元素类
     * @return 获取数组对应的实体类
     */
    public static JavaType getCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClasses) {
        return MAPPER.getTypeFactory().constructCollectionType(collectionClass, elementClasses);
    }

    /**
     * json字符串转化为对象
     *
     * @param json  json字符串
     * @param clazz 对象类
     * @return 对象
     */
    public static <T> T jsonToObject(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json) || clazz == null) {
            return null;
        }
        try {
            return MAPPER.readValue(json, clazz);
        }
        catch (Exception e) {
            log.error("json转换对象异常json=" + json + ",clazz=" + clazz.getName(), e);
            //throw new BaseRuntimeException(CommonCode1000.CODE_1050, e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param json     json字符串
     * @param javaType java类型
     * @return json字符串转化为数组对象
     */
    public static <T> T jsonToObject(String json, JavaType javaType) {
        if (StringUtils.isBlank(json) || javaType == null) {
            return null;
        }
        try {
            return MAPPER.readValue(json, javaType);
        }
        catch (Exception e) {
            log.error("json转换对象异常json=" + json + ",javaType=" + javaType.getTypeName(), e);
            e.printStackTrace();
        }
        return null;
    }



    /**
     * @param object 对象
     * @return 对象转化为json字符串
     */
    public static String objectToJson(Object object) {
        if (object == null) {
            return null;
        }
        StringWriter sw = new StringWriter();
        try {
            MAPPER.writeValue(sw, object);
        }
        catch (Exception e) {
            log.error("对象转换json异常object=" + object, e);
            //throw new BaseRuntimeException(CommonCode1000.CODE_1050, e);
            e.printStackTrace();
        }
        return sw.toString();
    }

    public static JSONObject combineJson(JSONObject srcObj, JSONObject addObj) throws JSONException {

        Set<String> itKeys1 = addObj.keySet();
        String key, value;
        for (String obj:addObj.keySet() ) {
            srcObj.put(obj, addObj.get(obj));
        }

        return srcObj;
    }
}
