package com.viit.base.controller;

import com.viit.base.enums.SysEnumItem;
import com.viit.base.modelview.RestData;
import com.viit.base.modelview.SimpleRestData;
import com.viit.base.enums.SysMenuType;
import com.viit.base.utils.EasyMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 系统枚举
 *
 * @author virit
 * @version 2019-11-01
 */
@RestController
@RequestMapping("/sys/enum")
public class SysEnumController {

    private Map<String, List<SysEnumItem>> enumMap;

    public SysEnumController() {
        enumMap = new EasyMap<String, List<SysEnumItem>>()
                // 系统菜单类型
                .set("sys-menu-type", SysMenuType.ITEMS);
    }

    @GetMapping("/{type}")
    public RestData get(@PathVariable("type") String type) {
        return new SimpleRestData<>().data(enumMap.get(type));
    }
}
