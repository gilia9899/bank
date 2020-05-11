package com.zhiling.bank.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiling.bank.dao.AccountMapper;
import com.zhiling.bank.pojo.Account;
import com.zhiling.bank.service.AccountService;
import com.zhiling.bank.util.Constants;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Resource(name = Constants.MYREDISTEMPLATE)
	RedisTemplate<String, Account> redisTemplate;
	
	@Autowired
	AccountMapper accDao;
	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void deleteById(Integer accno) {
		accDao.deleteByPrimaryKey(accno);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void insert(Account acc) {
		accDao.insert(acc);
	}

	@Override
	public Account findByAccno(Integer accno) {
		return accDao.selectByPrimaryKey(accno);
	}

	@Override
	public List<Account> findAll() {
		return accDao.selectAll();
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void updateById(Account acc) {
		accDao.updateByPrimaryKey(acc);
	}

	@Override
	public List<Account> findByUserid(Integer userid) {
		return accDao.findByUserid(userid);
	}

	@Override
	public int count(Integer userid) {
		return accDao.count(userid);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void batchinsert(List<Account> accounts) {
		accDao.batchinsert(accounts);
	}

	@Override
	public void addToRedis(Account account) {
		
		redisTemplate.opsForList().rightPush(Constants.REDIS_KEY_ACCOUNTS, account);
	}

	@Override
	public List<Account> getAllFromRedis() {
		
		return redisTemplate.opsForList().range(Constants.REDIS_KEY_ACCOUNTS, 0, -1);
	}

	@Override
	public List<Account> getRangeFromRedis(long start, long end) {
		
		return redisTemplate.opsForList().range(Constants.REDIS_KEY_ACCOUNTS, start, end);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteFromRedis(long count) {
		redisTemplate.execute(new SessionCallback() {

			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				operations.opsForList().trim(Constants.REDIS_KEY_ACCOUNTS, count, Long.MAX_VALUE);
				return null;
			}
		});
	}

}
