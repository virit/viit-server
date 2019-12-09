package com.viit.utils.lang;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.viit.utils.lang.constant.DateConstant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * 日期工具类Bean
 *
 * @author chentao
 * @version 2019-10-28
 */
class DateUtilsBean implements IDateUtilsBean {

    private static final Cache<String, DateFormat> PATTERN_CACHE;

    private static final int INITIAL_CAPACITY = 10;

    static {
        // 将常用的格式化置入缓存
        PATTERN_CACHE = CacheBuilder.newBuilder().initialCapacity(INITIAL_CAPACITY).build();
        DateFormat dateFormat = new SimpleDateFormat(DateConstant.DATE.getPattern());
        PATTERN_CACHE.put(DateConstant.DATE.getPattern(), dateFormat);
        DateFormat datetimeFormat = new SimpleDateFormat(DateConstant.DATE_TIME.getPattern());
        PATTERN_CACHE.put(DateConstant.DATE_TIME.getPattern(), datetimeFormat);
    }

    @Override
    public Date currentDate() {
        return new Date();
    }

    @Override
    public String format(Date date, String pattern) {
        DateFormat format = null;
        try {
            format = PATTERN_CACHE.get(pattern, () -> new SimpleDateFormat(pattern));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (format != null) {
            return format.format(date);
        } else {
            return null;
        }
    }
}
