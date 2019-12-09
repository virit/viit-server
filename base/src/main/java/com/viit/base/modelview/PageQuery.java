package com.viit.base.modelview;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * 分页查询封装
 *
 * @param <T> 实体类型
 * @author chentao
 * @version 2019-10-29
 */
@Data
public class PageQuery<T> {

    /**
     * 字段实体
     */
    private T fields;
    /**
     * 分页实体
     */
    private Page<T> page;

    public QueryWrapper<T> getWrapper() {
        return new QueryWrapper<>(fields);
    }
}
