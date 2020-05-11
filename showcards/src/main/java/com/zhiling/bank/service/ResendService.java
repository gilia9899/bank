package com.zhiling.bank.service;

import java.util.List;
import java.util.Map;

public interface ResendService {
	
	public void addFailToRedis(Map<String, Object> map);
	
	public void addNeedRetryToRedis(Map<String, Object> map);
	
	public void resend(List<Map<String, Object>> maps);
	
}
