package com.viit.orga.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viit.base.utils.objects.OrderObject;
import com.viit.orga.entity.Department;

import java.util.List;

/**
 * 机构service
 *
 * @author virit
 * @version 2019-11-19
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 根据父级id获取
     * @param id 父级id
     * @return 列表
     */
    List<Department> listByParentId(String id);

    /**
     * 保存排序
     * @param items 排序列表
     */
    void saveOrder(List<OrderObject> items);
}
