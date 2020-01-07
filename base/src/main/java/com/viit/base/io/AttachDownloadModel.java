package com.viit.base.io;

import java.io.InputStream;

/**
 * 文件下载模型
 *
 * @author virit
 * @version 2019-10-31
 */
public class AttachDownloadModel extends AbstractAttachOutputModel {

    public AttachDownloadModel(String fileName, InputStream inputStream) {
        super(fileName, inputStream);
        this.contentType = ContentType.APPLICATION_OCTET_STREAM.getType();
    }
}
