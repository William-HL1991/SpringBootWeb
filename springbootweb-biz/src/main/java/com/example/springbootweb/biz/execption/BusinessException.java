package com.example.springbootweb.biz.execption;

import com.example.springbootweb.common.error.ServiceErrors;

/**
 * @author william
 * @date 2020/4/21
 */
public class BusinessException extends RuntimeException {
    private Integer code;

    public BusinessException(ServiceErrors errors) {
        super(errors.getMessage());
        this.code = errors.getCode();
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public Integer getCode() {
        return this.code;
    }
}