package com.zhiling.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author LiZheng
 * @date 2020/4/22 20:48
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(value = "com.zhiling.bank.dao")
public class backstage8005 {
    public static void main(String[] args) {
        SpringApplication.run(backstage8005.class,args);
    }
}
