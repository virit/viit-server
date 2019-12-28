package com.viit.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viit.base.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统角色mapper
 *
 * @author virit
 * @version 2019-10-28
 */
@Mapper
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 查询用户角色
     */
    @Results(id = "role_map", value = {
            @Result(property = "id", column = "sr.id", id = true),
            @Result(property = "createUserId", column = "sr.create_user_id"),
            @Result(property = "updateUserId", column = "sr.update_user_id"),
            @Result(property = "createDate", column = "sr.create_date"),
            @Result(property = "updateDate", column = "sr.update_date"),
            @Result(property = "name", column = "sr.name"),
            @Result(property = "code", column = "sr.code"),
            @Result(property = "typeId", column = "sr.type_id"),
            @Result(property = "description", column = "sr.description")
    })
    @Select("select sr.* from sys_user_role sur left join sys_role sr on sur.role_id=sr.id where sur.user_id=#{userId}")
    List<SysRole> selectByUserId(String userId);
}
