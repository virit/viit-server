package com.viit.base.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * 默认的认证配置
 *
 * @author virit
 * @version 2019-12-28
 */
public class DefaultAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        return new UsernamePasswordAuthenticationToken(auth.getName(),
                auth.getCredentials());
    }
}
