package com.dhlg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.dhlg.module.*.*.dao","com.dhlg.module.*.*.*.dao"})
@ServletComponentScan
@EnableRabbit
public class MobanApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobanApplication.class, args);
    }

}
