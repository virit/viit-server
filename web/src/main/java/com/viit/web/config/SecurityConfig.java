package com.viit.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viit.base.entity.SuperUser;
import com.viit.base.modelview.RestData;
import com.viit.base.modelview.ResultCode;
import com.viit.base.modelview.SimpleRestData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.servlet.http.HttpServletResponse;

/**
 * spring security配置类
 *
 * @author virit
 * @version 2019-10-28
 */
@Configuration
@EnableRedisHttpSession
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String JSON_TYPE = "application/json;charset=UTF-8";

    private static final String[] PERMIT_ALL_MATCHERS = {
            "/user/login",
            "/user/token",
            "/websocket/**"
    };

    private final ObjectMapper objectMapper;

    private SessionRegistry sessionRegistry;


    public SecurityConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, e) -> {
            response.setContentType(JSON_TYPE);
            RestData restData = new SimpleRestData<>().resultCode(ResultCode.NO_PERMISSION);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(objectMapper.writeValueAsString(restData));
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, e) -> {
            response.setContentType(JSON_TYPE);
            RestData restData = new SimpleRestData<>().resultCode(ResultCode.UNAUTHORIZED);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(objectMapper.writeValueAsString(restData));
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilter(new ConcurrentSessionFilter(sessionRegistry));
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(PERMIT_ALL_MATCHERS).permitAll()
                .antMatchers("/*").hasRole(SuperUser.SUPER)
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public SessionRegistry sessionRegistry(){
        sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }
}
