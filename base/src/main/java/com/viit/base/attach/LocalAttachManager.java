package com.viit.base.attach;

import com.viit.base.util.IOUtils;

import java.io.*;

/**
 * 本地附件管理
 *
 * @author virit
 * @version 2019-11-20
 */
public class LocalAttachManager implements AttachManager {

    /**
     * 文件路径
     */
    private String dirPath;

    public LocalAttachManager(String dirPath) {
        this.dirPath = dirPath;
    }

    @Override
    public InputStream getFileStream(String path) {
        String fullPath = concatPath(dirPath, path);
        InputStream in = null;
        try {
            in =  new FileInputStream(fullPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return in;
    }

    @Override
    public boolean addFile(String path, String fileName, InputStream in) {
        String fullDirPath = concatPath(dirPath, path);
        File dir = new File(fullDirPath);
        // 创建文件夹
        boolean created = true;
        boolean success;
        if (!dir.exists()) {
            created = dir.mkdirs();
        }
        if (created) {
            // 生成文件名
            String fullFilePath = fullDirPath + fileName;
            OutputStream out = null;
            try {
                // 写入文件
                out = new FileOutputStream(fullFilePath);
                IOUtils.writeStream(in, out);
                success = true;
            } catch (IOException e) {
                e.printStackTrace();
                success = false;
            } finally {
                IOUtils.close(in, out);
            }
        } else {
            success = false;
        }
        return success;
    }

    private String concatPath(String pre, String after) {
        return pre + after;
    }
}
