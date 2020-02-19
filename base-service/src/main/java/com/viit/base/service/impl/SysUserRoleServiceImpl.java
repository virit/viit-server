package com.viit.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viit.base.entity.SysUserRole;
import com.viit.base.mapper.SysUserRoleMapper;
import com.viit.base.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色service实现类
 *
 * @author virit
 * @version 2019-10-29
 */
@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public List<String> listRoleIdByUserId(String userId) {

        SysUserRole query = new SysUserRole();
        query.setUserId(userId);
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>(query);
        return this.list(queryWrapper).stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
    }

    @Override
    public boolean removeByUserId(String userId) {
        SysUserRole query = new SysUserRole();
        query.setUserId(userId);
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>(query);
        return this.remove(queryWrapper);
    }
}
