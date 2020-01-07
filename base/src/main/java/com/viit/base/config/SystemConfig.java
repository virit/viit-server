package com.viit.base.config;

import com.viit.base.config.impl.SuperInfoImpl;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 系统配置类
 *
 * @author virit
 * @version 2019-10-31
 */
@Data
@Component
@ConfigurationProperties(prefix = "system")
public class SystemConfig {

    private SuperInfoImpl superInfo;

    private String attachServer = "";

    /**
     * 获取超级用户信息
     */
    public SuperInfo superInfo () {
        return this.superInfo;
    }

    /**
     * 获取附件存储服务
     */
    public String attachServer() {
        return this.attachServer;
    }
}
