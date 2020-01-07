package com.viit.bpmf.listener.base;

import com.viit.base.service.SysMessageService;
import org.activiti.engine.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Optional;

/**
 * 人员选择器
 *
 * @author virit
 * @version 2019-12-29
 */
public abstract class AbstractUserSelector extends AbstractTaskListener {


    private SysMessageService sysMessageService;

    @Autowired
    public void setSysMessageService(SysMessageService sysMessageService) {
        this.sysMessageService = sysMessageService;
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        Collection<String> users = getUsers();
        Optional.ofNullable(users).ifPresent(it -> {
            it.forEach(uid -> {
                // 发送待办任务
            });
        });
        delegateTask.addCandidateUsers(users);
    }

    /**
     * 获取处理人
     */
    public abstract Collection<String> getUsers();
}
