package com.viit.bpmf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viit.bpmf.entity.ModelJson;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * json模型mapper
 *
 * @author virit
 * @version 2019-12-22
 */
@Mapper
@Repository
public interface ModelJsonMapper extends BaseMapper<ModelJson> {
}
