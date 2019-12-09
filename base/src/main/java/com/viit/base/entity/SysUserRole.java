package com.viit.base.entity;

import com.viit.base.lang.entity.IdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色关联实体
 *
 * @author chentao
 * @version 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserRole extends IdEntity {

    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;
}
