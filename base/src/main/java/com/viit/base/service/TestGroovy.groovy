package com.viit.base.service

import com.viit.base.utils.ContextUtils
import org.springframework.beans.factory.annotation.Autowired

class TestGroovy {

    @Autowired
    SysUserService sysUserService

    String run() {
        return ContextUtils.currentUser().getAuthorities()
    }
}
