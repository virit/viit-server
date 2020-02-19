package com.viit.base.controller;

import com.viit.base.groovy.GroovyEngine;
import com.viit.base.modelview.RestData;
import com.viit.base.modelview.SimpleRestData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/sys/groovy")
public class GroovyController {

    private final GroovyEngine groovyEngine;

    public GroovyController(GroovyEngine groovyEngine) {
        this.groovyEngine = groovyEngine;
    }

    @PostMapping("/run")
    public RestData run(@RequestBody String script) throws Exception {
        Object result = groovyEngine.runScript(script);
        return new SimpleRestData<>().data(result);
    }
}
