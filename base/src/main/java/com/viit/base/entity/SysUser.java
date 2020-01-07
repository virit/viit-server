package com.viit.base.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonView;
import com.viit.base.constants.SysUserStatus;
import com.viit.base.lang.entity.IdEntity;
import com.viit.base.modelview.BaseProfile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统用户实体类
 *
 * @author virit
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUser extends IdEntity implements UserDetails {

    public static final String DEFAULT_AVATAR = "default-avatar";
    public static final String DEFAULT_AVATAR_PATH = "images/default-avatar.png";

    /**
     * 用户名
     */
    @JsonView(BaseProfile.class)
    @NotBlank(groups = {SysUserGroups.NamePass.class})
    @Size(max = 16, groups = {SysUserGroups.NamePass.class})
    @TableField(condition = SqlCondition.LIKE_RIGHT)
    protected String username;
    /**
     * 密码
     */
    @NotBlank(groups = {SysUserGroups.NamePass.class})
    @Size(max = 16, groups = {SysUserGroups.NamePass.class})
    protected String password;
    /**
     * 头像
     */
    @JsonView(BaseProfile.class)
    protected String avatar;

    @JsonView(BaseProfile.class)
    @TableField(exist = false)
    private List<String> roleIdList;

    @TableField(exist = false)
    private Collection<? extends GrantedAuthority> authorities;

    @TableField(exist = false)
    @JsonView(BaseProfile.class)
    private Collection<String> grantedAuthorities;

    /**
     * 状态
     */
    private Integer status;

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {

        // 进行权限分解
        List<GrantedAuthority> actualAuthorities = new ArrayList<>();
        authorities.forEach(it -> {
            // 菜单可能配置多个权限
            if (it instanceof SysMenu) {
                String str = it.getAuthority();
                if (str.contains("|")) {
                    for (String item : str.split("[|]")) {
                        actualAuthorities.add(new SysGrantedAuthority(item));
                    }
                } else {
                    actualAuthorities.add(new SysGrantedAuthority(str));
                }
            } else {
                actualAuthorities.add(new SysGrantedAuthority(it.getAuthority()));
            }
        });

        this.authorities = actualAuthorities;
        this.grantedAuthorities = actualAuthorities.stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    public Collection<String> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return SysUserStatus.ENABLED == status;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
