package com.viit.base.entity;

import com.viit.base.lang.entity.IdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色菜单
 *
 * @author virit
 * @version 2019-11-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenu extends IdEntity {

    /**
     * 角色id
     */
    private String roleId;
    /**
     * 菜单id
     */
    private String menuId;
}
