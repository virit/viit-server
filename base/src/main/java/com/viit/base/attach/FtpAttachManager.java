package com.viit.base.attach;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

/**
 * 基于ftp的文件存储
 *
 * @author virit
 * @version 2019-11-20
 */
public class FtpAttachManager implements AttachManager {

    /**
     * 数据源
     */
    private FtpDatasource datasource;

    private Logger logger = LoggerFactory.getLogger(FtpAttachManager.class);

    public FtpAttachManager(FtpDatasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public InputStream getFileStream(String path) {
        return null;
    }

    @Override
    public boolean addFile(String path, String fileName, InputStream in) {
        return false;
    }

    /**
     * 初始化ftp服务器
     */
    public FTPClient getFTPClient(String ftpHost, int ftpPort, String ftpUserName, String ftpPassword) throws IOException {

        FTPClient ftp = null;
        try {
            ftp = new FTPClient();
            // 连接FPT服务器,设置IP及端口
            ftp.connect(datasource.getHost(), datasource.getPort());
            // 设置用户名和密码
            ftp.login(datasource.getUsername(), datasource.getPassword());
            // 设置连接超时时间,5000毫秒
            ftp.setConnectTimeout(datasource.getTimeout());
            // 设置中文编码集，防止中文乱码
            ftp.setControlEncoding(datasource.getControlEncoding());
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                logger.info("未连接到FTP，用户名或密码错误");
                ftp.disconnect();
            } else {
                logger.info("FTP连接成功");
            }

        } catch (SocketException e) {
            e.printStackTrace();
            logger.info("FTP的IP地址可能错误，请正确配置");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("FTP的端口错误,请正确配置");
        }
        return ftp;
    }
}
