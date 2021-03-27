package com.mju.generatepaper.common;

public enum ResponseStatusCode {
    SUCCESS(0, "操作成功"),
    FAILED(1, "操作失败"),
    OPERATION_EXCEPTION(2, "操作异常"),
    INVALID_SERVER_REQUEST(500, "服务器内部错误"),
    UNKNOWN_EXCEPTION(1000, "未知错误信息"),
    INVALID_ARGUMENT(1001, "非法请求参数"),
    INCORRECT_FORMAT(1002, "格式不正确"),
    ILLEGAL_INTERVAL(1003, "无效区间范围"),
    REMOTE_EXCEPTION(2000, "远程接口调用异常"),
    AUTHENTICATION_EXCEPTION(3000, "用户验证异常"),
    INVALID_AUTHENTICATION_EXCEPTION(3001, "登录凭证无效或已过期"),
    UNREGISTERED(3002, "未注册用户"),
    UNAUTHORIZED(3003, "未经授权，不允许访问"),
    PAGE_REDIRECT(4000, "页面跳转"),
    PAGE_POPMSG(4001, "弹出窗口显示信息。");

    private int code;
    private String message;

    private ResponseStatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
