package com.zhiling.bank.test;

import java.util.concurrent.CountDownLatch;

import com.zhiling.bank.pojo.Account;
import com.zhiling.bank.service.SendService;

public class MyTask implements Runnable {
	
	private CountDownLatch countDownLatch;
	private SendService sendService;
	private Integer num;
	
	public MyTask(CountDownLatch countDownLatch,SendService sendService,Integer num) {
		this.countDownLatch = countDownLatch;
		this.sendService = sendService;
		this.num = num;
	}

	@Override
	public void run() {
		
		try {
			countDownLatch.await();
			Account acc = new Account();
			acc.setBank("aaaa"+num);
			sendService.sendMessage(acc);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

}
