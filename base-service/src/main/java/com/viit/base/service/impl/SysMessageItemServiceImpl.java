package com.viit.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viit.base.entity.SysMessageItem;
import com.viit.base.mapper.SysMessageItemMapper;
import com.viit.base.service.SysMessageItemService;
import org.springframework.stereotype.Service;

/**
 * @author virit
 * @version 2019-11-22
 */
@Service
public class SysMessageItemServiceImpl extends ServiceImpl<SysMessageItemMapper, SysMessageItem>
        implements SysMessageItemService {
}
