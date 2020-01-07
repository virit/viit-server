package com.viit.base.constants;

import lombok.Data;

@Data
public class SysEnumItem {

    private int value;

    private String label;

    public SysEnumItem(int value, String label) {
        this.value = value;
        this.label = label;
    }
}
