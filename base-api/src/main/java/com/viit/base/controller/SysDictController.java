package com.viit.base.controller;

import com.viit.base.entity.SysDict;
import com.viit.base.modelview.PageQuery;
import com.viit.base.modelview.RestData;
import com.viit.base.modelview.SimpleRestData;
import com.viit.base.service.SysDictService;
import com.viit.base.util.FastCrudUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 字典表接口
 *
 * @author virit
 * @version 2019-11-01
 */
@RestController
@RequestMapping("/sys/dict")
public class SysDictController {

    private final SysDictService sysDictService;

    public SysDictController(SysDictService sysDictService) {
        this.sysDictService = sysDictService;
    }

    /**
     * 插入操作
     */
    @PostMapping("")
    @PreAuthorize("withAuthority('sys:dict:insert')")
    public RestData save(@RequestBody @Validated SysDict sysDict, BindingResult bindingResult) {

        return FastCrudUtils.save(sysDict, sysDictService, bindingResult);
    }

    /**
     * 删除操作
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("withAuthority('sys:dict:delete')")
    public RestData delete(@PathVariable("id") String id) {
        return FastCrudUtils.delete(id, sysDictService);
    }

    /**
     * 修改操作
     */
    @PutMapping("/{id}")
    @PreAuthorize("withAuthority('sys:dict:update')")
    public RestData update(@PathVariable("id") String id, @RequestBody @Validated SysDict sysDict, BindingResult bindingResult) {
        sysDict.setId(id);
        return FastCrudUtils.update(sysDict, sysDictService, bindingResult);
    }

    /**
     * 查询
     */
    @GetMapping("/{id}")
    @PreAuthorize("withAuthority('sys:dict:query')")
    public RestData get(@PathVariable("id") String id) {
        return new SimpleRestData<>().data(sysDictService.getById(id));
    }

    /**
     * 列表数据
     */
    @GetMapping("")
    @PreAuthorize("withAuthority('sys:dict:query')")
    public RestData list(SysDictPageQuery pageQuery) {
        return FastCrudUtils.page(pageQuery, sysDictService);
    }

    private static class SysDictPageQuery extends PageQuery<SysDict> {}
}
