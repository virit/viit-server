package com.viit.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viit.base.entity.SysDataSource;
import com.viit.base.mapper.SysDataSourceMapper;
import com.viit.base.service.SysDataSourceService;
import org.springframework.stereotype.Service;

/**
 * 数据源service实现类
 *
 * @author virit
 * @version 2019-12-07
 */
@Service
public class SysDataSourceServiceImpl extends ServiceImpl<SysDataSourceMapper, SysDataSource>
        implements SysDataSourceService {
}
