package com.viit.base.lang.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonView;
import com.viit.base.modelview.BaseProfile;

/**
 * id实体
 *
 * @author chentao
 * @version 2019-10-29
 */
public class IdEntity extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.UUID)
    @JsonView(BaseProfile.class)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
