package com.viit.base.modelview;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.Collection;

/**
 * page data
 *
 * @param <I> 实体类型
 * @author virit
 * @version 2019-10-29
 */
public class PageData<I> extends SimpleRestData<PageInfo<I>> {

    public PageData(IPage<I> page) {
        PageInfo<I> info = new PageInfo<>();
        info.setRecords(page.getRecords());
        info.setTotal(page.getTotal());
        this.data = info;
    }

    public PageData(Collection<I> collection) {
        PageInfo<I> info = new PageInfo<>();
        info.setRecords(collection);
        info.setTotal(collection.size());
        this.data = info;
    }
}
