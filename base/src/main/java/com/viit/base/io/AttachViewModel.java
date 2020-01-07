package com.viit.base.io;

import java.io.InputStream;

/**
 * 文件model
 *
 * @author virit
 * @version 2019-11-01
 */
public class AttachViewModel extends AbstractAttachOutputModel {

    public AttachViewModel(String fileName, String ext, InputStream inputStream) {
        super(fileName, inputStream);
        this.contentType = ContentType.getTypeByExt(ext);
    }
}
