package com.viit.bpmf.constants;

import com.viit.base.constants.IDictCode;

/**
 * @author virit
 * @version 2019-12-18
 */
public enum BpmfDictCode implements IDictCode {

    /**
     * 流程类型
     */
    PROCESS_CATEGORY("process_category");

    private String code;

    BpmfDictCode(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
