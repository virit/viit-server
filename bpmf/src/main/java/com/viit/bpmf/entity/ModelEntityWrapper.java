package com.viit.bpmf.entity;

import com.viit.base.lang.entity.IExtendable;
import org.activiti.engine.impl.persistence.entity.ModelEntityImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * 工作量model包装实体
 *
 * @author virit
 * @version 2019-12-5
 */
public class ModelEntityWrapper extends ModelEntityImpl implements IExtendable {

    private Map<String, Object> infoFields = new HashMap<>();

    @Override
    public IExtendable addInfoField(String fieldName, Object fieldValue) {
        infoFields.put(fieldName, fieldValue);
        return this;
    }

    @Override
    public Map<String, Object> getInfoFields() {
        return infoFields;
    }
}
