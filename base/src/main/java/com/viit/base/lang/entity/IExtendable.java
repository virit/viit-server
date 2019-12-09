package com.viit.base.lang.entity;

import java.util.Map;

/**
 * 实体可扩展接口
 *
 * @author virit
 * @version 2019-12-05
 */
public interface IExtendable {

    /**
     * 添加信息字段
     * @param fieldName 字段名
     * @param fieldValue 值
     * @return this
     */
    IExtendable addInfoField(String fieldName, Object fieldValue);

    /**
     * 获取信息字段
     * @return map
     */
    Map<String, Object> getInfoFields();
}
