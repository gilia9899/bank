package com.zhiling.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author LiZheng
 * @date 2020/4/17 16:19
 */
@SpringBootApplication
@EnableEurekaClient
public class Transfer8001 {
    public static void main(String[] args) {
        SpringApplication.run(Transfer8001.class, args);
    }
}
