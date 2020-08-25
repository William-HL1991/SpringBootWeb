package com.example.springbootweb.common.error;

/**
 * @author william
 * @date 2020/4/21
 */
public enum SpringBootWebErrors implements ServiceErrors {
    /**
     * 错误码
     */
    BadRequest(400, "bad request"),
    CONDITION_IS_NOT_EXIST(404001, "查询条件不存在"),
    IMG_IS_NOT_EXIST(404002, "没有输入图片参数"),
    IMG_IS_EXIST(404003, "同名图片已存在"),
    NOT_LOGIN(404004, "尚未登录"),
    NOT_PERMISSION(404005, "没有对应的权限"),
    MethodNotAllowed(405, "%s请求不被允许"),
    NotAcceptable(406, "请使用%s参数"),
    SystemError(500, "系统内部错误"),
    RuntimeException(700, "[server] runtime exception"),
    NullPointerException(701, "[server] null pointer exception"),
    ClassCastException(702, "[server] class cast exception"),
    IoException(703, "[server] io exception"),
    NoSuchMethodException(704, "[server] no such method exception"),
    IndexOutOfBoundsException(705, "[server] index out of bounds exception"),
    RequestNotReadable(706, "[server] request not readable"),;

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

