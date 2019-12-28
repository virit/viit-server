package com.viit.base.modelview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * rest data实现类
 *
 * @param <T> data类型
 * @author virit
 * @version 2019-10-28
 */
@Data
public class SimpleRestData<T> implements RestData<T> {

    @JsonIgnore
    protected ResultCode resultCode;

    @JsonView(BaseProfile.class)
    protected String msg;

    @JsonView(BaseProfile.class)
    protected T data;

    @JsonView(BaseProfile.class)
    protected int code;

    /**
     * 请求成功响应
     */
    public static final SimpleRestData<Void> SUCCESS = new SimpleRestData<>();

    public SimpleRestData() {
        resultCode = ResultCode.SUCCESS;
    }

    public SimpleRestData<T> resultCode(ResultCode code) {
        this.resultCode = code;
        return this;
    }

    public SimpleRestData<T> code(int code) {
        this.code = code;
        return this;
    }

    public SimpleRestData<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public SimpleRestData<T> data(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String getMsg() {
        if (StringUtils.isNoneBlank(msg)) {
            return msg;
        } else if (resultCode != null) {
            return resultCode.getMsg();
        } else {
            return null;
        }
    }

    @Override
    public int getCode() {
        if (resultCode != null) {
            return resultCode.getCode();
        } else {
            return code;
        }
    }
}
