package com.viit.orga.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viit.orga.entity.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 员工信息mapper
 *
 * @author virit
 * @version 2019-11-20
 */
@Mapper
@Repository
public interface StaffMapper extends BaseMapper<Staff> {
}
