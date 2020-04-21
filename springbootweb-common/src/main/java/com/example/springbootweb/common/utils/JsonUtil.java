package com.example.springbootweb.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author william
 * @date 2020/4/21
 */
public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class.getName());
    private static ObjectMapper objectMapper = new ObjectMapper();
    // dateformat
    private static final String STANDARDFORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        // 对象所有字段全部入列
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 取消默认转换 timestamps
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 忽略 bean 转 json 错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 时间统一
        objectMapper.setDateFormat(new SimpleDateFormat(STANDARDFORMAT));
        // 忽略 在 json 中存在，但是 JAVA 对象不存在的情况，防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 对象转Json
     * @param obj obj 对象
     * @return Json 格式字符串
     */
    public static <T> String bean2Json(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.warn("Parse String to Object error : {}", e.toString());
            return null;
        }
    }

    /**
     * 对象转 Json ,格式化显示
     * @param obj 对象
     * @return 格式化 json 输出
     */
    public static <T> String bean2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj :
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.warn("Parse String to Object error : {}", e.toString());
            return null;
        }
    }

    /**
     * json 字符串转换为对象
     * @param str    要转换的 json 字符串
     * @param clazz  class对象
     * @return       对象
     */
    public static <T> T string2Bean(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            logger.warn("Parse String to Object error : {}", e.toString());
            return null;
        }
    }

    /**
     * json 转换成集合
     * @param str           要转换的 json 字符串
     * @param typeReference 集合类型
     * @return              集合对象
     */
    public static <T> T string2Bean(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ?
                    str : objectMapper.readValue(str, typeReference));
        } catch (IOException e) {
            logger.warn("Parse String to Object error", e);
            return null;
        }
    }

    /**
     * json 转换成集合对象
     * @param str             要转换的 json 字符串
     * @param collectionClazz 集合泛型
     * @param elementClazzes  元素泛型
     * @return                对象
     */
    public static <T> T string2Bean(String str, Class<?> collectionClazz, Class<?>... elementClazzes) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClazz, elementClazzes);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            logger.warn("Parse String to Object error : {}" + e.toString());
            return null;
        }
    }
}
