package com.viit.base.utils;

import java.util.HashMap;

public class EasyMap<K, V> extends HashMap<K, V> {

    public EasyMap<K, V> set(K key, V value) {
        this.put(key, value);
        return this;
    }
}
