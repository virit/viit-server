package com.viit.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viit.base.entity.SysMenu;
import com.viit.base.util.objects.OrderObject;

import java.util.List;

/**
 * 系统菜单service
 *
 * @author virit
 * @version 2019-10-29
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 根据父级id获取
     * @param id 父级id
     * @return 列表
     */
    List<SysMenu> listByParentId(String id);

    /**
     * 根据roleId获取
     */
    List<SysMenu> listByRoleId(String id);

    /**
     * 移除角色的所有菜单
     * @param roleId 角色id
     * @return boolean
     */
    boolean removeByRoleId(String roleId);

    /**
     * 分配菜单
     */
    void assignMenus(String roleId, List<String> menuIds);

    /**
     * 获取用户的菜单列表
     * @param userId 用户id
     * @return 用户的菜单列表
     */
    List<SysMenu> listByUserId(String userId);

    /**
     * 保存排序
     * @param items 排序列表
     */
    void saveOrder(List<OrderObject> items);
}
