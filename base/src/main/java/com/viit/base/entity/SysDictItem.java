package com.viit.base.entity;

import com.viit.base.lang.entity.IdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 字典表子项
 *
 * @author virit
 * @version 2019-11-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDictItem extends IdEntity {

    /**
     * 文本
     */
    private String text;
    /**
     * 值
     */
    private String value;
    /**
     * 父级id
     */
    private String parentId;
    /**
     * 字典表id
     */
    @NotBlank
    private String dictId;
}
