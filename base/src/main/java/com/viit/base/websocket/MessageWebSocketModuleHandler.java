package com.viit.base.websocket;

import com.viit.base.annotation.websocket.JsonData;
import com.viit.base.annotation.websocket.WSModulePath;
import com.viit.base.entity.SysUser;
import org.springframework.stereotype.Service;

@Service
@WSModulePath("message")
public class MessageWebSocketModuleHandler {

    @WSModulePath("send")
    public void onGetBooleanMessage(@JsonData SysUser sysUser) {

    }
}
