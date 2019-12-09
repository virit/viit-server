package com.viit.base.controller;

import com.viit.base.entity.SysMenu;
import com.viit.base.enums.SysMenuType;
import com.viit.base.modelview.PageQuery;
import com.viit.base.modelview.RestData;
import com.viit.base.modelview.SimpleRestData;
import com.viit.base.modelview.dto.tree.AbstractTreeModel;
import com.viit.base.modelview.dto.tree.TreeModel;
import com.viit.base.modelview.dto.tree.TreeNode;
import com.viit.base.service.SysMenuService;
import com.viit.base.utils.ContextUtils;
import com.viit.base.utils.FastCrudUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统菜单接口
 *
 * @author chentao
 * @version 2019-10-31
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {

    private final SysMenuService sysMenuService;

    public SysMenuController(SysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    /**
     * 插入操作
     */
    @PostMapping("")
    @PreAuthorize("hasRole('super')")
    public RestData save(@Valid @RequestBody SysMenu entity, BindingResult bindingResult) {
        return FastCrudUtils.save(entity, sysMenuService, bindingResult);
    }

    /**
     * 删除操作
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('super')")
    public RestData delete(@PathVariable("id") String id) {
        return FastCrudUtils.delete(id, sysMenuService);
    }

    /**
     * 修改操作
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('super')")
    public RestData update(@PathVariable("id") String id, @Valid @RequestBody SysMenu sysMenu, BindingResult bindingResult) {
        sysMenu.setId(id);
        return FastCrudUtils.update(sysMenu, sysMenuService, bindingResult);
    }

    /**
     * 查询
     */
    @GetMapping("/{id}")
    public RestData get(@PathVariable("id") String id) {
        return new SimpleRestData<>().data(sysMenuService.getById(id));
    }

    /**
     * 列表数据
     */
    @GetMapping("")
    public RestData list(SysMenuPageQuery pageQuery) {
        return FastCrudUtils.page(pageQuery, sysMenuService);
    }

    /**
     * 获取菜单树
     *
     * @param id 节点id
     * @return 模型
     */
    @GetMapping({"/tree", "/tree/{id}"})
    public RestData<TreeModel> model(@PathVariable(value = "id", required = false) String id) {

        List<SysMenu> list = sysMenuService.listByParentId(id);
        // 创建树模型
        TreeModel model = new AbstractTreeModel<SysMenu>(list) {

            @Override
            public void mapData(SysMenu entity, TreeNode treeNode) {
                treeNode.setId(entity.getId());
                treeNode.setParentId(entity.getParentId());
                treeNode.setLabel(entity.getTitle());
            }
        };
        return new SimpleRestData<TreeModel>().data(model);
    }

    /**
     * 获取路由树
     *
     * @return 模型
     */
    @GetMapping("/router")
    public RestData<TreeModel> router() {
        List<SysMenu> list = sysMenuService.listByUserId(ContextUtils.currentUser().getId())
                .stream().filter(sysMenu -> SysMenuType.MENU == sysMenu.getType())
                .collect(Collectors.toList());
        // 创建树模型
        TreeModel model = new AbstractTreeModel<SysMenu>(list) {

            @Override
            public void mapData(SysMenu menu, TreeNode treeNode) {
                Map<String, String> meta = new HashMap<>(2);
                meta.put("title", menu.getTitle());
                meta.put("icon", menu.getIcon());
                treeNode.set("component", menu.getUrl())
                        .set("meta", meta);
                treeNode.setId(menu.getId());
                treeNode.setParentId(menu.getParentId());
            }

            @Override
            public String[] getExcludeFields() {
                return new String[]{"id", "parentId"};
            }
        };
        return new SimpleRestData<TreeModel>().data(model);
    }

    private static class SysMenuPageQuery extends PageQuery<SysMenu> {
    }
}
