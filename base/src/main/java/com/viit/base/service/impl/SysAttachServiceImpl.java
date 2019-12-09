package com.viit.base.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viit.base.attach.AttachManager;
import com.viit.base.config.SystemConfig;
import com.viit.base.entity.SysAttach;
import com.viit.base.mapper.SysAttachMapper;
import com.viit.base.service.SysAttachService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Calendar;

/**
 * 附件service实现类
 *
 * @author virit
 * @version 2019-10-31
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysAttachServiceImpl extends ServiceImpl<SysAttachMapper, SysAttach> implements SysAttachService {

    private final SystemConfig systemConfig;

    private final AttachManager attachManager;

    public SysAttachServiceImpl(SystemConfig systemConfig, AttachManager attachManager) {
        this.systemConfig = systemConfig;
        this.attachManager = attachManager;
    }

    @Override
    public InputStream getStream(String id) {
        return getStream(getById(id));
    }

    @Override
    public InputStream getStream(SysAttach attach) {
        return attachManager.getFileStream(attach.getFilePath() + attach.getName());
    }

    @Override
    public SysAttach addAttach(String userId, String fileName, long length, InputStream in) {
        return addAttach(userId, fileName, length, in, null);
    }

    @Override
    public SysAttach addAttach(String userId, String fileName, long length, InputStream in, String groupId) {
        // 规则根据 用户id/年/月/日/文件名 的格式存储
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String dir = userId + '/' + year + '/' + month + '/' + day + '/';
        attachManager.addFile(dir, fileName, in);
        // 添加附件信息
        SysAttach attach = new SysAttach();
        attach.setName(fileName);
        attach.setUsed(false);
        attach.setFilePath(dir);
        attach.setLength(length);
        if (StringUtils.isBlank(groupId)) {
            attach.setGroupId(IdWorker.get32UUID());
        }
        attach.setExt(getExt(fileName));
        this.save(attach);
        return attach;
    }

    /**
     * 获取扩展名
     */
    private String getExt(String fileName) {
        if (fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf('.') + 1);
        } else {
            return null;
        }
    }
}
