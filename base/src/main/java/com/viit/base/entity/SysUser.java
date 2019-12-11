package com.viit.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonView;
import com.viit.base.lang.entity.IdEntity;
import com.viit.base.modelview.BaseProfile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统用户实体类
 *
 * @author chentao
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUser extends IdEntity implements UserDetails {

    public static final String DEFAULT_AVATAR = "default-avatar";
    public static final String DEFAULT_AVATAR_PATH = "classpath:images/default-avatar.png";

    /**
     * 用户名
     */
    @JsonView(BaseProfile.class)
    @NotBlank(groups = {SysUserGroups.NamePass.class})
    @Size(max = 16, groups = {SysUserGroups.NamePass.class})
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

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
        this.grantedAuthorities = authorities.stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
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
        return true;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
