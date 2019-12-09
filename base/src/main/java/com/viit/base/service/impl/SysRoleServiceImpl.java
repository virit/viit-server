package com.viit.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viit.base.entity.SysRole;
import com.viit.base.entity.SysRoleMenu;
import com.viit.base.mapper.SysRoleMapper;
import com.viit.base.service.SysRoleMenuService;
import com.viit.base.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统角色service实现类
 *
 * @author chentao
 * @version 2019-10-28
 */
@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysRoleMenuService sysRoleMenuService;

    public SysRoleServiceImpl(SysRoleMenuService sysRoleMenuService) {
        this.sysRoleMenuService = sysRoleMenuService;
    }

    private void handleBeforeSave(SysRole sysRole) {
        // 删除已有的配置
        sysRoleMenuService.removeByRoleId(sysRole.getId());
        if (sysRole.getMenuIdList() != null) {
            // 插入中间表
            List<SysRoleMenu> roleMenus = sysRole.getMenuIdList().stream().map(id -> {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setRoleId(sysRole.getId());
                roleMenu.setMenuId(id);
                return roleMenu;
            }).collect(Collectors.toList());
            sysRoleMenuService.saveBatch(roleMenus);
        }
    }

    @Override
    public boolean updateById(SysRole sysRole) {
        handleBeforeSave(sysRole);
        return super.updateById(sysRole);
    }

    @Override
    public boolean save(SysRole sysRole) {
        handleBeforeSave(sysRole);
        return super.save(sysRole);
    }

    @Override
    public SysRole getById(Serializable id) {
        SysRole role = super.getById(id);
        role.setMenuIdList(sysRoleMenuService.listMenuIdByRoleId(role.getId()));
        return role;
    }

    @Override
    public List<SysRole> listByUserId(String userId) {
        return baseMapper.selectByUserId(userId);
    }
}
