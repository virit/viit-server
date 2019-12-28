package com.viit.base.config;

/**
 * 超级用户信息
 *
 * @author virit
 * @version 2019-12-28
 */
public interface SuperInfo {

    /**
     * 用户名
     */
    String username();

    /**
     * 密码
     */
    String password();

    /**
     * 是否可用
     */
    boolean enabled();
}
