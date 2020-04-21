package com.example.springbootweb.web.exception;

/**
 * @author william
 * @date 2020/4/21
 */
public class SpringBootWebNotFoundException extends RuntimeException {

    private String msg = "Not Found";

    @Override
    public String getMessage() {
        return this.msg;
    }
}
