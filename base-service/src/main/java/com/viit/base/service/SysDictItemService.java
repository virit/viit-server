package com.viit.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viit.base.entity.SysDictItem;

import java.util.List;

/**
 * 字典子项service
 *
 * @author virit
 * @version 2019-11-01
 */
public interface SysDictItemService extends IService<SysDictItem> {

    List<SysDictItem> listItemsByCode(String code);
}
