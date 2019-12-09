package com.viit.base.lang.entity;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 弱类型实体
 *
 * @author virit
 * @version 2019-11-22
 */
public class Record extends HashMap<String, Object> implements Serializable, Cloneable {

    /**
     * 设置值
     *
     * @param key   key
     * @param value value
     * @return this
     */
    public Record set(String key, Object value) {
        this.put(key, value);
        return this;
    }

    /**
     * 获取值
     *
     * @param key   key
     * @param clazz 类型
     * @param <T>   类型
     * @return value
     */
    public <T> T get(String key, Class<T> clazz) {
        Object value = this.get(key);
        if (value == null) {
            return null;
        }
        if (value.getClass().equals(clazz)) {
            return (T) value;
        } else {
            throw new RuntimeException("类型不一致");
        }
    }

    /**
     * 获取字符串
     *
     * @param key key
     * @return 字符串
     */
    public String getString(String key) {
        return this.get(key, String.class);
    }
}
