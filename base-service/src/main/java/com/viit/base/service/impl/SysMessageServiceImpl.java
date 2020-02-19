package com.viit.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viit.base.entity.SysMessage;
import com.viit.base.entity.SysMessageItem;
import com.viit.base.mapper.SysMessageMapper;
import com.viit.base.service.ClientWebSocketService;
import com.viit.base.service.SysMessageItemService;
import com.viit.base.service.SysMessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息service
 *
 * @author virit
 * @version 2019-11-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysMessageServiceImpl extends ServiceImpl<SysMessageMapper, SysMessage> implements SysMessageService {

    private final SysMessageItemService sysMessageItemService;

    private final ClientWebSocketService clientWebSocketService;

    interface Scope {
        String MESSAGE_NEW = "message:new";
    }

    public SysMessageServiceImpl(SysMessageItemService sysMessageItemService, ClientWebSocketService clientWebSocketService) {
        this.sysMessageItemService = sysMessageItemService;
        this.clientWebSocketService = clientWebSocketService;
    }

    @Override
    public List<SysMessage> list(Wrapper<SysMessage> queryWrapper) {
        return super.list(queryWrapper);
    }

    @Override
    public void sendMessage(SysMessage message) throws IOException {
        // 插入数据库
        this.save(message);
        // 对每个用户插入消息实体
        List<SysMessageItem> items = new ArrayList<>(message.getToUsers().size());
        for (String toUser : message.getToUsers()) {
            SysMessageItem messageItem = new SysMessageItem();
            messageItem.setHasRead(false);
            messageItem.setMessageId(message.getId());
            messageItem.setUserId(toUser);
            items.add(messageItem);
        }
        sysMessageItemService.saveBatch(items);
        // websocket进行推送
//        WebSocketMessage webSocketMessage = new WebSocketMessage();
//        webSocketMessage.setScope(Scope.MESSAGE_NEW);
//        webSocketMessage.setSendTime(DateUtils.currentDate());
//        webSocketMessage.setMessage(message);
//        clientWebSocketService.sendMessages(message.getToUsers(), message);
    }

    @Override
    public boolean readMessage(String messageId, String userId) {
        return false;
    }

    @Override
    public List<SysMessage> listMessageByUserId(String userId, int type) {
        return null;
    }
}
