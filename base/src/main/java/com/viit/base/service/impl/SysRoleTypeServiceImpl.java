package com.viit.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viit.base.entity.SysRoleType;
import com.viit.base.mapper.SysRoleTypeMapper;
import com.viit.base.service.SysRoleTypeService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统角色类型service实现类
 *
 * @author chentao
 * @version 2019-10-29
 */
@Component
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class SysRoleTypeServiceImpl extends BaseServiceImpl<SysRoleTypeMapper, SysRoleType> implements SysRoleTypeService {
}
