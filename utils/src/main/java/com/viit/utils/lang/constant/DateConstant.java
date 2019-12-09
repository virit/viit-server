package com.viit.utils.lang.constant;

/**
 * 日期格式枚举
 *
 * @author chentao
 * @version 2019-10-28
 */
public enum DateConstant {

    /**
     * 日期
     */
    DATE("yyyy-MM-dd"),
    /**
     * 日期时间
     */
    DATE_TIME("yyyy-MM-dd HH:mm:ss");

    /**
     * pattern
     */
    private String pattern;

    DateConstant(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return this.pattern;
    }

    /**
     * 获取格式化字符串
     * @return 格式化字符串
     */
    public String getPattern() {
        return this.pattern;
    }
}
