package com.zhiling.bank.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;

import com.zhiling.bank.pojo.Acclog;
import com.zhiling.bank.pojo.Account;
import com.zhiling.bank.service.AcclogService;
import com.zhiling.bank.service.AccountService;
import com.zhiling.bank.service.ResendService;
import com.zhiling.bank.service.SendService;
import com.zhiling.bank.util.Constants;

@Component
public class RetrySchedule {
	
	@Resource(name = Constants.MYREDISTEMPLATE)
	RedisTemplate<String, List<Map<String, Object>>> redisTemplate;
	
	@Autowired
	ResendService resendService;
	
	
	@SuppressWarnings("unchecked")
	public void dealRetry() {
		

		long starttime = System.currentTimeMillis();
		long k = 0;
		
		k = redisTemplate.execute(new SessionCallback<Long>() {

			@Override
			public Long execute(RedisOperations operations) throws DataAccessException {
				
				long count = redisTemplate.opsForList().size(Constants.REDIS_KEY_RESENDIDS);
				long tmp = 0;
				if (count > 0) {
					long times = count/Constants.RETRY_PERTIME + 1;
					long start = 0;
					long end = 0;
					long tmpend = Long.MAX_VALUE;
					List<Map<String,Object>> needRetryItems = null;
					List<Map<String,Object>> items = null;
					
					for (int i = 0; i < times; i++) {
						start = i * Constants.RETRY_PERTIME;
						tmpend = count - start - 1;
						end = tmpend < start + Constants.RETRY_PERTIME - 1 ? tmpend : start + Constants.RETRY_PERTIME - 1 ;
						items = operations.opsForList().range(Constants.REDIS_KEY_RESENDIDS, start, end);
						for (Map<String, Object> map : items) {
							if (needRetry(map)) {
								needRetryItems.add(map);
							}
						}
						
						resendService.resend(needRetryItems);
						for (Map<String, Object> map2 : needRetryItems) {
							redisTemplate.opsForList().remove(Constants.REDIS_KEY_RESENDIDS, 1, map2);
						}
						tmp += needRetryItems.size();
						needRetryItems.clear();
					}
				}
				return tmp;
			}
			
		});
		long endtime = System.currentTimeMillis();
		System.out.println("处理重试 " + k + " 条 耗时 " + (endtime - starttime) / 1000 + " 秒");

	}
	
	private boolean needRetry(Map<String, Object> map) {
		
		Acclog log = (Acclog) map.get("log");
		Date now = new Date();
		return Constants.MSG_STATUS_SENDING.equals(log.getStatus()) && now.compareTo(log.getNextretrytime()) > 0 && log.getRetrycount().compareTo(Constants.MAX_RETRYCOUNT) < 0;
	
		
		
		
	}
	
	
	
	
}
