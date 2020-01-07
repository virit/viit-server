package com.viit.bpmf.service.impl;

import com.viit.base.service.impl.BaseServiceImpl;
import com.viit.bpmf.entity.ModelJson;
import com.viit.bpmf.mapper.ModelJsonMapper;
import com.viit.bpmf.service.ModelJsonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * json模型service实现类
 *
 * @author virit
 * @version 2019-12-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ModelJsonServiceImpl extends BaseServiceImpl<ModelJsonMapper, ModelJson>
        implements ModelJsonService {
}
