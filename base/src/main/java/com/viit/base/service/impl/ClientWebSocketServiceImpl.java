package com.viit.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.viit.base.entity.WebSocketMessage;
import com.viit.base.service.ClientWebSocketService;
import com.viit.base.websocket.CommonWebSocketServer;
import com.viit.base.websocket.MessagePackage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * websocket service实现类
 *
 * @author virit
 * @version 2019-12-06
 */
@Service
public class ClientWebSocketServiceImpl implements ClientWebSocketService {

    @Override
    public void sendMessage(String id, String message) throws IOException {

    }

    @Override
    public void sendMessages(Collection<String> ids, String message) throws IOException {
        Preconditions.checkNotNull(ids);
        for (String id : ids) {
            sendMessage(id, message);
        }
    }

    @Override
    public void sendMessage(String id, Object object) throws IOException {
        sendMessage(id, JSON.toJSONString(object));
    }

    @Override
    public void sendMessages(Collection<String> ids, Object object) throws IOException {
        sendMessages(ids, JSON.toJSONString(object));
    }

    @Override
    public void sendMessage(String id, WebSocketMessage webSocketMessage) throws IOException {
        sendMessage(id, JSON.toJSONString(webSocketMessage));
    }

    @Override
    public void sendMessage(Collection<String> ids, WebSocketMessage webSocketMessage) throws IOException {
        sendMessages(ids, JSON.toJSONString(webSocketMessage));
    }

    @Override
    public void sendMessage(String id, MessagePackage messagePackage, Consumer<?> consumer) throws IOException {

    }
}
