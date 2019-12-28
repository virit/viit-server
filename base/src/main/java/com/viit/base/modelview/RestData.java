package com.viit.base.modelview;

/**
 * rest data
 *
 * @author virit
 * @version 2019-10-28
 */
public interface RestData<T> {

    /**
     * 设置业务码
     * @param code 业务码
     */
    void setCode(int code);

    /**
     * 获取业务码
     * @return 业务码
     */
    int getCode();

    /**
     * 设置消息
     * @param msg 消息
     */
    void setMsg(String msg);

    /**
     * 获取消息
     * @return 消息
     */
    String getMsg();

    /**
     * 设置数据
     * @param data 数据
     */
    void setData(T data);

    /**
     * 获取数据
     * @return 数据
     */
    T getData();
}
