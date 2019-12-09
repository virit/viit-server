package com.viit.base.lang.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * id实体
 *
 * @author chentao
 * @version 2019-11-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class IntegerEntity extends BaseEntity {

    private Integer id;
}
