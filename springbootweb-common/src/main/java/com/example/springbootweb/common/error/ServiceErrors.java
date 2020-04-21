package com.example.springbootweb.common.error;

/**
 * @author william
 * @date 2020/4/21
 */
public interface ServiceErrors {

    /**
     * 获取错误码
     *
     * @return Integer
     */
    Integer getCode();

    /**
     * 获取错误信息
     *
     * @return String
     */
    String getMessage();

}
