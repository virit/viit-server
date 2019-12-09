package com.viit.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.viit.base.lang.entity.IdEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 系统消息
 *
 * @author virit
 * @version 2019-11-12
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class SysMessage extends IdEntity {

    /**
     * 消息类型
     */
    private Integer type;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 消息目标用户
     */
    @TableField(exist = false)
    @JsonIgnore
    private List<String> toUsers;
}
