package com.viit.base.groovy;

import java.lang.reflect.InvocationTargetException;

public interface GroovyEngine {

    Object runScript(String groovyScript, String methodName) throws InvocationTargetException, IllegalAccessException,
            InstantiationException, NoSuchMethodException;

    Object runScript(String groovyScript) throws InvocationTargetException, IllegalAccessException,
            InstantiationException, NoSuchMethodException;
}
