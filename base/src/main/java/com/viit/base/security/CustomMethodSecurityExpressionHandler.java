package com.viit.base.security;

import com.viit.base.config.SystemConfig;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
    private SystemConfig systemConfig;

    public CustomMethodSecurityExpressionHandler(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        CustomSecurityExpressionRoot root = new CustomSecurityExpressionRoot(authentication, systemConfig);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(this.trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
        return root;
    }
}
