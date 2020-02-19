package com.viit.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viit.base.entity.SysPermission;
import com.viit.base.mapper.SysPermissionMapper;
import com.viit.base.service.SysPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统权限service实现类
 *
 * @author virit
 * @version 2019-10-28
 */
@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission>
        implements SysPermissionService {
}
