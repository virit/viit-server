package com.viit.base.entity;

import com.viit.base.lang.entity.IdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典表实体
 *
 * @author virit
 * @version 2019-11-1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDict extends IdEntity {

    /**
     * 字典表名称
     */
    private String name;
    /**
     * 字典标识
     */
    private String code;
    /**
     * 字典表描述
     */
    private String description;
    /**
     * 字典类型，普通字典，树型字典
     */
    private Integer type;
}
