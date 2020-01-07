package com.viit.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viit.base.entity.SysMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 消息mapper
 *
 * @author virit
 * @version 2019-11-22
 */
@Mapper
@Repository
public interface SysMessageMapper extends BaseMapper<SysMessage> {

    @Select("select * from ")
    List<SysMessage> userList();
}
