package com.viit.base.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.viit.base.config.SystemConfig;
import com.viit.base.constants.SystemField;
import com.viit.base.entity.SysAttach;
import com.viit.base.entity.SysUser;
import com.viit.base.io.AttachOutputModel;
import com.viit.base.io.AttachViewModel;
import com.viit.base.lang.entity.Record;
import com.viit.base.modelview.BaseProfile;
import com.viit.base.modelview.RestData;
import com.viit.base.modelview.SimpleRestData;
import com.viit.base.service.SysAttachService;
import com.viit.base.utils.ContextUtils;
import com.viit.base.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 附件接口
 *
 * @author virit
 * @version 2019-10-31
 */
@RestController
@RequestMapping("/sys/attach")
public class SysAttachController {

    private final SystemConfig systemConfig;

    private final SysAttachService sysAttachService;

    public static final String CONTENT_URL = "/sys/attach/";

    public SysAttachController(SystemConfig systemConfig, SysAttachService sysAttachService) {
        this.systemConfig = systemConfig;
        this.sysAttachService = sysAttachService;
    }

    /**
     * 获取文件内容/下载文件
     */
    @GetMapping("/{id}")
    public void content(@PathVariable("id") String id) throws IOException {
        InputStream in = null;
        String ext = null;
        if (StringUtils.equals(id, SysUser.DEFAULT_AVATAR)) {
            ClassPathResource resource = new ClassPathResource(SysUser.DEFAULT_AVATAR_PATH);
            in = resource.getInputStream();
            ext = FileUtils.getFileExt(SysUser.DEFAULT_AVATAR_PATH);
        } else {
            // 获取文件流
            SysAttach attach = sysAttachService.getById(id);
            in = sysAttachService.getStream(attach);
            ext = attach.getExt();
        }
        AttachOutputModel model = new AttachViewModel(SysUser.DEFAULT_AVATAR, ext, in);
        model.execute();
    }

    /**
     * 查询
     */
    @GetMapping("/{id}/info")
    @JsonView(BaseProfile.class)
    public RestData get(@PathVariable("id") String id) {
        return new SimpleRestData<>().data(sysAttachService.getById(id));
    }

    /**
     * 上传文件
     */
    @PutMapping("")
    public RestData upload(@RequestPart("file") MultipartFile file, @RequestParam(required = false) String groupId)
            throws IOException {
        // 添加附件
        SysAttach attach = sysAttachService.addAttach(
                ContextUtils.currentUser().getId(),
                file.getOriginalFilename(),
                file.getSize(),
                file.getInputStream(),
                groupId
        );
        // 组装数据
        Record record = new Record();
        record.set(SystemField.ID, attach.getId())
                .set(SystemField.GROUP_ID, attach.getGroupId())
                .set(SystemField.NAME, attach.getName())
                .set(SystemField.URL, CONTENT_URL + attach.getId());
        return new SimpleRestData<Record>().data(record);
    }
}
