package com.viit.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.viit.base.config.SystemConfig;
import com.viit.base.entity.SysMenu;
import com.viit.base.entity.SysRole;
import com.viit.base.entity.SysUser;
import com.viit.base.entity.SysUserRole;
import com.viit.base.constants.SysMenuType;
import com.viit.base.mapper.SysUserMapper;
import com.viit.base.service.SysMenuService;
import com.viit.base.service.SysRoleService;
import com.viit.base.service.SysUserRoleService;
import com.viit.base.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统用户service实现类
 *
 * @author chentao
 * @version 2019-10-28
 */
@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final PasswordEncoder passwordEncoder;

    private final SysUserRoleService sysUserRoleService;

    private final SysRoleService sysRoleService;

    private final SysMenuService sysMenuService;

    private final SystemConfig systemConfig;

    public SysUserServiceImpl(PasswordEncoder passwordEncoder, SysUserRoleService sysUserRoleService,
                              SysRoleService sysRoleService, SysMenuService sysMenuService, SystemConfig systemConfig) {
        this.passwordEncoder = passwordEncoder;
        this.sysUserRoleService = sysUserRoleService;
        this.sysRoleService = sysRoleService;
        this.sysMenuService = sysMenuService;
        this.systemConfig = systemConfig;
    }

    private void handleSave(SysUser sysUser) {
        if (sysUser.getRoleIdList() != null) {
            // 删除已有的配置
            sysUserRoleService.removeByUserId(sysUser.getId());
            // 插入中间表
            List<SysUserRole> userRoles = sysUser.getRoleIdList().stream().map(id -> {
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(sysUser.getId());
                userRole.setRoleId(id);
                return userRole;
            }).collect(Collectors.toList());
            sysUserRoleService.saveBatch(userRoles);
        }
    }

    @Override
    public boolean updateById(SysUser sysUser) {
        if (StringUtils.isNotBlank(sysUser.getPassword())) {
            sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        }
        handleSave(sysUser);
        return super.updateById(sysUser);
    }

    @Override
    public boolean save(SysUser sysUser) {
        if (StringUtils.isNotBlank(sysUser.getPassword())) {
            sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        }
        boolean result = super.save(sysUser);
        handleSave(sysUser);
        return result;
    }

    @Override
    public boolean createUser(SysUser sysUser) {
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        return save(sysUser);
    }

    @Override
    public SysUser getOneByUsername(String username) {
        SysUser query = new SysUser();
        query.setUsername(username);
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>(query).eq("username", username);
        return this.getOne(queryWrapper);
    }

    @Override
    public SysUser getById(Serializable id) {
        SysUser user = super.getById(id);
        if (!(systemConfig.getSuperUsername().equals(id))) {
            user.setRoleIdList(sysUserRoleService.listRoleIdByUserId(user.getId()));
        }
        return user;
    }

    @Override
    public List<GrantedAuthority> getUserAuthorities(String userId) {
        // 获取用户角色
        List<SysRole> userRoles = sysRoleService.listByUserId(userId);
        List<GrantedAuthority> authorities = new ArrayList<>(userRoles);
        // 循环获取角色的权限
        for (SysRole userRole : userRoles) {
            List<SysMenu> menus = sysMenuService.listByRoleId(userRole.getId());
            menus = menus.stream().filter(sysMenu -> SysMenuType.BUTTON == sysMenu.getType())
                    .distinct().collect(Collectors.toList());
            authorities.addAll(menus);
        }
        return authorities;
    }
}
