package com.viit.base.attach;

import java.io.InputStream;

/**
 * 附件管理器
 *
 * @author virit
 * @version 2019-1120
 */
public interface AttachManager {

    /**
     * 获取文件流
     * @param path 路径
     * @return 输入流
     */
    InputStream getFileStream(String path);

    /**
     * 添加文件
     * @param path 路径
     * @param in 输入流
     * @return 是否成功
     */
    boolean addFile(String path, String fileName, InputStream in);
}
