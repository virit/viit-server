package com.viit.base.config;

import groovy.lang.Binding;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * groovy配置
 *
 * @author virit
 * @version 2019-12-28
 */
@Configuration
public class GroovyConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Bean
    public Binding groovyBinding() {

        Binding groovyBinding = new Binding();
        Map<String, Object> beanMap = applicationContext.getBeansOfType(Object.class);
        for (String beanName : beanMap.keySet()) {
            groovyBinding.setVariable(beanName, beanMap.get(beanName));
        }
        return groovyBinding;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
