package com.viit.base.service.util;

import com.viit.base.entity.SysUser;
import com.viit.base.util.RequestUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
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
     * 获取会话id
     * @return 会话id
     */
    public static String getSessionId() {
        return Optional.ofNullable(RequestUtils.getRequest())
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
