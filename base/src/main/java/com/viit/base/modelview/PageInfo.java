package com.viit.base.modelview;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.Collection;

/**
 * page info
 *
 * @param <T>
 * @author chentao
 * @version 2019-10-20
 */
@Data
public class PageInfo<T> {

    @JsonView(BaseProfile.class)
    private Collection<T> records;

    @JsonView(BaseProfile.class)
    private long total;
}
