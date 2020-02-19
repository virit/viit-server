package com.viit.base.service.impl;

import com.viit.base.config.SuperInfo;
import com.viit.base.config.SystemConfig;
import com.viit.base.entity.SuperUser;
import com.viit.base.entity.SysUser;
import com.viit.base.service.SysUserRoleService;
import com.viit.base.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户详情service，配置类
 *
 * @author virit
 * @version 2019-10-28
 */
@Service
@PropertySource("classpath:system.properties")
public class UserDetailServiceImpl implements UserDetailsService {

    private final SysUserService sysUserService;

    private final SysUserRoleService sysUserRoleService;

    private final PasswordEncoder passwordEncoder;

    private final SuperInfo superInfo;

    public UserDetailServiceImpl(SysUserServiceImpl sysUserService, PasswordEncoder passwordEncoder,
                                 SystemConfig systemConfig, SysUserRoleService sysUserRoleService) {

        this.sysUserService = sysUserService;
        this.passwordEncoder = passwordEncoder;
        this.sysUserRoleService = sysUserRoleService;
        this.superInfo = systemConfig.superInfo();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser user = null;
        if (StringUtils.equals(superInfo.username(), username)) {
            // 超级用户
            user = new SuperUser(superInfo.username(), passwordEncoder.encode(superInfo.password()));
        } else {
            user = sysUserService.getOneByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException(username + "不存在");
            }
            if (user != null && !(superInfo.username().equals(user.getId()))) {
                user.setAuthorities(sysUserService.getUserAuthorities(user.getId()));
                user.setRoleIdList(sysUserRoleService.listRoleIdByUserId(user.getId()));
            }
        }
        if (user != null && StringUtils.isBlank(user.getAvatar())) {
            // 设置默认头像
            user.setAvatar(SysUser.DEFAULT_AVATAR);
        }
        return user;
    }
}
