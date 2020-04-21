package com.example.springbootweb.common.error;

/**
 * @author william
 * @date 2020/4/21
 */
public enum SpringBootWebErrors implements ServiceErrors {
    /**
     * 错误码
     */
    SYSTEM_ERROR(10000, "系统错误"),
    PARAM_ERROR(10001, "参数错误"),
    CONDITION_IS_NOT_EXIST(404001, "查询条件不存在"),
    IMG_IS_NOT_EXIST(404002, "没有输入图片参数"),
    IMG_IS_EXIST(404003, "同名图片已存在"),

    ;
    private Integer code;

    private String message;

    SpringBootWebErrors(Integer code, String message) {
        this.code    = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

