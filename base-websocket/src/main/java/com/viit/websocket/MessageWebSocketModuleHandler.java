package com.viit.websocket;

import com.viit.websocket.annotation.WSPath;
import org.springframework.stereotype.Service;

@Service
@WSPath("/test")
public class MessageWebSocketModuleHandler extends AbstractWebSocketHandler {

    @WSPath("/test")
    public String onGetBooleanMessage(String test) {
        return "123";
    }
}
