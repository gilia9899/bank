package com.zhiling.bank.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiling.bank.dao.AcclogMapper;
import com.zhiling.bank.pojo.Acclog;
import com.zhiling.bank.service.AcclogService;
import com.zhiling.bank.util.Constants;

@Service
public class AcclogServiceImpl implements AcclogService {
	
	@Resource(name = Constants.MYREDISTEMPLATE)
	private RedisTemplate<String, Acclog> redisTemplate;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	AcclogMapper logDao;
	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void deleteById(Integer id) {
		logDao.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void insert(Acclog acclog) {
		logDao.insert(acclog);
	}

	

	@Override
	public List<Acclog> findAll() {
		return logDao.selectAll();
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void updateById(Acclog acclog) {
		logDao.updateByPrimaryKey(acclog);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void batchinsert(List<Acclog> logs) {
		logDao.batchinsert(logs);
		
	}

	@Override
	public Acclog findById(Integer id) {
		return logDao.selectByPrimaryKey(id);
	}

	@Override
	public void addToRedis(Acclog acclog) {
		
		redisTemplate.opsForList().rightPush(Constants.REDIS_KEY_LOGS, acclog);
	}

	@Override
	public List<Acclog> getAllFromRedis() {
		return redisTemplate.opsForList().range(Constants.REDIS_KEY_LOGS, 0, -1);
	}

	@Override
	public List<Acclog> getRangeFromRedis(long start, long end) {
		return redisTemplate.opsForList().range(Constants.REDIS_KEY_LOGS, start, end);
	}

	@Override
	public boolean existsInRedis(String msgid) {
		
		return stringRedisTemplate.opsForSet().isMember(Constants.REDIS_KEY_MSGIDS, msgid);
	}

	@Override
	public void addMsgidToRedis(String msgid) {
		
		stringRedisTemplate.opsForSet().add(Constants.REDIS_KEY_MSGIDS, msgid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteFromRedis(long count) {
		redisTemplate.execute(new SessionCallback() {

			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				operations.opsForList().trim(Constants.REDIS_KEY_LOGS, count, Long.MAX_VALUE);
				
				return null;
			}
		});
	}

}
