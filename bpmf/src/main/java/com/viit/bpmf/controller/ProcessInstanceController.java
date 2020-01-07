package com.viit.bpmf.controller;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bpmf/instance")
public class ProcessInstanceController {

    private final RuntimeService runtimeService;

    private final TaskService taskService;

    public ProcessInstanceController(RuntimeService runtimeService, TaskService taskService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
    }
}
