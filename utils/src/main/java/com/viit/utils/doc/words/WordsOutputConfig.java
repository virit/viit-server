package com.viit.utils.doc.words;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文档输出配置
 *
 * @author virit
 * @version 2019-11-28
 */
@Getter
@Builder(builderClassName = "Builder")
public class WordsOutputConfig {

    /**
     * 数据
     */
    private MailMergeData data;
    /**
     * 输入流
     */
    private InputStream inputStream;
    /**
     * 输出流
     */
    private OutputStream outputStream;
    /**
     * 格式
     */
    private int saveFormat;
}
