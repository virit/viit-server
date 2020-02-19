package com.viit.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.viit.base.entity.SysRole;
import com.viit.base.entity.SysRoleMenu;
import com.viit.base.entity.SysRoleType;
import com.viit.base.entity.SysUserRole;
import com.viit.base.mapper.SysRoleMapper;
import com.viit.base.service.SysRoleMenuService;
import com.viit.base.service.SysRoleService;
import com.viit.base.service.SysRoleTypeService;
import com.viit.base.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 系统角色service实现类
 *
 * @author virit
 * @version 2019-10-28
 */
@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class SysRoleServiceImpl extends FieldsInjectServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysRoleMenuService sysRoleMenuService;

    private final SysRoleTypeService sysRoleTypeService;

    private final SysUserRoleService sysUserRoleService;

    public SysRoleServiceImpl(SysRoleMenuService sysRoleMenuService, SysRoleTypeService sysRoleTypeService
            , SysUserRoleService sysUserRoleService) {
        this.sysRoleMenuService = sysRoleMenuService;
        this.sysRoleTypeService = sysRoleTypeService;
        this.sysUserRoleService = sysUserRoleService;
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
    public List<SysRole> infoFieldsInject(List<SysRole> list) {
        if (list == null) {
            return null;
        }
        list.forEach(role -> {
            String typeName = Optional.ofNullable(sysRoleTypeService.getById(role.getTypeId()))
                    .map(SysRoleType::getTypeName).orElse("");
            role.addInfoField("typeIdText", typeName);
            role.setMenuIdList(sysRoleMenuService.listMenuIdByRoleId(role.getId()));
        });
        return list;
    }

    @Override
    public SysRole getById(Serializable id) {
        SysRole role = super.getById(id);
        role.setMenuIdList(sysRoleMenuService.listMenuIdByRoleId(role.getId()));
        return role;
    }

    private List<SysUserRole> listUserRoleByUserId(String userId) {
        SysUserRole query = new SysUserRole();
        query.setUserId(userId);
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>(query);
        return sysUserRoleService.list(queryWrapper);
    }

    @Override
    public List<SysRole> listByUserId(String userId) {

        List<SysUserRole> sysUserRoles = listUserRoleByUserId(userId);
        return listByIds(sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList()));
    }
}
