package com.skbaby.orange.enums;

public enum ErrorCode {

    // sql
    SUCCESS(0,"成功"),
    INSERT_ERROR(1,"新增失败"),
    DELETE_ERROR(2,"删除失败"),
    NOT_LOGIN(3,"用户未登录"),
    TOKEN_INVALID(4,"token失效"),
    CLOSE_ERROR(5,"删除失败"),

    //微信错误
    SYSTEM_BUSY(-1,"系统繁忙，此时请开发者稍候再试"),
    RATE_LIMIT(45011,"频率限制，每个用户每分钟100次"),
    INVALID_CODE(40029,"code 无效");
    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
