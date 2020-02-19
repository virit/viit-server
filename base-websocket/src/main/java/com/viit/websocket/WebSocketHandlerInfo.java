package com.viit.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * 处理器信息
 *
 * @author virit
 * @version 2020-01-05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketHandlerInfo {

    private String path;

    private Method method;

    private Object object;
}
