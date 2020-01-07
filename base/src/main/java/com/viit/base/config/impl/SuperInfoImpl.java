package com.viit.base.config.impl;

import com.viit.base.config.SuperInfo;
import lombok.Setter;

/**
 * 超级用户信息实现类
 *
 * @author virit
 * @version 2019-12-28
 */
@Setter
public class SuperInfoImpl implements SuperInfo {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否启用
     */
    private boolean enabled = false;

    @Override
    public String username() {
        return this.username;
    }

    @Override
    public String password() {
        return this.password;
    }

    @Override
    public boolean enabled() {
        return this.enabled;
    }
}
