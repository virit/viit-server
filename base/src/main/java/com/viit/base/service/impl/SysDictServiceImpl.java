package com.viit.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viit.base.entity.SysDict;
import com.viit.base.enums.IDictCode;
import com.viit.base.mapper.SysDictMapper;
import com.viit.base.service.SysDictItemService;
import com.viit.base.service.SysDictService;
import com.viit.base.utils.SysDictGather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 字典表service实现类
 *
 * @author virit
 * @version 2019-11-01
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    private SysDictItemService sysDictItemService;

    @Override
    public SysDictGather gatherWithCode(IDictCode dictCode) {
        return new SysDictGather(sysDictItemService.listItemsByCode(dictCode.getCode()));
    }

    @Autowired
    public void setSysDictItemService(SysDictItemService sysDictItemService) {
        this.sysDictItemService = sysDictItemService;
    }
}
