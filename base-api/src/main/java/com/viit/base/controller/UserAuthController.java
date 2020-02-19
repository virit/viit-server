package com.viit.base.controller;

import com.viit.base.entity.SysUser;
import com.viit.base.entity.SysUserGroups;
import com.viit.base.modelview.KeyValueData;
import com.viit.base.modelview.RestData;
import com.viit.base.modelview.ResultCode;
import com.viit.base.modelview.SimpleRestData;
import com.viit.base.util.ApplicationUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户权限controller
 *
 * @author virit
 * @version 2019-10-28
 */
@RestController
public class UserAuthController {

    private final AuthenticationManager authenticationManager;

    public UserAuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 登录接口
     */
    @PostMapping("/user/login")
    public RestData userLogin(@RequestBody @Validated(SysUserGroups.NamePass.class) SysUser sysUser
            , BindingResult bindingResult) throws Exception {

        ResultCode resultCode = null;
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(sysUser.getUsername()
                    , sysUser.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(result);
            resultCode = ResultCode.SUCCESS;
        } catch(UsernameNotFoundException | BadCredentialsException e) {
            resultCode = ResultCode.USERNAME_OR_PASSWORD_ERROR;
        } catch (Exception e) {
            throw e;
        }
        if (resultCode != ResultCode.SUCCESS) {
            return new SimpleRestData<>().resultCode(resultCode);
        } else {
            // 返回session id
            return new KeyValueData().put("token", ApplicationUtils.getSessionId());
        }
    }

    /**
     * 登出接口
     */
    @PostMapping("/user/logout")
    public RestData userLogout() {
        SecurityContextHolder.clearContext();
        return SimpleRestData.SUCCESS;
    }

    @GetMapping("/user/token")
    public RestData getToken(Model model) {

        return null;
    }
}
