package com.viit.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viit.base.entity.SysRole;

import java.util.List;

/**
 * 系统角色service
 *
 * @author virit
 * @version 2019-10-28
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 获取用户的角色列表
     */
    List<SysRole> listByUserId(String userId);
}
