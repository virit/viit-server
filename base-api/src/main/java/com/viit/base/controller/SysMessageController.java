package com.viit.base.controller;

import com.viit.base.entity.SysMessage;
import com.viit.base.modelview.PageData;
import com.viit.base.modelview.PageQuery;
import com.viit.base.modelview.RestData;
import com.viit.base.service.SysMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 消息接口
 *
 * @author virit
 * @version 2019-12-06
 */
@RestController
@RequestMapping("/sys/message")
public class SysMessageController {

    private final SysMessageService sysMessageService;

    public SysMessageController(SysMessageService sysMessageService) {
        this.sysMessageService = sysMessageService;
    }

    private static class SysMessagePageQuery extends PageQuery<SysMessage> {}

    @GetMapping
    public RestData list(SysMessagePageQuery pageQuery) {
        if (pageQuery.getPage() != null) {
            return new PageData<>(sysMessageService.page(pageQuery.getPage(), pageQuery.getWrapper()));
        } else {
            return new PageData<>(sysMessageService.list(pageQuery.getWrapper()));
        }
    }
}
