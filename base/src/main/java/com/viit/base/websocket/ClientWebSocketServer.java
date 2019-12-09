package com.viit.base.websocket;

import com.viit.base.entity.SysUser;
import com.viit.base.utils.ContextUtils;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息websocket
 *
 * @author virit
 * @version 2019-11-22
 */
@Component
@ServerEndpoint("/websocket/common/{token}")
public class ClientWebSocketServer {

    private static int onlineCount = 0;

    private static Map<String, ClientWebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    private Session session;

    private String uid = "";

    private static class ComponentHolder {
        private static final SessionRegistry SESSION_REGISTRY = ContextUtils.getBean(SessionRegistry.class);
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String sessionId) {
        SysUser user = Optional.ofNullable(ComponentHolder.SESSION_REGISTRY.getSessionInformation(sessionId))
                .map(sessionInfo -> (SysUser) sessionInfo.getPrincipal())
                .orElse(new SysUser());
        this.uid = user.getId();
        this.session = session;
        webSocketMap.put(uid, this);
        addOnlineCount();
    }

    @OnClose
    public void onClose() {
        webSocketMap.remove(this.uid);
        subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        //群发消息
        webSocketMap.forEach((key, value) -> {
            try {
                value.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendInfo(@PathParam("uid") String uid, String message) {

        webSocketMap.forEach((key, value) -> {
            try {
                if (uid == null) {
                    value.sendMessage(message);
                } else if (value.uid.equals(uid)) {
                    value.sendMessage(message);
                }
            } catch (IOException ignored) {
            }
        });
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        ClientWebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        ClientWebSocketServer.onlineCount--;
    }

    public static ClientWebSocketServer getByUid(String uid) {
        return webSocketMap.get(uid);
    }
}