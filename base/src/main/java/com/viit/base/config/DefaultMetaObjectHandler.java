package com.viit.base.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.viit.base.utils.ContextUtils;
import com.viit.utils.lang.DateUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 默认的填充类
 *
 * @author virit
 * @version 2019-12-12
 */
@Component
public class DefaultMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = DateUtils.currentDate();
        setFieldValByName("createDate", date, metaObject);
        setFieldValByName("updateDate", date, metaObject);
        String userId = ContextUtils.currentUser().getId();
        setFieldValByName("createUserId", userId, metaObject);
        setFieldValByName("updateUserId", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateDate", DateUtils.currentDate(), metaObject);
        String userId = ContextUtils.currentUser().getId();
        setFieldValByName("updateUserId", userId, metaObject);
    }
}
