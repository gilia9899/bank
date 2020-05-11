package com.zhiling.bank.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhiling.bank.service.SendService;

@Component
public class TestSend {
	
	@Autowired
	SendService sendService;
	
	public void testsend(int num) {
		
		CountDownLatch countDownLatch = new CountDownLatch(1);
		for (int i = 0; i < num; i++) {
			Executors.newCachedThreadPool().execute(new MyTask(countDownLatch,sendService,i));
		}
		countDownLatch.countDown();
		
	}
	
	
}
