package com.viit.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Preconditions;
import com.viit.base.config.SystemConfig;
import com.viit.base.entity.SysMenu;
import com.viit.base.entity.SysRole;
import com.viit.base.entity.SysRoleMenu;
import com.viit.base.entity.SysUserRole;
import com.viit.base.mapper.SysMenuMapper;
import com.viit.base.service.SysMenuService;
import com.viit.base.service.SysRoleMenuService;
import com.viit.base.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统菜单service实现类
 *
 * @author chentao
 * @version 2019-10-29
 */
@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysRoleMenuService sysRoleMenuService;

    private final SysRoleService sysRoleService;

    private final SystemConfig systemConfig;

    public SysMenuServiceImpl(SysRoleMenuService sysRoleMenuService, SysRoleService sysRoleService, SystemConfig systemConfig) {
        this.sysRoleMenuService = sysRoleMenuService;
        this.sysRoleService = sysRoleService;
        this.systemConfig = systemConfig;
    }

    @Override
    public List<SysMenu> listByParentId(String id) {

        if (StringUtils.isBlank(id)) {
            return this.list();
        } else {
            SysMenu query = new SysMenu();
            query.setParentId(id);
            QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>(query);
            return this.list(queryWrapper);
        }
    }

    private List<SysRoleMenu> listRoleMenuByRoleId(String roleId) {
        SysRoleMenu query = new SysRoleMenu();
        query.setRoleId(roleId);
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>(query);
        return sysRoleMenuService.list(queryWrapper);
    }

    @Override
    public List<SysMenu> listByRoleId(String id) {
        List<SysRoleMenu> sysRoleMenus = listRoleMenuByRoleId(id);
        return listByIds(sysRoleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList()));
    }

    @Override
    public boolean removeByRoleId(String roleId) {
        return false;
    }

    @Override
    public void assignMenus(String roleId, List<String> menuIds) {
        Preconditions.checkNotNull(roleId);
        // 移除角色的所有菜单
        sysRoleMenuService.removeByRoleId(roleId);
        // 重新分配按钮
        if (menuIds != null) {
            List<SysRoleMenu> roleMenus = menuIds.stream().map(menuId -> {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setMenuId(menuId);
                roleMenu.setRoleId(roleId);
                return roleMenu;
            }).collect(Collectors.toList());
            sysRoleMenuService.saveBatch(roleMenus);
        }
    }

    @Override
    public List<SysMenu> listByUserId(String userId) {
        if (systemConfig.getSuperUsername().equals(userId)) {
            return this.listByParentId(null);
        }
        // 获取用户的角色列表
        List<SysRole> roles = sysRoleService.listByUserId(userId);
        List<SysMenu> menuCollection = new ArrayList<>(10);
        for (SysRole role : roles) {
            // 获取角色的菜单
            List<SysMenu> menus = this.listByRoleId(role.getId());
            for (SysMenu menu : menus) {
                boolean flag = true;
                for (SysMenu menuInCollection : menuCollection) {
                    if (menu.getId().equals(menuInCollection.getId())) {
                        flag = false;
                    }
                }
                if (flag) {
                    menuCollection.add(menu);
                }
            }
        }
        // 递归获取父级菜单
        Map<String, SysMenu> menuMap = convertMenuMap(menuCollection);
        List<SysMenu> parents = new ArrayList<>(10);
        for (SysMenu menu : menuCollection) {
            SysMenu current = menu;
            while (StringUtils.isNotBlank(current.getParentId()) && menuMap.get(current.getParentId()) == null) {
                SysMenu parent = getById(current.getParentId());
                menuMap.put(parent.getId(), parent);
                parents.add(parent);
                current = parent;
            }
        }
        menuCollection.addAll(parents);
        return menuCollection;
    }

    private Map<String, SysMenu> convertMenuMap(List<SysMenu> menus) {
        Map<String, SysMenu> menuMap = new HashMap<>(10);
        for (SysMenu menu : menus) {
            menuMap.put(menu.getId(), menu);
        }
        return menuMap;
    }

    @Override
    public boolean removeById(Serializable id) {
        List<SysMenu> children = listByParentId((String) id);
        boolean result = super.removeById(id);
        if (result) {
            children.forEach(sysMenu -> removeById(sysMenu.getId()));
        }
        return result;
    }
}
