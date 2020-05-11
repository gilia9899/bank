package com.zhiling.bank.service;

import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
@Component
public class RabbitConfirmCallBack implements ConfirmCallback {
	
	@Autowired
	ResendService resendService;

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		
		if (!ack) {
			System.out.println("找不到exchange");
			Message msg = correlationData.getReturnedMessage();
			Map<String,Object> map = JSONObject.parseObject(new String(msg.getBody()), Map.class);
			resendService.addNeedRetryToRedis(map);
		}
		
	}
	
	
	
}
