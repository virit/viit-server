package com.viit.base.modelview;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.HashMap;
import java.util.Map;

/**
 * 键值对data
 *
 * @author virit
 * @version 2019-10-29
 */
@JsonView(BaseProfile.class)
public class KeyValueData extends SimpleRestData<Map<String, Object>> {

    public KeyValueData() {
        this.data = new HashMap<>();
    }

    public KeyValueData put(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
