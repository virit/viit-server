package com.viit.base.util;

import com.google.common.base.Preconditions;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * io工具类
 *
 * @author virit
 * @version 2019-10-31
 */
public class IOUtils {

    /**
     * 缓冲区大小
     */
    public static final int BUFFER_SIZE = 512;

    /**
     * 关闭流
     */
    public static void close(Closeable... closeableArray) {
        if (closeableArray == null) {
            return;
        }
        try {
            for (Closeable closeable : closeableArray) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 流写入
     * @param in 输入流
     * @param out 输入流
     */
    public static void writeStream(InputStream in, OutputStream out) throws IOException {
        Preconditions.checkNotNull(in, out);
        byte[] buffer = new byte[BUFFER_SIZE];
        int len = -1;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
    }
}
