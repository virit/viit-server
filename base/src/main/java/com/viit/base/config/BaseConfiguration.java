package com.viit.base.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.viit.base.attach.AttachManager;
import com.viit.base.attach.LocalAttachManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.sql.DataSource;

/**
 * 配置类
 *
 * @author chentao
 * @version 2019-10-28
 */
@Configuration
@PropertySource(value = "classpath:system.properties", encoding = "UTF-8")
public class BaseConfiguration {

    /**
     * 附件服务配置
     */
    @Value("${attachServer}")
    private String attachServer;

    public @Bean BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置分页
     */
    public @Bean MybatisSqlSessionFactoryBean sessionFactory(DataSource dataSource) {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        PaginationInterceptor interceptor = new PaginationInterceptor();
        interceptor.setDialectType("mysql");
        bean.setPlugins(interceptor);
        return bean;
    }

    /**
     * 配置附件管理器
     */
    public @Bean AttachManager attachManager() {

        final String ftpPrefix = "ftp://";
        // ftp服务
        if (attachServer.startsWith(ftpPrefix)) {
            return null;
        } else {
            return new LocalAttachManager(attachServer);
        }
    }

    public @Bean ServerEndpointExporter serverEndpointExporter () {
        return new ServerEndpointExporter();
    }
}
