package com.viit.base.io;

import java.util.HashMap;

class TypeHolder {
    static final HashMap<String, ContentType> MAP = new HashMap<>();
}
/**
 * 响应类型
 */
public enum ContentType {

    /**
     * application/octet-stream
     */
    APPLICATION_OCTET_STREAM("application/octet-stream"),
    /**
     * jpg
     */
    JPG("image/jpeg", "jpg"),
    /**
     * png
     */
    PNG("image/png", "png");

    /**
     * 类型
     */
    private String type;

    ContentType(String type, String... exts) {
        this.type = type;
        for (String ext : exts) {
            TypeHolder.MAP.put(ext, this);
        }
    }

    public String getType() {
        return type;
    }

    public static String getTypeByExt(String ext) {
        ContentType contentType = TypeHolder.MAP.get(ext);
        if (contentType != null) {
            return contentType.getType();
        } else {
            return APPLICATION_OCTET_STREAM.getType();
        }
    }
}
