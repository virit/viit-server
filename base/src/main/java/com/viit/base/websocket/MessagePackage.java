package com.viit.base.websocket;

import lombok.Data;

/**
 * 消息包
 *
 * @author virit
 * @version 2020-01-05
 */
@Data
public class MessagePackage {
    /**
     * 消息id
     */
    private String id;
    /**
     * 请求路径
     */
    private String path;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 状态码
     */
    private Integer code;
}
