package com.zhiling.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SystemControlMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(SystemControlMain8002.class, args);
    }
}
