package com.viit.utils.bean;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * bean utils
 *
 * @author virit
 * @version 2019-12-05
 */
@Data
class Test {
    private String a;
    private String b;
}
public class BeanUtils {

    /**
     * 复制属性
     *
     * @param from 源
     * @param dest 目标
     */
    public static void copyFields(Object from, Object dest) throws InvocationTargetException, IllegalAccessException {
        Preconditions.checkNotNull(from);
        Preconditions.checkNotNull(dest);
        final String setterNamePrefix = "set";
        final String getterNamePrefix = "get";
        Class<?> fromClass = from.getClass();
        Method[] destMethods = dest.getClass().getMethods();
        for (Method method : destMethods) {
            String methodName = method.getName();
            // 利用setter函数进行属性注入
            if (methodName.startsWith(setterNamePrefix)) {
                String fromGetterName = methodName.replace(setterNamePrefix, getterNamePrefix);
                Method fromGetter = null;
                try {
                    fromGetter = fromClass.getMethod(fromGetterName);
                } catch (NoSuchMethodException ignored) {
                }
                if (fromGetter != null) {
                    method.invoke(dest, fromGetter.invoke(from));
                }
            }
        }
    }
}
