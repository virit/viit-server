package com.viit.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viit.base.entity.SysRoleMenu;

import java.util.List;

/**
 * 角色按钮service
 *
 * @author virit
 * @version 2019-11-05
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 根据角色id删除
     */
    boolean removeByRoleId(String roleId);

    /**
     * 根据角色id获取菜单id列表
     */
    List<String> listMenuIdByRoleId(String roleId);
}
