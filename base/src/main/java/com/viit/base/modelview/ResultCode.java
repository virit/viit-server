package com.viit.base.modelview;

/**
 * 业务状态码
 *
 * @author virit
 * @version 2019-10-28
 */
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(2000, "请求成功"),

    /**
     * 错误
     */
    ERROR(5000, "服务器错误"),

    /**
     * 未登录
     */
    NOT_LOGIN(4000, "用户未登录"),

    /**
     * 表单校验失败
     */
    FORM_VALID_FAILED(4001, "表单校验失败"),
    /**
     * 用户名或密码错误
     */
    USERNAME_OR_PASSWORD_ERROR(4002, "用户名或密码错误"),
    /**
     * 用户名或密码错误
     */
    USERNAME_EXISTENCE(4003, "用户名已存在"),
    /**
     * 用户未认证
     */
    UNAUTHORIZED(401, "用户未认证"),
    /**
     * 用户无权限
     */
    NO_PERMISSION(403, "用户无权限");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return String.valueOf(this.code);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
