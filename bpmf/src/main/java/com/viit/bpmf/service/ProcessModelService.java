package com.viit.bpmf.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.viit.bpmf.entity.ModelEntityWrapper;

import java.util.List;

/**
 * 流程模型service
 *
 * @author virit
 * @version 2019-12-18
 */
public interface ProcessModelService {

    /**
     * 分页查询
     */
    IPage<ModelEntityWrapper> page(Page<ModelEntityWrapper> page, QueryWrapper<ModelEntityWrapper> queryWrapper);

    /**
     * 查询列表
     */
    List<ModelEntityWrapper> list(QueryWrapper<ModelEntityWrapper> queryWrapper);

    /**
     * 根据id查询
     */
    ModelEntityWrapper getById(String id);

    /**
     * 添加流程定义与流程图
     */
    void addModelSourceAndExtra(String id, String source, String extra);
}
