package com.viit.base.controller;

import com.viit.base.entity.SysDictItem;
import com.viit.base.modelview.PageQuery;
import com.viit.base.modelview.RestData;
import com.viit.base.modelview.SimpleRestData;
import com.viit.base.service.SysDictItemService;
import com.viit.base.util.FastCrudUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典项接口
 *
 * @author virit
 * @version 2019-12-04
 */
@RestController
@RequestMapping("/sys/dict-item")
public class SysDictItemController {

    private final SysDictItemService sysDictItemService;

    public SysDictItemController(SysDictItemService sysDictItemService) {
        this.sysDictItemService = sysDictItemService;
    }

    @GetMapping("/code/{code}")
    public RestData listByCode(@PathVariable("code") String code) {
        final String splitter = ",";
        if (code.contains(splitter)) {
            String[] codes = code.split(splitter);
            Map<String, List<SysDictItem>> map = new HashMap<>(1);
            for (String codeItem : codes) {
                map.put(codeItem, sysDictItemService.listItemsByCode(codeItem));
            }
            return new SimpleRestData<>().data(map);
        } else {
            return new SimpleRestData<>().data(sysDictItemService.listItemsByCode(code));
        }
    }

    /**
     * 插入操作
     */
    @PostMapping("")
    @PreAuthorize("withAuthority('sys:dict:insert')")
    public RestData save(@RequestBody @Validated SysDictItem sysDictItem, BindingResult bindingResult) {

        return FastCrudUtils.save(sysDictItem, sysDictItemService, bindingResult);
    }

    /**
     * 删除操作
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("withAuthority('sys:dict:delete')")
    public RestData delete(@PathVariable("id") String id) {
        return FastCrudUtils.delete(id, sysDictItemService);
    }

    /**
     * 修改操作
     */
    @PutMapping("/{id}")
    @PreAuthorize("withAuthority('sys:dict:update')")
    public RestData update(@PathVariable("id") String id, @RequestBody @Validated SysDictItem sysDictItem, BindingResult bindingResult) {
        sysDictItem.setId(id);
        return FastCrudUtils.update(sysDictItem, sysDictItemService, bindingResult);
    }

    /**
     * 查询
     */
    @GetMapping("/{id}")
    @PreAuthorize("withAuthority('sys:dict:query')")
    public RestData get(@PathVariable("id") String id) {
        return new SimpleRestData<>().data(sysDictItemService.getById(id));
    }

    /**
     * 列表数据
     */
    @GetMapping("")
    @PreAuthorize("withAuthority('sys:dict:query')")
    public RestData list(SysDictItemPageQuery pageQuery) {
        return FastCrudUtils.page(pageQuery, sysDictItemService);
    }

    private static class SysDictItemPageQuery extends PageQuery<SysDictItem> {}
}
