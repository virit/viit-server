package com.viit.bpmf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.viit.base.constant.SystemField;
import com.viit.bpmf.entity.ModelEntityWrapper;
import com.viit.bpmf.service.ProcessModelService;
import com.viit.utils.bean.BeanUtils;
import com.viit.base.modelview.*;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

/**
 * 流程模型controller
 *
 * @author virit
 * @version 2019-12-05
 */
@RestController
@RequestMapping("/bpmf/model")
public class ProcessModelController {

    private final RepositoryService repositoryService;

    private final ProcessModelService processModelService;

    public ProcessModelController(RepositoryService repositoryService, ProcessModelService processModelService) {

        this.repositoryService = repositoryService;
        this.processModelService = processModelService;
    }

    /**
     * 保存模型
     */
    @PostMapping
    public RestData save(@RequestBody @Valid ModelEntityWrapper modelEntity, BindingResult bindingResult)
            throws IllegalAccessException, InvocationTargetException {

        if (bindingResult.hasErrors()) {
            return new FormErrorData(bindingResult);
        }
        Model model = repositoryService.newModel();
        BeanUtils.copyFields(modelEntity, model);
        repositoryService.saveModel(model);
        return new KeyValueData().put(SystemField.ID, model.getId());
    }

    /**
     * 上传流程图
     */
    @PostMapping("/editor-source/{id}")
    public RestData saveEditorSource(@PathVariable("id") String id, @RequestBody String jsonXml) {

        JSONObject jsonObject = JSON.parseObject(jsonXml);
        String sourceXml = jsonObject.getString("xml");
        String svgXml = jsonObject.getString("svg");
        processModelService.addModelSourceAndExtra(id, sourceXml, svgXml);
        return SimpleRestData.SUCCESS;
    }

    /**
     * 获取流程图
     */
    @GetMapping("/editor-source/{id}")
    public RestData getEditorSource(@PathVariable("id") String id) {

        return new SimpleRestData<>().data(
                Optional.ofNullable(repositoryService.getModelEditorSource(id)).map(String::new).orElse(null)
        );
    }

    @GetMapping("/{id}")
    public RestData get(@PathVariable("id") String id) {

        return new SimpleRestData<ModelEntityWrapper>().data(processModelService.getById(id));
    }

    private static class ModelPageQuery extends PageQuery<ModelEntityWrapper> {
    }

    /**
     * 列表数据
     */
    @GetMapping
    public RestData list(ModelPageQuery pageQuery) {

        if (pageQuery.getPage() != null) {
            return new PageData<>(processModelService.page(pageQuery.getPage(), pageQuery.getWrapper()));
        } else {
            return new PageData<>(processModelService.list(pageQuery.getWrapper()));
        }
    }

    /**
     * 删除模型
     */
    @DeleteMapping("/{id}")
    public RestData delete(@PathVariable("id") String id) {
        repositoryService.deleteModel(id);
        return SimpleRestData.SUCCESS;
    }
}
