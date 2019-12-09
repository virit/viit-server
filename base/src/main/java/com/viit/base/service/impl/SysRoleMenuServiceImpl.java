package com.viit.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viit.base.entity.SysRoleMenu;
import com.viit.base.mapper.SysRoleMenuMapper;
import com.viit.base.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色菜单service实现类
 *
 * @author virit
 * @version 2019-11-05
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Override
    public boolean removeByRoleId(String roleId) {
        SysRoleMenu query = new SysRoleMenu();
        query.setRoleId(roleId);
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>(query);
        return this.remove(queryWrapper);
    }

    @Override
    public List<String> listMenuIdByRoleId(String userId) {

        SysRoleMenu query = new SysRoleMenu();
        query.setRoleId(userId);
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>(query);
        return this.list(queryWrapper).stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
    }
}
