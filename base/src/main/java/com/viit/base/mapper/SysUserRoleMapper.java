package com.viit.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viit.base.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户角色mapper
 *
 * @author chentao
 * @version 2019-10-29
 */
@Mapper
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
}
