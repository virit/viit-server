package com.viit.base.utils;

/**
 * setterable
 *
 * @author virit
 * @version 2019-11-11
 */
public interface Setterable<K, V> {

    /**
     * set value
     * @param key key
     * @param value value
     */
    Setterable<K, V> set(K key, V value);
}
