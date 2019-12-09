package com.viit.base.entity;

import com.viit.base.lang.entity.IdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 系统角色类型
 *
 * @author chentao
 * @version 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleType extends IdEntity {

    /**
     * 类型名称
     */
    @NotBlank
    @Size(max = 16)
    private String typeName;
    /**
     * 类型描述
     */
    @Size(max = 32)
    private String description;
}
