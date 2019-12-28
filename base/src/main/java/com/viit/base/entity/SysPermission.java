package com.viit.base.entity;

import com.viit.base.lang.entity.IdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

/**
 * 系统权限
 *
 * @author virit
 * @version 2019-10-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysPermission extends IdEntity implements GrantedAuthority {

    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限码
     */
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
