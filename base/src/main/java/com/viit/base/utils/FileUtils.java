package com.viit.base.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

/**
 * 文件工具类
 *
 * @author virit
 * @version 2019-10-31
 */
public class FileUtils {

    /**
     * 获取文件后缀名
     * @param fileName 文件名
     * @return 文件后缀
     */
    public static String getFileExt(String fileName) {
        Preconditions.checkNotNull(fileName);
        return StringUtils.substringAfter(fileName, ".");
    }
}
