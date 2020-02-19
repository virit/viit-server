package com.viit.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viit.base.entity.SysAttach;

import java.io.InputStream;

/**
 * 附件service
 *
 * @author virit
 * @version 2019-10-31
 */
public interface SysAttachService extends IService<SysAttach> {

    /**
     * 获取文件流
     *
     * @param id 附件id
     * @return 文件流
     */
    InputStream getStream(String id);

    /**
     * 获取文件流
     *
     * @param attach attach
     */
    InputStream getStream(SysAttach attach);

    /**
     * 添加附件
     *
     * @return 附件信息
     */
    SysAttach addAttach(String userId, String fileName, long length, InputStream in);

    /**
     * 添加附件
     *
     * @return 附件信息
     */
    SysAttach addAttach(String userId, String fileName, long length, InputStream in, String groupId);
}
