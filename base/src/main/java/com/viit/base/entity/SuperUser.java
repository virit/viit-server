package com.viit.base.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.*;

/**
 * 超级用户
 *
 * @author chentao
 * @version 2019-10-31
 */
public class SuperUser extends SysUser {

    public static final String SUPER = "super";
    public static final String ROLE_SUPER = "ROLE_SUPER";

    public SuperUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.setId(username);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SysRole roleSuper = new SysRole();
        roleSuper.setCode(SUPER);
        List<GrantedAuthority> authorityList = new ArrayList<>(Collections.singletonList(roleSuper));
        setAuthorities(authorityList);
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
