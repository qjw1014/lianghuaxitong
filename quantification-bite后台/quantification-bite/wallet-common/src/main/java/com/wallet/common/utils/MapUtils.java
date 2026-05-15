package com.wallet.common.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Splitter;
import com.google.common.base.Splitter.MapSplitter;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * map工具类
 *
 * @author chenle
 */
public abstract class MapUtils {

    private static final MapSplitter MAP_SPLITTER = Splitter.on("&").withKeyValueSeparator("=");

    private static final Logger log = LoggerFactory.getLogger(MapUtils.class);

    /**
     * 转化成map
     *
     * @param str 格式key1=value1&key2=value2
     * @return map对象
     */
    public static Map<String, String> toMap(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        return MAP_SPLITTER.split(str);
    }

    /**
     * 转换map中的值类型
     *
     * @param map 待转换的对象
     * @return 转换后的值
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> cast(Map<String, String> map) {
        int size = map != null ? map.size() : 0;
        Map<String, T> result = Maps.newHashMapWithExpectedSize(size);
        if (size != 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                result.put(entry.getKey(), (T) entry.getValue());
            }
        }
        return result;
    }

    /**
     * 校验是否为空
     *
     * @param map map对象
     * @return 若为null或size==0则返回true
     */
    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    /**
     * 校验是否不为空
     *
     * @param map map对象
     * @return 若不为null或size>0则返回true
     */
    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    /**
     * 获取哈希的长度，若为空则返回0
     *
     * @param map 哈希
     * @return 哈希的长度
     */
    public static int getSize(Map<?, ?> map) {
        return map != null ? map.size() : 0;
    }

    /**
     * 将bean的声明字段（不包含继承的）生成map对象（map中key的值为注解JsonProperty的值或字段名）
     *
     * @param bean 对象
     * @return map对象
     */
    public static Map<String, String> toMap(Object bean) {
        if (bean == null) {
            return null;
        }
        Map<String, String> result = Maps.newHashMap();
        List<Field> fields = new ArrayList<>(Arrays.asList(bean.getClass().getDeclaredFields()));
        try {
            for (Field each : fields) {
                boolean isAccessible = each.isAccessible();
                each.setAccessible(true);

                // 获取字段值
                Object value = each.get(bean);
                if (value != null) {
                    if (each.isAnnotationPresent(JsonProperty.class)) {
                        result.put(each.getAnnotation(JsonProperty.class).value(), value.toString());
                    }
                    else if (!Modifier.isStatic(each.getModifiers())) {
                        //忽略掉静态成员变量
                        result.put(each.getName(), value.toString());
                    }
                }
                each.setAccessible(isAccessible);
            }
        }
        catch (Exception e) {
            log.error("对象转map时异常", e);
        }
        return result;
    }

    /**
     * 转换map为字符串，格式key1=value1&key2=value2
     *
     * @param paramsMap 参数
     * @return 字符串
     */
    public static String toString(Map<String, Object> paramsMap) {
        // 获取参数名并排序
        List<String> nameList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            nameList.add(entry.getKey());
        }
        Collections.sort(nameList);

        // 拼装参数名和参数值
        StringBuilder result = new StringBuilder();
        for (String name : nameList) {
            Object value = paramsMap.get(name);
            result.append(name).append("=").append(value).append("&");
        }
        deleteLastChar(result);
        return result.toString();
    }

    /**
     * 删除最后一个字符
     *
     * @param builder StringBuilder对象
     */
    public static void deleteLastChar(StringBuilder builder) {
        int length;
        if (builder != null && (length = builder.length()) != 0) {
            builder.deleteCharAt(length - 1);
        }
    }

}
