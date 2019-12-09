package com.viit.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viit.base.entity.SysUserRole;

import java.util.List;

/**
 * 用户角色service
 *
 * @author chentao
 * @version 2019-10-29
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 获取用户的roleId列表
     * @param userId 用户id
     * @return roleId列表
     */
    List<String> listRoleIdByUserId(String userId);

    /**
     * 根据用户id删除
     * @param userId 用户id
     * @return boolean
     */
    boolean removeByUserId(String userId);
}
