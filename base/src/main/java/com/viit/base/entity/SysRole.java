package com.viit.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.viit.base.lang.entity.IdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * 系统角色
 *
 * @author chentao
 * @version 2019-10-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends IdEntity implements GrantedAuthority {

    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色标识
     */
    private String code;
    /**
     * 类型id
     */
    private String typeId;
    /**
     * 角色描述
     */
    private String description;

    /**
     * 菜单id列表
     */
    @TableField(exist = false)
    private List<String> menuIdList;

    private static final String PREFIX = "ROLE_";

    @Override
    @JsonIgnore
    public String getAuthority() {
        return PREFIX + code;
    }
}
