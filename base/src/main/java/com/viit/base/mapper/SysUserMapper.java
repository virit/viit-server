package com.viit.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viit.base.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 系统用户mapper
 *
 * @author virit
 * @version 2019-10-28
 */
@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
}
