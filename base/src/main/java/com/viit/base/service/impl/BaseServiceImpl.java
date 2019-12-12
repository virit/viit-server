package com.viit.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T>  {

    @Override
    public boolean removeById(Serializable id) {
        String ids = String.valueOf(id);
        String split = ",";
        if (ids.contains(split)) {
            Collection<String> idCollection = Arrays.asList(ids.split(split));
            return removeByIds(idCollection);
        } else {
            return super.removeById(id);
        }
    }
}
