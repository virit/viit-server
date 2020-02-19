package com.viit.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 注入属性的service实现
 * @param <M> Mapper
 * @param <T> 实体
 */
public abstract class FieldsInjectServiceImpl<M extends BaseMapper<T>, T> extends BaseServiceImpl<M, T> {

    public abstract List<T> infoFieldsInject(List<T> list);

    @Override
    public <E extends IPage<T>> E page(E page, Wrapper<T> queryWrapper) {
        E resultPage = super.page(page, queryWrapper);
        resultPage.setRecords(infoFieldsInject(resultPage.getRecords()));
        return resultPage;
    }

    @Override
    public <E extends IPage<T>> E page(E page) {
        E resultPage = super.page(page);
        resultPage.setRecords(infoFieldsInject(resultPage.getRecords()));
        return resultPage;
    }

    /*
    @Override
    public IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper) {
        IPage<T> resultPage = super.page(page, queryWrapper);
        resultPage.setRecords(infoFieldsInject(resultPage.getRecords()));
        return resultPage;
    }

    @Override
    public IPage<T> page(IPage<T> page) {
        IPage<T> resultPage = super.page(page);
        resultPage.setRecords(infoFieldsInject(resultPage.getRecords()));
        return resultPage;
    }*/

    @Override
    public List<T> list(Wrapper<T> queryWrapper) {
        return infoFieldsInject(super.list(queryWrapper));
    }

    @Override
    public T getById(Serializable id) {
        return infoFieldsInject(Collections.singletonList(super.getById(id))).get(0);
    }
}
