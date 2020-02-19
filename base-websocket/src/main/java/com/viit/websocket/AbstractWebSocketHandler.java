package com.viit.websocket;


import com.viit.websocket.annotation.WSPath;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * websocket处理抽象类
 *
 * @author virit
 * @version 2020-01-05
 */
public abstract class AbstractWebSocketHandler {

    private static Map<String, WebSocketHandlerInfo> infoMap = new HashMap<>(1);

    public AbstractWebSocketHandler() {
        Class<?> clazz = this.getClass();
        WSPath classWsPath = clazz.getAnnotation(WSPath.class);
        String basePath = "";
        if (classWsPath != null) {
            basePath = classWsPath.value();
        }
        // 处理映射方法
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            handleMethod(basePath, method);
        }
    }

    private void handleMethod(String basePath, Method method) {
        WSPath wsPath = method.getAnnotation(WSPath.class);
        if (wsPath == null) {
            return;
        }
        String path = wsPath.value();
        String fullPath = basePath + path;
        WebSocketHandlerInfo info = new WebSocketHandlerInfo(fullPath, method, this);
        infoMap.put(fullPath, info);
    }

    /**
     * 获取处理器信息
     *
     * @param path 处理路径
     * @return 处理器信息
     */
    static WebSocketHandlerInfo getHandlerInfo(String path) {
        return infoMap.get(path);
    }
}
