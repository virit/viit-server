package com.viit.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viit.base.entity.SysAttach;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 附件mapper
 *
 * @author virit
 * @version 2019-10-31
 */
@Mapper
@Repository
public interface SysAttachMapper extends BaseMapper<SysAttach> {
}
