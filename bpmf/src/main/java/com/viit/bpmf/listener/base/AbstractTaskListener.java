package com.viit.bpmf.listener.base;

import com.viit.base.utils.ContextUtils;
import org.activiti.engine.delegate.TaskListener;

/**
 * 任务监听器基类
 *
 * @author virit
 * @version 2019-12-29
 */
public abstract class AbstractTaskListener implements TaskListener {

    public AbstractTaskListener() {
        ContextUtils.autowireBean(this);
    }
}
