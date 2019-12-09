package com.viit.orga.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.viit.base.lang.entity.IdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户
 *
 * @author virit
 * @version 2019-11-12
 */
@Data
@TableName("orga_staff")
@EqualsAndHashCode(callSuper = true)
public class Staff extends IdEntity {

    /**
     * 员工姓名
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 手机号码
     */
    private String phoneNumber;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 所属部门id
     */
    private String departmentId;
    /**
     * 职务
     */
    private String duty;
    /**
     * 行政级别
     */
    private String level;
    /**
     * 员工类型
     */
    private String type;
    /**
     * 工号
     */
    private String workNumber;
    /**
     * 籍贯
     */
    private String nativePlace;
    /**
     * 婚姻状态
     */
    private String maritalStatus;
    /**
     * 毕业院校
     */
    private String school;
    /**
     * 毕业时间
     */
    @DateTimeFormat(pattern = "yyyy")
    @JsonFormat(pattern = "yyyy")
    private Date graduationDate;
    /**
     * 学历
     */
    private String educationBackground;
    /**
     * 所学专业
     */
    private String major;
    /**
     * 备注
     */
    private String remark;
}
