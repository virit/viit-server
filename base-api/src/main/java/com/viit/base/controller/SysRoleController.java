package com.viit.base.controller;

import com.viit.base.entity.SysMenu;
import com.viit.base.entity.SysRole;
import com.viit.base.modelview.PageQuery;
import com.viit.base.modelview.RestData;
import com.viit.base.modelview.SimpleRestData;
import com.viit.base.service.SysMenuService;
import com.viit.base.service.SysRoleService;
import com.viit.base.util.FastCrudUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色controller
 *
 * @author virit
 * @version 2019-10-29
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    private final SysMenuService sysMenuService;

    private final SysRoleService sysRoleService;

    public SysRoleController(SysMenuService sysMenuService, SysRoleService sysRoleService) {
        this.sysMenuService = sysMenuService;
        this.sysRoleService = sysRoleService;
    }

    /**
     * 新增
     */
    @PostMapping("")
    @PreAuthorize("withAuthority('sys:role:add')")
    public RestData save(@Valid @RequestBody SysRole entity, BindingResult bindingResult) {
        return FastCrudUtils.save(entity, sysRoleService, bindingResult);
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("withAuthority('sys:role:delete')")
    public RestData delete(@PathVariable("id") String id) {
        return FastCrudUtils.delete(id, sysRoleService);
    }

    /**
     * 修改
     */
    @PutMapping("/{id}")
    @PreAuthorize("withAuthority('sys:role:update')")
    public RestData update(@Valid @PathVariable("id") String id, @RequestBody SysRole entity, BindingResult bindingResult) {
        entity.setId(id);
        return FastCrudUtils.update(entity, sysRoleService, bindingResult);
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    @PreAuthorize("withAuthority('sys:role:view')")
    public RestData get(@PathVariable("id") String id) {
        return new SimpleRestData<>().data(sysRoleService.getById(id));
    }

    /**
     * 列表查询
     */
    @GetMapping
    @PreAuthorize("withAuthority('sys:role:view')")
    public RestData list(RolePageQuery pageQuery) {
        return FastCrudUtils.page(pageQuery, sysRoleService);
    }

    private static class Menus {
        private List<String> menus;

        List<String> getMenus() {
            return menus;
        }

        void setMenus(List<String> menus) {
            this.menus = menus;
        }
    }

    /**
     * 给角色分配菜单
     *
     * @param id id
     * @return 响应
     */
    @PutMapping("/menus/{id}")
    public RestData assignMenus(@PathVariable("id") String id, @RequestBody Menus menus) {
        sysMenuService.assignMenus(id, menus.getMenus());
        return SimpleRestData.SUCCESS;
    }

    /**
     * 获取角色的所有菜单
     *
     * @param id 角色id
     * @return data
     */
    @GetMapping("/menus/{id}")
    public RestData getMenus(@PathVariable("id") String id) {
        List<SysMenu> sysMenus = sysMenuService.listByRoleId(id);
        return new SimpleRestData<>().data(sysMenus.stream()
                .map(SysMenu::getId).collect(Collectors.toList()));
    }

    /**
     * 分页查询实体
     */
    private static class RolePageQuery extends PageQuery<SysRole> {
    }
}
