package com.viit.orga.service.impl;

import com.google.common.base.Preconditions;
import com.viit.base.enums.SysDictCode;
import com.viit.orga.entity.Department;
import com.viit.orga.entity.Staff;
import com.viit.orga.mapper.StaffMapper;
import com.viit.orga.service.DepartmentService;
import com.viit.orga.service.StaffService;
import com.viit.base.service.FieldsInjectServiceImpl;
import com.viit.base.service.SysDictService;
import com.viit.base.utils.SysDictGather;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 员工信息service实现类
 *
 * @author virit
 * @version 2019-11-20
 */
@Service
public class StaffServiceImpl extends FieldsInjectServiceImpl<StaffMapper, Staff> implements StaffService {

    private final SysDictService sysDictService;

    private final DepartmentService departmentService;

    public StaffServiceImpl(SysDictService sysDictService, DepartmentService departmentService) {
        this.sysDictService = sysDictService;
        this.departmentService = departmentService;
    }

    @Override
    public List<Staff> infoFieldsInject(List<Staff> list) {
        Preconditions.checkNotNull(list);
        // 性别字典
        SysDictGather gender = sysDictService.gatherWithCode(SysDictCode.GENDER);
        // 学历字典
        SysDictGather eduBg = sysDictService.gatherWithCode(SysDictCode.EDUCATION_BG);
        // 员工类型
        SysDictGather staffType = sysDictService.gatherWithCode(SysDictCode.STAFF_TYPE);
        // 婚姻状态
        SysDictGather maritalStatus = sysDictService.gatherWithCode(SysDictCode.MARiTAL_STATUS);
        // 职务
        SysDictGather duty = sysDictService.gatherWithCode(SysDictCode.DUTY);
        // 行政级别
        SysDictGather staffLevel = sysDictService.gatherWithCode(SysDictCode.STAFF_LEVEL);

        list.forEach(staff -> staff.addInfoField("genderText", gender.getText(staff.getGender()))
                .addInfoField("educationBackgroundText", eduBg.getText(staff.getEducationBackground()))
                .addInfoField("typeText", staffType.getText(staff.getType()))
                .addInfoField("maritalStatusText", maritalStatus.getText(staff.getMaritalStatus()))
                .addInfoField("dutyText", duty.getText(staff.getDuty()))
                .addInfoField("levelText", staffLevel.getText(staff.getLevel()))
                .addInfoField("departmentIdText",
                        Optional.ofNullable(departmentService.getById(staff.getDepartmentId())).map(Department::getName)
                        .orElse(null)
                )
        );
        return list;
    }
}
