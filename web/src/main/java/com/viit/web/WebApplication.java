package com.viit.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author virit
 * @version 2019-12-10
 */
@MapperScan("com.viit.**.mapper")
@SpringBootApplication(scanBasePackages = "com.viit")
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
