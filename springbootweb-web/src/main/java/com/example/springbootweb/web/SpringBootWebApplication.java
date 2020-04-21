package com.example.springbootweb.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author william
 * @date 2020/4/21
 */
@SpringBootApplication(scanBasePackages = "com.example.springbootweb")
@MapperScan("com.example.springbootweb.dao.mapper")
@EnableAsync
public class SpringBootWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
}
