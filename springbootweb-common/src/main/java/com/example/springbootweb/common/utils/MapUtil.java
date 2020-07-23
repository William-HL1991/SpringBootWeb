package com.example.springbootweb.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Map;

/**
 * @author william
 * @date 2020/7/23
 */
@Slf4j
public class MapUtil {

    public MapUtil() {
    }

    /**
     * 解析出类
     * @param map
     * @param key
     * @return
     */
    public static Object getObject(final Map map, final Object key) {
        if (map != null) {
            return map.get(key);
        }
        return null;
    }

    /**
     * 解析出字符串
     * @param map
     * @param key
     * @return
     */
    public static String getString(final Map map, final Object key) {
        if (map != null) {
            Object answer = map.get(key);
            if (answer != null) {
                return answer.toString();
            }
        }
        return null;
    }

    /**
     * 解析出 布尔值
     * @param map
     * @param key
     * @return
     */
    public static Boolean getBoolean(final Map map, final Object key) {
        if (map != null) {
            Object answer = map.get(key);
            if (answer != null) {
                if (answer instanceof Boolean) {
                    return (Boolean) answer;

                } else if (answer instanceof String) {
                    return new Boolean((String) answer);

                } else if (answer instanceof Number) {
                    Number n = (Number) answer;
                    return (n.intValue() != 0) ? Boolean.TRUE : Boolean.FALSE;
                }
            }
        }
        return null;
    }

    /**
     * 解析出数值
     * @param map
     * @param key
     * @return
     */
    public static Number getNumber(final Map map, final Object key) {
        if (map != null) {
            Object answer = map.get(key);
            if (answer != null) {
                if (answer instanceof Number) {
                    return (Number) answer;

                } else if (answer instanceof String) {
                    try {
                        String text = (String) answer;
                        return NumberFormat.getInstance().parse(text);

                    } catch (ParseException e) {
                        log.error(e.toString());
                    }
                }
            }
        }
        return null;
    }

    /**
     * 解析出字节
     * @param map
     * @param key
     * @return
     */
    public static Byte getByte(final Map map, final Object key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Byte) {
            return (Byte) answer;
        }
        return new Byte(answer.byteValue());
    }

    /**
     * 解析出 short 类型
     * @param map
     * @param key
     * @return
     */
    public static Short getShort(final Map map, final Object key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Short) {
            return (Short) answer;
        }
        return new Short(answer.shortValue());
    }

    /**
     * 解析出 int 形
     * @param map
     * @param key
     * @return
     */
    public static Integer getInteger(final Map map, final Object key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Integer) {
            return (Integer) answer;
        }
        return new Integer(answer.intValue());
    }

    /**
     * 解析出 long 形
     * @param map
     * @param key
     * @return
     */
    public static Long getLong(final Map map, final Object key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Long) {
            return (Long) answer;
        }
        return new Long(answer.longValue());
    }

    /**
     * 解析出 float
     * @param map
     * @param key
     * @return
     */
    public static Float getFloat(final Map map, final Object key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Float) {
            return (Float) answer;
        }
        return new Float(answer.floatValue());
    }

    /**
     * 解析出 doubule
     * @param map
     * @param key
     * @return
     */
    public static Double getDouble(final Map map, final Object key) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            return null;
        } else if (answer instanceof Double) {
            return (Double) answer;
        }
        return new Double(answer.doubleValue());
    }

}
