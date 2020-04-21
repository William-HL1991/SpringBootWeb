package com.example.springbootweb.common.utils;

import java.util.UUID;

/**
 * @author william
 * @date 2020/4/21
 */
public class StringUtil {

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
