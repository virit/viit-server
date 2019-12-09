package com.viit.utils.web;

/**
 * 错误字段
 *
 * @author chentao
 * @version 2019-10-29
 */
public interface ErrorField {

    /**
     * 获取字段名
     * @return 字段名
     */
    String getFieldName();

    /**
     * 获取错误消息
     * @return 错误消息
     */
    String getMessage();
}
