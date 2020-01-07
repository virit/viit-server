package com.viit.base.utils;

import com.viit.base.entity.SysUser;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * context utils
 *
 * @author virit
 * @version 2019-10-29
 */
@Component
public class ContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    /**
     * 获取当前用户
     * @return 当前用户
     */
    public static SysUser currentUser() {
        return Optional.ofNullable((SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .orElse(new SysUser());
    }

    /**
     * 获取bean
     * @return bean
     */
    public static <T>T getBean(Class<T> type) {
        return context.getBean(type);
    }

    /**
     * 获取容器
     * @return 容器
     */
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * 获取request
     * @return request
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            return null;
        } else {
            return servletRequestAttributes.getRequest();
        }
    }

    /**
     * 获取response
     * @return request
     */
    public static HttpServletResponse getResponse(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            return null;
        } else {
            return servletRequestAttributes.getResponse();
        }
    }

    /**
     * 获取会话id
     * @return 会话id
     */
    public static String getSessionId() {
        return Optional.ofNullable(getRequest())
                .map(HttpServletRequest::getSession)
                .map(HttpSession::getId).get();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static void autowireBean(Object bean) {
        context.getAutowireCapableBeanFactory().autowireBean(bean);
    }
}
