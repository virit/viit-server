package com.viit.base.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.viit.base.entity.SysUser;
import com.viit.base.modelview.BaseProfile;
import com.viit.base.modelview.PageQuery;
import com.viit.base.modelview.RestData;
import com.viit.base.modelview.SimpleRestData;
import com.viit.base.service.SysUserService;
import com.viit.base.utils.ContextUtils;
import com.viit.base.utils.FastCrudUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统用户controller
 *
 * @author chentao
 * @version 2019-10-29
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    private final SysUserService sysUserService;

    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 插入操作
     */
    @PostMapping("")
    @PreAuthorize("withAuthority('sys:user:insert')")
    public RestData save(@RequestBody @Validated SysUser sysUser, BindingResult bindingResult) {

        return FastCrudUtils.save(sysUser, sysUserService, bindingResult);
    }

    /**
     * 删除操作
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("withAuthority('sys:user:delete')")
    public RestData delete(@PathVariable("id") String id) {
        return FastCrudUtils.delete(id, sysUserService);
    }

    /**
     * 修改操作
     */
    @PutMapping("/{id}")
    @PreAuthorize("withAuthority('sys:user:update')")
    public RestData update(@PathVariable("id") String id, @RequestBody @Validated SysUser sysUser, BindingResult bindingResult) {
        sysUser.setId(id);
        return FastCrudUtils.update(sysUser, sysUserService, bindingResult);
    }

    /**
     * 查询
     */
    @GetMapping("/{id}")
    @PreAuthorize("withAuthority('sys:user:query')")
    public RestData get(@PathVariable("id") String id) {
        return new SimpleRestData<>().data(sysUserService.getById(id));
    }

    /**
     * 列表数据
     */
    @GetMapping("")
    @PreAuthorize("withAuthority('sys:user:query')")
    public RestData list(SysUserController.SysUserPageQuery pageQuery) {
        return FastCrudUtils.page(pageQuery, sysUserService);
    }

    private static class SysUserPageQuery extends PageQuery<SysUser> {
    }

    /**
     * 当前用户信息
     */
    @GetMapping("/current")
    @JsonView(BaseProfile.class)
    public RestData get() {
        return new SimpleRestData<>().data(ContextUtils.currentUser());
    }
}
