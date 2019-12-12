package com.viit.base.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.viit.utils.lang.DateUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 默认填充类
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
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateDate", DateUtils.currentDate(), metaObject);
    }
}
