package com.zhiling.bank.schedule;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhiling.bank.util.Constants;

@Component
public class TaskSchedule {
	
	@Autowired
	RetrySchedule retrySchedule;
	
	@Autowired
	SaveSchedule saveSchedule;
	
	@Scheduled(cron = "0 30 * ? * *")
	@Async
	public void dealRetry() {
		
		retrySchedule.dealRetry();
	}
	
	@Scheduled(cron = "0 0 * ? * *")
	@Async
	public void save() {
		
		saveSchedule.saveAccAndLog();
	}
	
}
