package com.viit.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viit.base.entity.SysDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author virit
 * @version 2019-12-07
 */
@Mapper
@Repository
public interface SysDataSourceMapper extends BaseMapper<SysDataSource> {
}
