package com.viit.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viit.base.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * 系统用户service
 *
 * @author chentao
 * @version 2019-10-28
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 创建用户
     *
     * @param sysUser 用户
     * @return 是否成功
     */
    boolean createUser(SysUser sysUser);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    SysUser getOneByUsername(String username);

    List<GrantedAuthority> getUserAuthorities(String userId);
}
