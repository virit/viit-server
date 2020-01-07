package com.viit.base.entity;

import com.viit.base.lang.entity.IdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 附件信息
 *
 * @author virit
 * @version 2019-10-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysAttach extends IdEntity {

    /**
     * 文件名称
     */
    private String name;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件大小
     */
    private Long length;
    /**
     * 文件组id，标识多个文件为一组文件
     */
    private String groupId;
    /**
     * 文件后缀
     */
    private String ext;
    /**
     * 是否使用(是否有业务引用，没有的话会被定期删除)
     */
    private boolean used;
}
