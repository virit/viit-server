package com.viit.base.entity;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * 系统权限
 *
 * @author virit
 * @version 2019-12-15
 */
@AllArgsConstructor
public class SysGrantedAuthority implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
