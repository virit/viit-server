package com.viit.base.entity;

import com.viit.base.lang.entity.IdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据源
 *
 * @author virit
 * @version 2019-12-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDataSource extends IdEntity {

    /**
     * 数据源名称
     */
    private String name;
    /**
     * 数据源类型
     */
    private String type;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
