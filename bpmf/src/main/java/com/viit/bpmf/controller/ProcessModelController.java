package com.viit.bpmf.controller;

import com.alibaba.fastjson.JSON;
import com.viit.base.constant.SystemField;
import com.viit.base.modelview.*;
import com.viit.bpmf.entity.ModelEntityWrapper;
import com.viit.bpmf.entity.ModelJson;
import com.viit.bpmf.service.ProcessModelService;
import com.viit.utils.bean.BeanUtils;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final RuntimeService runtimeService;

    public ProcessModelController(RepositoryService repositoryService, ProcessModelService processModelService, RuntimeService runtimeService) {

        this.repositoryService = repositoryService;
        this.processModelService = processModelService;
        this.runtimeService = runtimeService;
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
     * 保存模型
     */
    @PutMapping("/{id}")
    public RestData update(@PathVariable("id") String id, @RequestBody @Valid ModelEntityWrapper modelEntity,
                           BindingResult bindingResult)
            throws IllegalAccessException, InvocationTargetException {

        if (bindingResult.hasErrors()) {
            return new FormErrorData(bindingResult);
        }
        Model model = repositoryService.getModel(id);
        model.setName(modelEntity.getName());
        model.setKey(modelEntity.getKey());
        repositoryService.saveModel(model);
        return new KeyValueData().put(SystemField.ID, model.getId());
    }

    /**
     * 上传流程图
     */
    @PostMapping("/editor-source/{id}")
    public RestData saveEditorSource(@PathVariable("id") String id, @RequestBody String xmlAndJson) {

        String[] splits = xmlAndJson.split("__SPLIT__");
        processModelService.addModelSource(id, splits[0], splits[1]);
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

    /**
     * 部署流程
     */
    @PostMapping("/deploy/{id}")
    public RestData deploy(@PathVariable("id") String id) {
        Model model = repositoryService.getModel(id);
        Deployment deployment = repositoryService.createDeployment()
                .name(model.getName())
                .addString(model.getKey() + ".bpmn",
                        new String(repositoryService.getModelEditorSource(id)))
                .deploy();
        return SimpleRestData.SUCCESS;
    }

    /**
     * 获取json model
     */
    @GetMapping("/editor-source/json/{id}")
    public RestData getEditorJsonSource(@PathVariable("id") String id) {

        return new SimpleRestData<>().data(JSON.parseObject(
                Optional.ofNullable(processModelService.getModelJson(id)).map(ModelJson::getJsonData).orElse("")
        ));
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
