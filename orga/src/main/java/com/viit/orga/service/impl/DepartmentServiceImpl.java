package com.viit.orga.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Preconditions;
import com.viit.base.constants.SysDictCode;
import com.viit.orga.entity.Department;
import com.viit.orga.mapper.DepartmentMapper;
import com.viit.orga.service.DepartmentService;
import com.viit.base.service.SysDictService;
import com.viit.base.utils.SysDictGather;
import com.viit.utils.collection.CollectionHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 机构service实现
 *
 * @author virit
 * @version 2019-11-19
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    private final SysDictService sysDictService;

    public DepartmentServiceImpl(SysDictService sysDictService) {
        this.sysDictService = sysDictService;
    }

    @Override
    public List<Department> listByParentId(String id) {
        if (StringUtils.isBlank(id)) {
            return this.list();
        } else {
            Department query = new Department();
            query.setParentId(id);
            QueryWrapper<Department> queryWrapper = new QueryWrapper<>(query);
            return infoFieldsInject(this.list(queryWrapper));
        }
    }

    @Override
    public List<Department> list(Wrapper<Department> queryWrapper) {
        return infoFieldsInject(super.list(queryWrapper));
    }

    @Override
    public IPage<Department> page(IPage<Department> page, Wrapper<Department> queryWrapper) {
        IPage<Department> resultPage = super.page(page, queryWrapper);
        resultPage.setRecords(infoFieldsInject(resultPage.getRecords()));
        return resultPage;
    }

    @Override
    public IPage<Department> page(IPage<Department> page) {
        IPage<Department> resultPage = super.page(page);
        resultPage.setRecords(infoFieldsInject(resultPage.getRecords()));
        return resultPage;
    }

    /**
     * 注入扩展信息字段
     */
    public List<Department> infoFieldsInject(List<Department> list) {
        Preconditions.checkNotNull(list);
        if (list.isEmpty()) {
            return list;
        }
        // 查出所有父级id的信息字段
        Collection<String> parentIds = list.stream().map(Department::getParentId).collect(Collectors.toList());
        Map<Object, Department> parents = listByIds(parentIds).stream()
                .collect(Collectors.toMap(Department::getId, Function.identity()));

        SysDictGather deptGather = sysDictService.gatherWithCode(SysDictCode.DEPT_TYPE);
        CollectionHandler.of(list).forEachWithIndex((index, item) ->
                item.addInfoField("parentIdText",
                        Optional.ofNullable(parents.get(item.getParentId())).map(Department::getName)
                )
                .addInfoField("typeText", deptGather.getText(item.getType()))
        );
        return list;
    }
}
