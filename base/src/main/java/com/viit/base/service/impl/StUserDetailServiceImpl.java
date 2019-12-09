package com.viit.base.service.impl;

import com.viit.base.entity.SuperUser;
import com.viit.base.entity.SysUser;
import com.viit.base.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
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
public class StUserDetailServiceImpl implements UserDetailsService {

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

    private final PasswordEncoder passwordEncoder;

    public StUserDetailServiceImpl(SysUserServiceImpl sysUserService, PasswordEncoder passwordEncoder) {
        this.sysUserService = sysUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = null;
        if (StringUtils.equals(superUsername, username)) {
            // 超级用户
            user = new SuperUser(superUsername, passwordEncoder.encode(superPassword));
        } else {
            user = sysUserService.getOneByUsername(username);
        }
        if (StringUtils.isBlank(user.getAvatar())) {
            // 设置默认头像
            user.setAvatar(SysUser.DEFAULT_AVATAR);
        }
        return user;
    }
}
