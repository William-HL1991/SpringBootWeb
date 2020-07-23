package com.example.springbootweb.dao.common;

/**
 * @author william
 * @date 2020/7/23
 */
public class MultipleDataSourceHelper {
    public static final String MASTER = "master";
    public static final String SLAVE = "slave";

    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();
    public static void set(String db) {
        contextHolder.set(db);
    }

    public static String get() {
        return contextHolder.get();
    }
}
