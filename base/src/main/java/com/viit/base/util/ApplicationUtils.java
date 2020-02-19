package com.viit.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Component
public class ApplicationUtils implements ApplicationContextAware {


    private static ApplicationContext context;

    /**
     * 获取bean
     *
     * @return bean
     */
    public static <T> T getBean(Class<T> type) {
        return context.getBean(type);
    }

    /**
     * 获取容器
     *
     * @return 容器
     */
    public static ApplicationContext getApplicationContext() {
        return context;
    }


    /**
     * 获取会话id
     *
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
