package com.viit.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viit.base.entity.SysMessageItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 消息mapper
 *
 * @author virit
 * @version 2019-11-22
 */
@Mapper
@Repository
public interface SysMessageItemMapper extends BaseMapper<SysMessageItem> {
}
