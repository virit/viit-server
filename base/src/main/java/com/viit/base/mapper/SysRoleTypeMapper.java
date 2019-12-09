package com.viit.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viit.base.entity.SysRoleType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 系统角色类型mapper
 *
 * @author chentao
 * @version 2019-10-29
 */
@Mapper
@Repository
public interface SysRoleTypeMapper extends BaseMapper<SysRoleType> {

}
