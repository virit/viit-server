package com.viit.base.groovy;

import com.google.common.base.Preconditions;
import com.viit.base.utils.ContextUtils;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * groovy脚本引擎实现类
 *
 * @author virit
 * @version 2019-12-06
 */
@Service
public class GroovyEngineImpl implements GroovyEngine {

    private static final String DEFAULT_METHOD = "run";

    @Override
    public Object runScript(String groovyScript, String methodName) throws InvocationTargetException, IllegalAccessException,
            InstantiationException, NoSuchMethodException {

        Preconditions.checkNotNull(groovyScript);
        Class<?> clazz = new GroovyClassLoader().parseClass(groovyScript);
        Method run = clazz.getMethod(methodName);
        Object o = clazz.newInstance();
        ContextUtils.autowireBean(o);
        return run.invoke(o);
    }

    @Override
    public Object runScript(String groovyScript) throws InvocationTargetException, IllegalAccessException,
            InstantiationException, NoSuchMethodException {

        return runScript(groovyScript, DEFAULT_METHOD);
    }
}
