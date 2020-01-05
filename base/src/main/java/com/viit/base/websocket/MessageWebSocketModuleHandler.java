package com.viit.base.websocket;

import com.viit.base.annotation.websocket.WSPath;
import org.springframework.stereotype.Service;

@Service
@WSPath("/test")
public class MessageWebSocketModuleHandler extends AbstractWebSocketHandler {

    @WSPath("/test")
    public String onGetBooleanMessage(String test) {
        return "123";
    }
}
