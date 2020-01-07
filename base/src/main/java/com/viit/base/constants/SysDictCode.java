package com.viit.base.constants;

/**
 * 字典code
 *
 * @author virit
 * @version 2019-12-4
 */
public enum SysDictCode implements IDictCode {

    /**
     * 婚姻状况
     */
    MARiTAL_STATUS("marital_status"),
    /**
     * 行政级别
     */
    STAFF_LEVEL("staff_level"),
    /**
     * 性别
     */
    GENDER("gender"),
    /**
     * 员工类型
     */
    STAFF_TYPE("staff_type"),
    /**
     * 学历
     */
    EDUCATION_BG("education_bg"),
    /**
     * 职务
     */
    DUTY("duty"),
    /**
     * 机构类型
     */
    DEPT_TYPE("dept_type");

    private String code;

    SysDictCode(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return this.code;
    }
}
