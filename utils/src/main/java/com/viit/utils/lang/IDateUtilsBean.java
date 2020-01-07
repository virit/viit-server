package com.viit.utils.lang;

import java.util.Date;

/**
 * 日期工具类Bean接口
 *
 * @author virit
 * @version 2019-10-28
 */
public interface IDateUtilsBean {

    /**
     * 获取当前日期
     * @return 当前日期
     */
    Date currentDate();

    /**
     * 格式化日期
     * @param date 日期
     * @param pattern 格式化字符串
     * @return 格式化后的字符串
     */
    String format(Date date, String pattern);
}
