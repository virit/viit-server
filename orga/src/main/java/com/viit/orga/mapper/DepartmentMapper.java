package com.viit.orga.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viit.orga.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 机构mapper
 *
 * @author virit
 * @version 2019-11-19
 */
@Mapper
@Repository
public interface DepartmentMapper extends BaseMapper<Department> {
}
