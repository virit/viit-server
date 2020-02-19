package com.viit.orga.controller;

import com.viit.base.modelview.PageQuery;
import com.viit.base.modelview.RestData;
import com.viit.base.modelview.SimpleRestData;
import com.viit.orga.entity.Staff;
import com.viit.orga.service.StaffService;
import com.viit.base.util.FastCrudUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 员工信息controller
 *
 * @author virit
 * @version 2019-11-20
 */
@RestController
@RequestMapping("/orga/staff")
public class StaffController {

    private final StaffService service;

    public StaffController(StaffService service) {
        this.service = service;
    }

    @PostMapping("")
    public RestData save(@RequestBody @Validated Staff entity, BindingResult bindingResult) {

        return FastCrudUtils.save(entity, service, bindingResult);
    }

    /**
     * 删除操作
     */
    @DeleteMapping("/{id}")
    public RestData delete(@PathVariable("id") String id) {
        return FastCrudUtils.delete(id, service);
    }

    /**
     * 修改操作
     */
    @PutMapping("/{id}")
    public RestData update(@PathVariable("id") String id, @RequestBody @Validated Staff entity, BindingResult bindingResult) {
        entity.setId(id);
        return FastCrudUtils.update(entity, service, bindingResult);
    }

    /**
     * 查询
     */
    @GetMapping("/{id}")
    public RestData get(@PathVariable("id") String id) {
        return new SimpleRestData<>().data(service.getById(id));
    }

    /**
     * 列表数据
     */
    @GetMapping("")
    public RestData list(StaffPageQuery pageQuery) {
        return FastCrudUtils.page(pageQuery, service);
    }

    public class StaffPageQuery extends PageQuery<Staff> {}
}
