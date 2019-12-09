package com.viit.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 系统配置类
 *
 * @author virit
 * @version 2019-10-31
 */
@Component
@PropertySource("classpath:system.properties")
public class SystemConfig {

    /**
     * 超级用户用户名
     */
    @Value("${super.username}")
    private String superUsername;
    /**
     * 超级用户密码
     */
    @Value("${super.password}")
    private String superPassword;
    /**
     * 文件存储地址
     */
    @Value("${system.file.path}")
    private String filePath;

    public String getSuperUsername() {
        return this.superUsername;
    }

    public String getSuperPassword() {
        return this.superPassword;
    }

    public String getFilePath() {
        return this.filePath;
    }
}
