package com.viit.orga.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.viit.base.lang.entity.IdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门
 *
 * @author virit
 * @version 2019-11-12
 */
@Data
@TableName("orga_department")
@EqualsAndHashCode(callSuper = true)
public class Department extends IdEntity {

    /**
     * 上级机构id
     */
    private String parentId;
    /**
     * 机构名称
     */
    private String name;
    /**
     * 机构类型
     */
    private String type;
    /**
     * 机构编码
     */
    private String code;
    /**
     * 排序号
     */
    private Integer orderNum;
}
