package com.viit.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viit.base.entity.SysMessage;

import java.io.IOException;
import java.util.List;

/**
 * 系统消息service
 *
 * @author virit
 * @version 2019-11-12
 */
public interface SysMessageService extends IService<SysMessage> {

    /**
     * 发送消息
     *
     * @param message 消息实体
     * @return 是否成功
     */
    void sendMessage(SysMessage message) throws IOException;

    /**
     * 读取消息
     *
     * @param messageId 消息id
     * @param userId    用户id
     * @return 是否成功
     */
    boolean readMessage(String messageId, String userId);

    /**
     * 获取用户的消息
     *
     * @param userId 用户id
     * @param type   消息类型
     * @return 消息列表
     */
    List<SysMessage> listMessageByUserId(String userId, int type);
}
