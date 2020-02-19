package com.viit.base.io;

import com.viit.base.util.IOUtils;
import com.viit.base.util.ResponseUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;

import static com.google.common.io.ByteStreams.copy;

/**
 * 文件传输模型抽象类
 *
 * @author virit
 * @version 2019-10-31
 */
public class AbstractAttachOutputModel implements AttachOutputModel {

    /**
     * 响应类型
     */
    protected String contentType;
    /**
     * 文件名称
     */
    protected String fileName;
    /**
     * 输入流
     */
    protected InputStream inputStream;

    private static final int BUFFER_SIZE = 512;

    public AbstractAttachOutputModel(String fileName, InputStream inputStream) {
        this.fileName = fileName;
        this.inputStream = inputStream;
    }

    @Override
    public void execute() {
        HttpServletResponse response = ResponseUtils.getResponse();
        if (response == null) {
            return;
        }
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "attachment;fileName=" + this.fileName);
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(inputStream, outputStream);
        }
    }
}
