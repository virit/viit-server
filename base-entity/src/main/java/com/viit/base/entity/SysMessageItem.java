package com.viit.base.entity;

import com.viit.base.lang.entity.IdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息记录
 *
 * @author virit
 * @version 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysMessageItem extends IdEntity {

    /**
     * 消息id
     */
    private String messageId;
    /**
     * 接收人id
     */
    private String userId;
    /**
     * 是否已读
     */
    private Boolean hasRead;
}
