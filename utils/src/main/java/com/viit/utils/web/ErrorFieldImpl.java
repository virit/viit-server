package com.viit.utils.web;

import lombok.Data;

/**
 * 错误字段实现类
 *
 * @author virit
 * @version 2019-10-29
 */
@Data
public class ErrorFieldImpl implements ErrorField {

    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 消息
     */
    private String message;
}
