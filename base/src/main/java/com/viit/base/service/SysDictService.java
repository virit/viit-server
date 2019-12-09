package com.viit.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viit.base.entity.SysDict;
import com.viit.base.enums.IDictCode;
import com.viit.base.utils.SysDictGather;

/**
 * 字典service
 *
 * @author virit
 * @version 2019-11-01
 */
public interface SysDictService extends IService<SysDict> {

    SysDictGather gatherWithCode(IDictCode code);
}
