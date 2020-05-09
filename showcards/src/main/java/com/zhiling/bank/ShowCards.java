package com.zhiling.bank;


import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.zhiling.bank.pojo.Account;
import com.zhiling.bank.service.SendService;
import com.zhiling.bank.test.TestSend;


@SpringBootApplication
//@EnableEurekaClient
@MapperScan(annotationClass = Mapper.class,basePackages = "com.zhiling.bank.dao")
@EnableScheduling
@EnableAsync
public class ShowCards {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = SpringApplication.run(ShowCards.class, args);
		/*
		SendService sendService = context.getBean(SendService.class);
		Account acc = new Account();
		acc.setBank("sdfasdfsa");
		sendService.sendMessage(acc);
		*/
		TestSend testSend = context.getBean(TestSend.class);
		testSend.testsend(4000);
		
	}

}
