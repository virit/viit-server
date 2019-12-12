package com.viit.base.service.impl;

import com.viit.base.config.SystemConfig;
import com.viit.base.entity.SuperUser;
import com.viit.base.entity.SysUser;
import com.viit.base.service.SysUserRoleService;
import com.viit.base.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户详情service，配置类
 *
 * @author chentao
 * @version 2019-10-28
 */
@Service
@PropertySource("classpath:system.properties")
public class UserDetailServiceImpl implements UserDetailsService {

    private final SysUserService sysUserService;

    /**
     * 超级用户用户名
     */
    @Value("${super.username}")
    private String superUsername;

    /**
     * 超级用户密码
     */
    @Value("${super.password}")
    private String superPassword;

    private final SysUserRoleService sysUserRoleService;

    private final SystemConfig systemConfig;

    private final PasswordEncoder passwordEncoder;

    public UserDetailServiceImpl(SysUserServiceImpl sysUserService, PasswordEncoder passwordEncoder,
                                 SystemConfig systemConfig, SysUserRoleService sysUserRoleService) {

        this.sysUserService = sysUserService;
        this.passwordEncoder = passwordEncoder;
        this.systemConfig = systemConfig;
        this.sysUserRoleService = sysUserRoleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser user = null;
        if (StringUtils.equals(superUsername, username)) {
            // 超级用户
            user = new SuperUser(superUsername, passwordEncoder.encode(superPassword));
        } else {
            user = sysUserService.getOneByUsername(username);
            if (!(systemConfig.getSuperUsername().equals(user.getId()))) {
                user.setAuthorities(sysUserService.getUserAuthorities(user.getId()));
                user.setRoleIdList(sysUserRoleService.listRoleIdByUserId(user.getId()));
            }
        }
        if (StringUtils.isBlank(user.getAvatar())) {
            // 设置默认头像
            user.setAvatar(SysUser.DEFAULT_AVATAR);
        }
        return user;
    }
}
