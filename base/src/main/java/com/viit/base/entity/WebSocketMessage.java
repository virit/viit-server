package com.viit.base.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * websocket消息
 *
 * @author virit
 * @version 2019-12-06
 */
@Data
public class WebSocketMessage {

    /**
     * 消息域
     */
    private String scope;
    /**
     * 消息
     */
    private Object message;
    /**
     * 发送时间
     */
    private Date sendTime;
}
