package com.zhiling.bank.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.parser.ParserConfig;
import com.rabbitmq.client.AMQP.Exchange;
import com.zhiling.bank.service.RabbitConfirmCallBack;
import com.zhiling.bank.service.RabbitReturnCallback;
import com.zhiling.bank.util.Constants;
import com.zhiling.bank.util.MySerializer;

@Configuration
public class AppConfig {
	
	@Autowired
	RedisConnectionFactory redisConnectionFactory;
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	RabbitConfirmCallBack confirmCallback;
	
	@Autowired
	RabbitReturnCallback returnCallback;
	
	@PostConstruct
	public void initRabbitTemplate() {
		rabbitTemplate.setConfirmCallback(confirmCallback);
		rabbitTemplate.setReturnCallback(returnCallback);
	}
	@Bean(name = Constants.MYREDISTEMPLATE)
	public RedisTemplate<String, Object> customRedisTemplate() {
		
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
		redisTemplate.setHashKeySerializer(redisTemplate.getStringSerializer());
		MySerializer<Object> mySerializer = new MySerializer<Object>(Object.class);
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		ParserConfig.getGlobalInstance().addAccept("com.zhiling.bank.pojo");
		
		redisTemplate.setHashValueSerializer(mySerializer);
		redisTemplate.setValueSerializer(mySerializer);
		return redisTemplate;
		
	}
	
	/******************* queue exchange bindings *******************************/
	
	@Bean
	public Queue accque() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(Constants.X_MAX_LENGTH, 1000);
		param.put(Constants.X_DEAD_LETTER_EXCHANGE, "backupexchange");
		//param.put(Constants.X_DEAD_LETTER_ROUTING_KEY, "rb");
		return new Queue(Constants.QUEUE_NAME, true, false, false, param);
		
	}
	/*
	@Bean
	public DirectExchange accExchange() {
		return ExchangeBuilder.directExchange(Constants.EXCHANGE_NAME).build();
	}
	*/
	
	@Bean
	public FanoutExchange accExchange () {
		return ExchangeBuilder.fanoutExchange(Constants.EXCHANGE_NAME).durable(true).build();
		//return ExchangeBuilder.directExchange("aa58").durable(false).build();
		
	}
	
	
	@Bean
	public Binding accBinding() {
		return BindingBuilder.bind(accque()).to(accExchange());
	}
	
	//************* backup1 *********
	
	@Bean
	public Queue deadque() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(Constants.X_MAX_LENGTH, 2000);
		param.put(Constants.X_DEAD_LETTER_EXCHANGE, "backupexchange2");
		return new Queue("backupque1", true, false, false, param);
		
	}
	@Bean
	public FanoutExchange deadexchange() {
		return ExchangeBuilder.fanoutExchange("backupexchange").build();
	}
	@Bean
	public Binding deadbinding() {
		return BindingBuilder.bind(deadque()).to(deadexchange());
	}
	
	
	//*********** backup2 ****
	
	@Bean
	public Queue  backupque2() {
		return new Queue("backupque2", true, false, false, null);
	}
	@Bean
	public FanoutExchange backupexchange2() {
		return ExchangeBuilder.fanoutExchange("backupexchange2").build();
	}
	@Bean
	public Binding backupbinding2() {
		return BindingBuilder.bind(backupque2()).to(backupexchange2());
	}
	

	
	
	
	
}
