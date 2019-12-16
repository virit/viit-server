package com.viit.base.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.viit.base.lang.entity.IdEntity;
import com.viit.base.modelview.BaseProfile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 系统菜单实体
 *
 * @author chentao
 * @version 2019-10-28
 */
@Data
@JsonView(BaseProfile.class)
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends IdEntity implements GrantedAuthority {

    /**
     * 菜单标题
     */
    @NotBlank
    @Size(max = 16)
    private String title;
    /**
     * 页面地址
     */
    @Size(max = 64)
    private String url;
    /**
     * 权限标识
     */
    @Size(max = 64)
    private String authority;
    /**
     * 菜单类型
     */
    @NotNull
    private Integer type;
    /**
     * 父级菜单id
     */
    @Size(max = 64)
    private String parentId;
    /**
     * 菜单图标
     */
    @Size(max = 16)
    private String icon;
    /**
     * 是否隐藏
     */
    private Integer hide;

    /**
     * 排序号
     */
    private Integer orderNum;

    @Override
    public String getAuthority() {
        return authority;
    }
}
