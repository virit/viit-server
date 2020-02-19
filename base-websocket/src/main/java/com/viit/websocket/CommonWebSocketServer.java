package com.viit.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viit.base.entity.SysUser;
import com.viit.base.util.ApplicationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息websocket
 *
 * @author virit
 * @version 2019-11-22
 */
@Component
@ServerEndpoint("/ws/common")
public class CommonWebSocketServer {

    private static Map<String, Map<String, CommonWebSocketServer>> webSocketMap = new ConcurrentHashMap<>();

    private Session session;

    private String uid;

    @OnOpen
    public void onOpen(Session session) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) session.getUserPrincipal();
        SysUser sysUser = (SysUser) token.getPrincipal();
        Map<String, CommonWebSocketServer> userWsMap = webSocketMap.computeIfAbsent(
                sysUser.getId(), k -> new HashMap<>(1)
        );
        userWsMap.put(session.getId(), this);
        this.session = session;
        this.uid = sysUser.getId();

        MessagePackage messagePackage = new MessagePackage();
        messagePackage.setPath("/a/b");
        messagePackage.setMessage("123144");
        messagePackage.setId(UUID.randomUUID().toString());

        sendMessage("super", messagePackage);
    }

    @OnClose
    public void onClose() {
        Map<String, CommonWebSocketServer> userWsMap = webSocketMap.get(this.uid);
        userWsMap.remove(session.getId());
        if (userWsMap.isEmpty()) {
            webSocketMap.remove(this.uid);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InvocationTargetException
            , IllegalAccessException {

        ObjectMapper objectMapper = ApplicationUtils.getBean(ObjectMapper.class);

        MessagePackage messagePackage = objectMapper.readValue(message, MessagePackage.class);
        String path = messagePackage.getPath();
        if (StringUtils.isBlank(path)) {
            // TODO nothing
            return;
        }
        WebSocketHandlerInfo handlerInfo = AbstractWebSocketHandler.getHandlerInfo(path);

        MessagePackage resultPackage = new MessagePackage();
        resultPackage.setId(messagePackage.getId());
        if (handlerInfo == null) {
            resultPackage.setCode(HttpServletResponse.SC_NOT_FOUND);
            session.getBasicRemote().sendText(objectMapper.writeValueAsString(resultPackage));
            return;
        }

        Method method = handlerInfo.getMethod();
        Class<?>[] types = method.getParameterTypes();
        Object result;
        if (types.length == 0) {
            result = method.invoke(handlerInfo.getObject());
        } else {
            Class<?> type = types[0];
            Object param = objectMapper.readValue(messagePackage.getMessage(), type);
            result = method.invoke(handlerInfo.getObject(), param);
        }
        resultPackage.setMessage(objectMapper.writeValueAsString(result));
        resultPackage.setCode(HttpServletResponse.SC_OK);
        session.getBasicRemote().sendText(objectMapper.writeValueAsString(resultPackage));
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendMessage(String uid, MessagePackage messagePackage) {

        ObjectMapper objectMapper = ApplicationUtils.getBean(ObjectMapper.class);

        Map<String, CommonWebSocketServer> userWsMap = webSocketMap.get(uid);
        if (userWsMap != null) {
            userWsMap.forEach((k, server) -> {
                try {
                    server.sendMessage(objectMapper.writeValueAsString(messagePackage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}