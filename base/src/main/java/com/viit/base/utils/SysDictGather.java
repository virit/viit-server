package com.viit.base.utils;

import com.google.common.base.Preconditions;
import com.viit.base.entity.SysDictItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 字典集合工具
 *
 * @author virit
 * @version 2019-12-4
 */
public class SysDictGather {

    private Map<String, SysDictItem> dictMap = new HashMap<>();

    public SysDictGather(List<SysDictItem> items) {
        Preconditions.checkNotNull(items);
        for (SysDictItem item : items) {
            dictMap.put(item.getValue(), item);
        }
    }

    public String getText(String value) {
        return Optional.ofNullable(dictMap.get(value)).map(SysDictItem::getText).orElse(null);
    }

    public String getText(Integer value) {
        return getText(String.valueOf(value));
    }
}
