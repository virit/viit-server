package com.viit.base.controller;

import com.viit.base.entity.SysRoleType;
import com.viit.base.modelview.PageQuery;
import com.viit.base.modelview.RestData;
import com.viit.base.modelview.SimpleRestData;
import com.viit.base.service.SysRoleTypeService;
import com.viit.base.utils.FastCrudUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统角色类型controller
 *
 * @author chentao
 * @version 2019-10-29
 */
@RestController
@RequestMapping("/sys/role-type")
public class SysRoleTypeController {

    private final SysRoleTypeService sysDictService;

    public SysRoleTypeController(SysRoleTypeService sysDictService) {
        this.sysDictService = sysDictService;
    }

    /**
     * 插入操作
     */
    @PostMapping("")
    @PreAuthorize("withAuthority('sys:role:insert')")
    public RestData save(@RequestBody @Validated SysRoleType sysDict, BindingResult bindingResult) {

        return FastCrudUtils.save(sysDict, sysDictService, bindingResult);
    }

    /**
     * 删除操作
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("withAuthority('sys:role:delete')")
    public RestData delete(@PathVariable("id") String id) {
        return FastCrudUtils.delete(id, sysDictService);
    }

    /**
     * 修改操作
     */
    @PutMapping("/{id}")
    @PreAuthorize("withAuthority('sys:role:update')")
    public RestData update(@PathVariable("id") String id, @RequestBody @Validated SysRoleType sysDict, BindingResult bindingResult) {
        sysDict.setId(id);
        return FastCrudUtils.update(sysDict, sysDictService, bindingResult);
    }

    /**
     * 查询
     */
    @GetMapping("/{id}")
    @PreAuthorize("withAuthority('sys:role:query')")
    public RestData get(@PathVariable("id") String id) {
        return new SimpleRestData<>().data(sysDictService.getById(id));
    }

    /**
     * 列表数据
     */
    @GetMapping("")
    @PreAuthorize("withAuthority('sys:role:query')")
    public RestData list(SysRoleTypePageQuery pageQuery) {
        return FastCrudUtils.page(pageQuery, sysDictService);
    }

    private static class SysRoleTypePageQuery extends PageQuery<SysRoleType> {
    }
}
