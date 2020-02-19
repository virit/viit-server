package com.viit.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viit.base.constants.SystemColumn;
import com.viit.base.entity.SysDict;
import com.viit.base.entity.SysDictItem;
import com.viit.base.mapper.SysDictItemMapper;
import com.viit.base.service.SysDictItemService;
import com.viit.base.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 字典子项service实现类
 *
 * @author virit
 * @version 2019-11-01
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {

    private SysDictService sysDictService;

    @Override
    public List<SysDictItem> list(Wrapper<SysDictItem> wrapper) {
        QueryWrapper<SysDictItem> queryWrapper = (QueryWrapper<SysDictItem>) wrapper;
        queryWrapper.orderByAsc(SystemColumn.CREATE_DATE);
        return super.list(queryWrapper);
    }

    @Override
    public List<SysDictItem> listItemsByCode(String code) {
        // 获取字典id
        SysDict dictQuery = new SysDict();
        dictQuery.setCode(code);
        QueryWrapper<SysDict> dictQueryWrapper = new QueryWrapper<>(dictQuery);
        String dictId = Optional.ofNullable(sysDictService.getOne(dictQueryWrapper)).map(SysDict::getId).get();
        // 查询字典列表
        SysDictItem dictItemQuery = new SysDictItem();
        dictItemQuery.setDictId(dictId);
        QueryWrapper<SysDictItem> dictItemQueryWrapper = new QueryWrapper<>(dictItemQuery);
        return this.list(dictItemQueryWrapper);
    }

    @Autowired
    public void setSysDictService(SysDictService sysDictService) {
        this.sysDictService = sysDictService;
    }
}
