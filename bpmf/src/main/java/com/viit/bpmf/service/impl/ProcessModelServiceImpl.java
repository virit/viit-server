package com.viit.bpmf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;
import com.viit.base.service.SysDictService;
import com.viit.base.utils.SysDictGather;
import com.viit.bpmf.constants.BpmfDictCode;
import com.viit.bpmf.entity.ModelEntityWrapper;
import com.viit.bpmf.service.ProcessModelService;
import com.viit.utils.bean.BeanUtils;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 流程模型service实现类
 *
 * @author virit
 * @version 2019-12-18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProcessModelServiceImpl implements ProcessModelService {

    private final RepositoryService repositoryService;

    private final SysDictService sysDictService;

    private Logger logger = LoggerFactory.getLogger(ProcessModelServiceImpl.class);

    public ProcessModelServiceImpl(RepositoryService repositoryService, SysDictService sysDictService) {
        this.repositoryService = repositoryService;
        this.sysDictService = sysDictService;
    }

    private ModelQuery createModelQuery(QueryWrapper<ModelEntityWrapper> queryWrapper) {
        ModelQuery modelQuery = repositoryService.createModelQuery();
        ModelEntityWrapper entity = queryWrapper.getEntity();
        if (entity != null) {
            if (StringUtils.isNotBlank(entity.getKey())) {
                modelQuery.modelKey(entity.getKey());
            }
            if (StringUtils.isNotBlank(entity.getName())) {
                modelQuery.modelNameLike(entity.getName());
            }
        }
        return modelQuery;
    }

    @Override
    public IPage<ModelEntityWrapper> page(Page<ModelEntityWrapper> page
            , QueryWrapper<ModelEntityWrapper> queryWrapper) {

        ModelQuery modelQuery = createModelQuery(queryWrapper);

        // 查询并转换为包装实体
        List<ModelEntityWrapper> list =
                modelQuery.listPage((int) ((page.getCurrent() - 1) * page.getSize()), (int) page.getSize())
                        .stream().map(model -> {
                    ModelEntityWrapper modelEntityWrapper = new ModelEntityWrapper();
                    try {
                        BeanUtils.copyFields(model, modelEntityWrapper);
                    } catch (InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                        logger.error(e.getMessage());
                    }
                    return modelEntityWrapper;
                }).collect(Collectors.toList());
        long count = modelQuery.count();
        Page<ModelEntityWrapper> resultPage = new Page<>();
        resultPage.setRecords(infoFieldsInject(list));
        resultPage.setTotal(count);
        resultPage.setCurrent(page.getCurrent());
        return resultPage;
    }

    @Override
    public List<ModelEntityWrapper> list(QueryWrapper<ModelEntityWrapper> queryWrapper) {

        ModelQuery modelQuery = createModelQuery(queryWrapper);

        // 查询并转换为包装实体
        List<ModelEntityWrapper> list = modelQuery.list().stream().map(model -> {
            ModelEntityWrapper modelEntityWrapper = new ModelEntityWrapper();
            try {
                BeanUtils.copyFields(model, modelEntityWrapper);
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
            return modelEntityWrapper;
        }).collect(Collectors.toList());
        return infoFieldsInject(list);
    }

    @Override
    public ModelEntityWrapper getById(String id) {

        Model model = repositoryService.getModel(id);
        if (model == null) {
            return null;
        }
        ModelEntityWrapper modelEntityWrapper = new ModelEntityWrapper();
        try {
            BeanUtils.copyFields(model, modelEntityWrapper);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return infoFieldsInject(Collections.singletonList(modelEntityWrapper)).get(0);
    }

    @Override
    public void addModelSourceAndExtra(String id, String source, String extra) {
        repositoryService.addModelEditorSource(id, source.getBytes());
        repositoryService.addModelEditorSourceExtra(id, extra.getBytes());
    }

    private List<ModelEntityWrapper> infoFieldsInject(List<ModelEntityWrapper> list) {
        Preconditions.checkNotNull(list);
        SysDictGather processCategory = sysDictService.gatherWithCode(BpmfDictCode.PROCESS_CATEGORY);
        for (ModelEntityWrapper modelEntityWrapper : list) {
            modelEntityWrapper.addInfoField("categoryText"
                    , processCategory.getText(modelEntityWrapper.getCategory()));
        }
        return list;
    }
}
