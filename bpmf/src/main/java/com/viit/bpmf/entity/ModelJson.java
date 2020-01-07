package com.viit.bpmf.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.viit.base.lang.entity.IdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 流程json模型数据
 *
 * @author virit
 * @version 2019-12-22
 */
@Data
@TableName("bpmf_model_json")
@EqualsAndHashCode(callSuper = true)
public class ModelJson extends IdEntity {

    /**
     * json数据
     */
    private String jsonData;
    /**
     * 模型id
     */
    private String modelId;
}
