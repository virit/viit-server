package com.viit.base.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.viit.base.constants.SystemColumn;
import com.viit.base.lang.entity.IdEntity;
import com.viit.base.modelview.*;
import org.springframework.validation.BindingResult;

import java.io.Serializable;
import java.util.List;

/**
 * 快速增删改查工具
 *
 * @author virit
 * @version 2019-10-29
 */
public class FastCrudUtils {

    /**
     * 快速新增
     *
     * @param entity 实体
     * @param service service
     * @param bindingResult 校验结果
     * @return json视图
     */
    public static <T extends IdEntity> RestData save(T entity, IService<T> service, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new FormErrorData(bindingResult);
        }
        boolean result = service.save(entity);
        if (result) {
            return new KeyValueData().put("id", entity.getId());
        }
        return new SimpleRestData().resultCode(ResultCode.ERROR);
    }

    /**
     * 快速更新
     *
     * @param entity 实体
     * @param service service
     * @param bindingResult 校验结果
     * @return json视图
     */
    public static <T extends IdEntity> RestData update(T entity, IService<T> service, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new FormErrorData(bindingResult);
        }
        boolean result = service.updateById(entity);
        return new SimpleRestData();
    }

    /**
     * 快速删除
     * @param id id
     * @param service service
     * @param <T> 实体类型
     * @return json视图
     */
    public static <T extends IdEntity> RestData delete(Serializable id, IService<T> service) {
        boolean result = service.removeById(id);
        if (result) {
            // 删除成功
            return new SimpleRestData().msg(StringConstant.DELETE_SUCCESS);
        } else {
            // 删除失败
            return new SimpleRestData().resultCode(ResultCode.ERROR);
        }
    }

    /**
     * 快速分页查询
     * @param query 查询条件
     * @param service service
     * @param <T> 实体类型
     * @return json视图
     */
    public static <T extends IdEntity> RestData page(PageQuery<T> query, IService<T> service) {
        query.getWrapper().orderByDesc(SystemColumn.CREATE_DATE);
        if (query.getPage() != null) {
            IPage<T> list = service.page(query.getPage(), query.getWrapper());
            return new PageData<>(list);
        } else {
            List<T> list = service.list(query.getWrapper());
            return new PageData<>(list);
        }
    }
}
