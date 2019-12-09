package com.viit.base.attach;

import lombok.Builder;
import lombok.Getter;

/**
 * ftp数据源
 *
 * @author virit
 * @version 2019-11-20
 */
@Builder
@Getter
public class FtpDatasource {

    /**
     * 主机地址
     */
    private String host;
    /**
     * 端口
     */
    private int port;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 超时时间
     */
    private int timeout = 50000;
    /**
     * 字符集
     */
    private String controlEncoding = "UTF-8";
}
