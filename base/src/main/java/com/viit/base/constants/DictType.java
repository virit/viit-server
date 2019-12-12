package com.viit.base.constants;

/**
 * 字典类型
 *
 * @author virit
 * @version 2019-11-01
 */
public enum DictType {

    /**
     * 普通字典
     */
    NORMAL(10),
    /**
     * 树型字典
     */
    TREE(20);

    private int type;

    DictType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
