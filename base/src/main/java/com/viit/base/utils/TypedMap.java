package com.viit.base.utils;

import java.util.HashMap;

/**
 * typed map
 *
 * @author virit
 * @version 2019-11-8
 */
public class TypedMap extends HashMap<String, Object> {

    public TypedMap set(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public <T> T get(String key, Class<T> type) {
        return (T) this.get(key);
    }
}
