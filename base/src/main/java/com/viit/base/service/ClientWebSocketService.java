package com.viit.base.service;

import com.viit.base.entity.WebSocketMessage;

import java.io.IOException;
import java.util.Collection;

/**
 * websocket service
 *
 * @author virit
 * @version 2019-12-06
 */
public interface ClientWebSocketService {

    /**
     * 发送消息
     */
    void sendMessage(String id, String message) throws IOException;

    /**
     * 发送消息
     */
    void sendMessages(Collection<String> ids, String message) throws IOException;

    /**
     * 发送消息
     */
    void sendMessage(String id, Object object) throws IOException;

    /**
     * 发送消息
     */
    void sendMessages(Collection<String> ids, Object object) throws IOException;

    /**
     * 发送消息
     */
    void sendMessage(String id, WebSocketMessage webSocketMessage) throws IOException;

    /**
     * 发送消息
     */
    void sendMessage(Collection<String> ids, WebSocketMessage webSocketMessage) throws IOException;
}
