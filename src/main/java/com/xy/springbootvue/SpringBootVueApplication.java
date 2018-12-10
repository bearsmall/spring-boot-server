package com.xy.springbootvue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.xy.springbootvue.mapper")
public class SpringBootVueApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootVueApplication.class, args);
    }
}
