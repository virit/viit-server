package com.viit.base.lang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonView;
import com.viit.base.modelview.BaseProfile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 实体基类
 *
 * @author chentao
 * @version 2019-10-28
 */
public class BaseEntity implements Entity, IExtendable {

    @JsonView(BaseProfile.class)
    private String createUserId;

    @JsonView(BaseProfile.class)
    private String updateUserId;

    @JsonView(BaseProfile.class)
    private Date createDate;

    @JsonView(BaseProfile.class)
    private Date updateDate;

    @TableField(exist = false)
    @JsonView(BaseProfile.class)
    private Map<String, Object> infoFields;

    @Override
    public void setCreateUserId(String userId) {
        this.createUserId = userId;
    }

    @Override
    public String getCreateUserId() {
        return createUserId;
    }

    @Override
    public String getUpdateUserId() {
        return updateUserId;
    }

    @Override
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public BaseEntity addInfoField(String fieldName, Object fieldValue) {
        checkFields();
        infoFields.put(fieldName, fieldValue);
        return this;
    }

    @Override
    public Map<String, Object> getInfoFields() {
        return this.infoFields;
    }

    private void checkFields() {
        if (infoFields == null) {
            infoFields = new HashMap<>(1);
        }
    }
}
