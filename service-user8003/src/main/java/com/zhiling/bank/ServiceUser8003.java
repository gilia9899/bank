package com.zhiling.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceUser8003 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUser8003.class, args);

    }
}
