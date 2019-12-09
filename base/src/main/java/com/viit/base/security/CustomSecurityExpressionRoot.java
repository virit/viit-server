package com.viit.base.security;

import com.viit.base.config.SystemConfig;
import com.viit.base.utils.ContextUtils;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

/**
 * 自定义方法表达式
 *
 * @author virit
 * @version 2019-11-12
 */
public class CustomSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;

    private SystemConfig systemConfig;

    public CustomSecurityExpressionRoot(Authentication authentication, SystemConfig systemConfig) {
        super(authentication);
        this.systemConfig = systemConfig;
    }

    /**
     * 基于hasAuthority
     * @param authority 权限
     * @return boolean
     */
    public final boolean withAuthority(String authority) {
        if (systemConfig.getSuperUsername().equals(ContextUtils.currentUser().getId())) {
            return true;
        }
        return hasAuthority(authority);
    }
    /**
     * 基于hasAuthority
     * @param authorities 权限
     * @return boolean
     */
    public final boolean withAnyAuthority(String... authorities) {
        if (systemConfig.getSuperUsername().equals(ContextUtils.currentUser().getId())) {
            return true;
        }
        return hasAnyAuthority(authorities);
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public void setFilterObject(Object obj) {
        this.filterObject = obj;
    }

    @Override
    public void setReturnObject(Object obj) {
        this.returnObject = obj;
    }
}
