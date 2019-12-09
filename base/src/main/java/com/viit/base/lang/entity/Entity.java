package com.viit.base.lang.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 实体类接口
 *
 * @author chentao
 * @version 2019-10-28
 */
public interface Entity extends Serializable, Cloneable {

    /**
     * 设置创建用户id
     *
     * @param userId 用户id
     */
    void setCreateUserId(String userId);

    /**
     * 获取创建用户id
     *
     * @return 用户id
     */
    String getCreateUserId();

    /**
     * 设置更新用户id
     *
     * @param userId 用户id
     */
    void setUpdateUserId(String userId);

    /**
     * 获取更新用户id
     *
     * @return 用户id
     */
    String getUpdateUserId();

    /**
     * 设置创建时间
     *
     * @param date 创建时间
     */
    void setCreateDate(Date date);

    /**
     * 获取创建时间
     *
     * @return 创建时间
     */
    Date getCreateDate();

    /**
     * 设置更新时间
     *
     * @param date 更新时间
     */
    void setUpdateDate(Date date);

    /**
     * 获取更新时间
     *
     * @return 更新时间
     */
    Date getUpdateDate();
}
