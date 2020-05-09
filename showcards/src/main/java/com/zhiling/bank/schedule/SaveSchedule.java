package com.zhiling.bank.schedule;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhiling.bank.pojo.Acclog;
import com.zhiling.bank.pojo.Account;
import com.zhiling.bank.service.AcclogService;
import com.zhiling.bank.service.AccountService;
import com.zhiling.bank.util.Constants;

@Component
public class SaveSchedule {
	
	@Resource(name = Constants.MYREDISTEMPLATE)
	RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	AcclogService logService;
	
	@Autowired
	AccountService accService;
	
	
	public void saveAccAndLog() {
		
		long count = redisTemplate.opsForList().size(Constants.REDIS_KEY_ACCOUNTS);
		if (count >= Constants.SAVE_TRIGGER) {
			int times = (int) (count / Constants.SAVE_PERTIME) + 1;
			long start = 0;
			long end = 0;
			long starttime = System.currentTimeMillis();
			long k = 0;
			long tmpend = Long.MAX_VALUE;
			for (int i = 0; i < times; i++) {
				start = i * Constants.SAVE_PERTIME;
				tmpend = count - start - 1;
				end = tmpend < start + Constants.SAVE_PERTIME- 1 ? tmpend : start + Constants.SAVE_PERTIME - 1 ;
				List<Account> accounts = accService.getRangeFromRedis(start, end);
				List<Acclog> logs = logService.getRangeFromRedis(start, end);
				accService.batchinsert(accounts);
				logService.batchinsert(logs);
				k += accounts.size();
			}
			accService.deleteFromRedis(count);
			logService.deleteFromRedis(count);
			long endtime = System.currentTimeMillis();
			
			System.out.println("存入数据库 "+k+" 条 耗时： " + (endtime - starttime) / 1000 + "  秒" );

		}
	}
	
	
}
