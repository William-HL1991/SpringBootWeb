package com.example.springbootweb.biz.execption;

import com.example.springbootweb.common.error.ServiceErrors;

/**
 * @author william
 * @date 2020/4/21
 */
public class ServiceException extends RuntimeException {
    private final Integer code;

    public ServiceException(ServiceErrors errors) {
        super(errors.getMessage());
        this.code = errors.getCode();
    }

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}