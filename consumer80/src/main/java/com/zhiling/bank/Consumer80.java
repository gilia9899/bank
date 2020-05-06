package com.zhiling.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author LiZheng
 * @date 2020/4/22 20:48
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class Consumer80 {
    public static void main(String[] args) {
        SpringApplication.run(Consumer80.class,args);
    }
}
