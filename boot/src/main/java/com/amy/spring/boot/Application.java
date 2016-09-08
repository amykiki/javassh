package com.amy.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@Configuration // 配置控制
//@EnableAutoConfiguration //启用自动配置
//@ComponentScan //组件扫描
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class Application {
	public static void main(String[] args) {
//	    启动Spring Boot项目唯一入口点
        SpringApplication.run(Application.class, args);
	}
}
