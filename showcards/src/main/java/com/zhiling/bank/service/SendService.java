package com.zhiling.bank.service;

import java.util.Map;

import com.zhiling.bank.pojo.Account;

public interface SendService {
	
	public void sendMessage(Account acc);
	
	public void sendMessage(Map<String, Object> map);
	
	
	
	
	
}
