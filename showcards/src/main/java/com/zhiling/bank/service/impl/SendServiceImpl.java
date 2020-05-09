package com.zhiling.bank.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhiling.bank.pojo.Acclog;
import com.zhiling.bank.pojo.Account;
import com.zhiling.bank.service.SendService;
import com.zhiling.bank.util.Constants;

@Component
public class SendServiceImpl implements SendService {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Override
	public void sendMessage(Account acc) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", acc);
		Acclog log = createAcclog();
		map.put("log", log);
		Message msg = MessageBuilder.withBody(JSONObject.toJSONString(map,SerializerFeature.WriteClassName).getBytes()).build();
		CorrelationData correlationData = new CorrelationData();
		correlationData.setReturnedMessage(msg);
		System.out.println("发消息—— "+acc.getBank());
		rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, "", map,correlationData);
		
		//rabbitTemplate.convertAndSend("a", "", map,correlationData);
		//rabbitTemplate.convertAndSend("aa58", "bbb", map,correlationData);
		
	}
	
	
	private Acclog createAcclog() {
		
		String msgid = System.currentTimeMillis()+UUID.randomUUID().toString();
		String status = Constants.MSG_STATUS_SUCCESS;
		Date createtime = new Date(); 
		Date updatetime = createtime;
		Date nextretrytime = addTime(updatetime);
		Integer retrycount = 0;
		Acclog acclog = new Acclog(msgid, createtime, nextretrytime, retrycount, status, updatetime);
		
		return acclog;
	}
	
	private Date addTime(Date old) {
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(old);
		calendar.add(Calendar.MINUTE, Constants.RETRY_INTERVAL);
		return calendar.getTime();
	}


	@Override
	public void sendMessage(Map<String, Object> map) {
		Message msg = MessageBuilder.withBody(JSONObject.toJSONString(map,SerializerFeature.WriteClassName).getBytes()).build();
		CorrelationData correlationData = new CorrelationData();
		correlationData.setReturnedMessage(msg);
		rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, "", map,correlationData);
	}

	
	
}
