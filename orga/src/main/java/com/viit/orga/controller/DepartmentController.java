package com.viit.orga.controller;

import com.viit.base.modelview.PageQuery;
import com.viit.base.modelview.RestData;
import com.viit.base.modelview.SimpleRestData;
import com.viit.base.modelview.dto.tree.AbstractTreeModel;
import com.viit.base.modelview.dto.tree.TreeModel;
import com.viit.base.modelview.dto.tree.TreeNode;
import com.viit.orga.entity.Department;
import com.viit.orga.service.DepartmentService;
import com.viit.base.utils.FastCrudUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机构controller
 *
 * @author virit
 * @version 2019-11-20
 */
@RestController
@RequestMapping("/orga/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * 插入操作
     */
    @PostMapping("")
    @PreAuthorize("withAuthority('orga:department:save')")
    public RestData save(@RequestBody @Validated Department department, BindingResult bindingResult) {

        return FastCrudUtils.save(department, departmentService, bindingResult);
    }

    /**
     * 删除操作
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("withAuthority('orga:department:delete')")
    public RestData delete(@PathVariable("id") String id) {
        return FastCrudUtils.delete(id, departmentService);
    }

    /**
     * 修改操作
     */
    @PutMapping("/{id}")
    @PreAuthorize("withAuthority('orga:department:update')")
    public RestData update(@PathVariable("id") String id, @RequestBody @Validated Department deparment, BindingResult bindingResult) {
        deparment.setId(id);
        return FastCrudUtils.update(deparment, departmentService, bindingResult);
    }

    /**
     * 查询
     */
    @GetMapping("/{id}")
    @PreAuthorize("withAuthority('orga:department:query')")
    public RestData get(@PathVariable("id") String id) {
        return new SimpleRestData<>().data(departmentService.getById(id));
    }

    /**
     * 列表数据
     */
    @GetMapping("")
    @PreAuthorize("withAuthority('orga:department:query')")
    public RestData list(DepartmentPageQuery pageQuery) {
        return FastCrudUtils.page(pageQuery, departmentService);
    }

    /**
     * 获取菜单树
     *
     * @param id 节点id
     * @return 模型
     */
    @GetMapping({"/tree", "/tree/{id}"})
    public RestData<TreeModel> model(@PathVariable(value = "id", required = false) String id) {

        List<Department> list = departmentService.listByParentId(id);
        // 创建树模型
        TreeModel model = new AbstractTreeModel<Department>(list) {
            @Override
            public void mapData(Department entity, TreeNode treeNode) {
                treeNode.setId(entity.getId());
                treeNode.setParentId(entity.getParentId());
                treeNode.setLabel(entity.getName());
            }
        };
        return new SimpleRestData<TreeModel>().data(model);
    }

    private static class DepartmentPageQuery extends PageQuery<Department> {}
}
