package com.viit.base.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.viit.base.attach.AttachManager;
import com.viit.base.attach.LocalAttachManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 配置类
 *
 * @author virit
 * @version 2019-10-28
 */
@Configuration
@PropertySource(value = "classpath:application.yml", encoding = "UTF-8")
public class BaseConfiguration {

    private final SystemConfig systemConfig;

    public BaseConfiguration(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    /**
     * 密码加密组件
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置分页
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 配置附件管理器
     */
    @Bean
    public AttachManager attachManager() {

        final String ftpPrefix = "ftp://";
        // ftp服务
        if (systemConfig.attachServer().startsWith(ftpPrefix)) {
            return null;
        } else {
            // 本地存储
            return new LocalAttachManager(systemConfig.attachServer());
        }
    }

    /**
     * websocket端点
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter () {
        return new ServerEndpointExporter();
    }
}
