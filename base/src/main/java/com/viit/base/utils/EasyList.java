package com.viit.base.utils;

import java.util.ArrayList;

/**
 * @author virit
 * @version 2019-11-20
 */
public class EasyList<T> extends ArrayList<T> {

    public EasyList<T> append(T item) {
        this.add(item);
        return this;
    }
}
