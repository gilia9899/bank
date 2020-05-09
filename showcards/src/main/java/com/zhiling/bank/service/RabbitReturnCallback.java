package com.zhiling.bank.service;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class RabbitReturnCallback implements ReturnCallback {
	
	@Autowired
	ResendService resendService;

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		
		System.out.println("找不到队列--replycode: "+replyCode+"--replytext--"+replyText+"--exchange--"+exchange+"--routingkey--"+routingKey);
		Map<String,Object> map = null;
		try(
				ByteArrayInputStream bi = new ByteArrayInputStream(message.getBody());
				ObjectInputStream oi = new ObjectInputStream(bi);
			){
				map = (Map<String, Object>) oi.readObject();
		} catch (Exception e) {
		}
		resendService.addFailToRedis(map);
	}

}
