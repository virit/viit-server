package com.viit.utils.lang;

import java.util.Date;

/**
 * 日期工具类
 *
 * @author virit
 * @version 2019-10-28
 */
public class DateUtils {

    /**
     * 格式化bean
     */
    private static IDateUtilsBean dateUtilsBean = new DateUtilsBean();

    /**
     * @see IDateUtilsBean
     * @return 当前日期
     */
    public static Date currentDate() {
        return dateUtilsBean.currentDate();
    }
}
