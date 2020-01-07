package com.viit.base.utils.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 排序对象
 *
 * @author virit
 * @version 2019-12-18
 */
@Data
@AllArgsConstructor
public class OrderObject {

    /**
     * id
     */
    private String id;

    /**
     * 父级id
     */
    private String parent;
}
