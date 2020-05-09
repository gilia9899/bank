package com.zhiling.bank.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.zhiling.bank.pojo.Acclog;
import com.zhiling.bank.pojo.Account;
import com.zhiling.bank.service.ResendService;
import com.zhiling.bank.service.SendService;
import com.zhiling.bank.util.Constants;
@Component
public class ResendServiceImpl implements ResendService {
	
	@Resource(name = Constants.MYREDISTEMPLATE)
	RedisTemplate<String, Map<String, Object>> redisTemplate;
	
	@Autowired
	SendService sendService;

	@Override
	public void addFailToRedis(Map<String,Object> map) {
		
		Acclog log = (Acclog) map.get("log");
		//Account account = (Account) map.get("account");
		//System.out.println(log.getMsgid());
		//System.out.println(account.getBank());
		Date updatetime = new Date();
		log.setStatus(Constants.MSG_STATUS_FAILED);
		log.setUpdatetime(updatetime);
		redisTemplate.opsForList().rightPush(Constants.REDIS_KEY_FAILED, map);
	}

	@Override
	public void addNeedRetryToRedis(Map<String,Object> map) {
		
		Acclog log = (Acclog) map.get("log");
		//Account acc = (Account)map.get("account");
		//System.out.println(log.getMsgid());
		//System.out.println(acc.getBank());
		log.setStatus(Constants.MSG_STATUS_SENDING);
		Date updatetime = new Date();
		Date nextretrytime = addTime(updatetime);
		log.setRetrycount(log.getRetrycount() + 1);
		redisTemplate.opsForList().rightPush(Constants.REDIS_KEY_RESENDIDS, map);
 	}
	
	private Date addTime(Date old) {
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(old);
		calendar.add(Calendar.MINUTE, Constants.RETRY_INTERVAL);
		return calendar.getTime();
	}

	@Override
	public void resend(List<Map<String, Object>> maps) {
		
		for (Map<String, Object> map : maps) {
			sendService.sendMessage(map);
		}
	}

	
	
	
	
	
	
	
	
	
}
