package com.example.springbootweb.biz.execption;

import com.example.springbootweb.common.error.SpringBootWebErrors;

public class ServiceException extends BusinessException {
    private SpringBootWebErrors errors;
    private Object data;

    public ServiceException(SpringBootWebErrors errors) {
        super(errors);
        this.errors = errors;
    }

    public ServiceException(SpringBootWebErrors errors, Object data) {
        super(errors);
        this.errors = errors;
        this.data = data;
    }


    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public SpringBootWebErrors getError() {
        return errors;
    }

    public Object getData() {
        return data;
    }
}
