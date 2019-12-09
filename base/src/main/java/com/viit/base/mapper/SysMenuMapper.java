package com.viit.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viit.base.entity.SysMenu;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统菜单mapper
 *
 * @author chentao
 * @version 2019-10-29
 */
@Mapper
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    @Results(id = "menu_map", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "createUserId", column = "create_user_id"),
            @Result(property = "updateUserId", column = "update_user_id"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date"),
            @Result(property = "title", column = "title"),
            @Result(property = "url", column = "url"),
            @Result(property = "authority", column = "authority"),
            @Result(property = "type", column = "type"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "icon", column = "icon")
    })
    @Select("select sm.* from sys_role_menu srm left join sys_menu sm on srm.menu_id=sm.id where srm.role_id=#{roleId}")
    List<SysMenu> selectListByRoleId(String roleId);
}
