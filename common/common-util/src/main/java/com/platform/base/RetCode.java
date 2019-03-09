package com.platform.base;

public enum  RetCode {

    SUCCESS(0, "success"),
    ERROR(1, "请求失败"),
    SERVICE_BROKEN(2, "服务异常"),
    NEED_LOGIN(3, "请先登录");

    private Integer code;

    private String msg;

    private RetCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
