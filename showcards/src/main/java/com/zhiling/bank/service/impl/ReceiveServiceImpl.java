package com.zhiling.bank.service.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.zhiling.bank.pojo.Acclog;
import com.zhiling.bank.pojo.Account;
import com.zhiling.bank.service.AcclogService;
import com.zhiling.bank.service.AccountService;
import com.zhiling.bank.service.ReceiveService;
import com.zhiling.bank.service.ResendService;
import com.zhiling.bank.util.Constants;

@Component
public class ReceiveServiceImpl implements ReceiveService {
	
	@Autowired
	AccountService accountService;
	@Autowired
	AcclogService acclogService;
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Autowired
	ResendService resendService;
	
	@RabbitListener(queues = {Constants.QUEUE_NAME},concurrency = "8")
	@RabbitHandler
	public void recevie(Map<String, Object> map,Channel channel,Message msg) {
		
		
		Account account = (Account) map.get("account");
		Acclog log = (Acclog) map.get("log");
		
		try {
			// redis中 已经有msgid 说明已经消费，避免重复消费
			boolean isConsumed = acclogService.existsInRedis(log.getMsgid());
			
			if (!isConsumed) {
				System.out.println("接收消息："+account.getBank());
				accountService.addToRedis(account);
				acclogService.addToRedis(log);
				acclogService.addMsgidToRedis(log.getMsgid());
				
			}
			channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
			
		} catch (IOException e) {
			try {
				Integer retrycount = log.getRetrycount();
				// 重试次数超过限制
				if (retrycount.compareTo(Constants.MAX_RETRYCOUNT) > 0) {
					
					resendService.addFailToRedis(map);
				}
				else { // 需要重试
					resendService.addNeedRetryToRedis(map);
				}
				
				channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, false);	
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}			
		}	
	}
	
	
	
	
	
	@RabbitListener(queues = {"backupque1"},concurrency = "8")
	@RabbitHandler
	public void recevie2(Map<String, Object> map,Channel channel,Message msg) {
		
		
		Account account = (Account) map.get("account");
		Acclog log = (Acclog) map.get("log");
		
		try {
			// redis中 已经有msgid 说明已经消费，避免重复消费
			boolean isConsumed = acclogService.existsInRedis(log.getMsgid());
			
			if (!isConsumed) {
				System.out.println("接收消息2："+account.getBank());
				accountService.addToRedis(account);
				acclogService.addToRedis(log);
				acclogService.addMsgidToRedis(log.getMsgid());
				
			}
			channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
			
		} catch (IOException e) {
			try {
				Integer retrycount = log.getRetrycount();
				// 重试次数超过限制
				if (retrycount.compareTo(Constants.MAX_RETRYCOUNT) > 0) {
					
					resendService.addFailToRedis(map);
				}
				else { // 需要重试
					resendService.addNeedRetryToRedis(map);
				}
				
				channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, false);	
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}			
		}	
	}
	
	/*
	@RabbitListener(queues = {"backupque2"},concurrency = "8")
	@RabbitHandler
	public void recevie3(Map<String, Object> map,Channel channel,Message msg) {
		
		
		Account account = (Account) map.get("account");
		Acclog log = (Acclog) map.get("log");
		
		try {
			// redis中 已经有msgid 说明已经消费，避免重复消费
			boolean isConsumed = acclogService.existsInRedis(log.getMsgid());
			
			if (!isConsumed) {
				System.out.println("接收消息3："+account.getBank());
				accountService.addToRedis(account);
				acclogService.addToRedis(log);
				acclogService.addMsgidToRedis(log.getMsgid());
				
			}
			channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
			
		} catch (IOException e) {
			try {
				Integer retrycount = log.getRetrycount();
				// 重试次数超过限制
				if (retrycount.compareTo(Constants.MAX_RETRYCOUNT) > 0) {
					
					resendService.addFailToRedis(map);
				}
				else { // 需要重试
					resendService.addNeedRetryToRedis(map);
				}
				
				channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, false);	
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}			
		}	
	}
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
}
