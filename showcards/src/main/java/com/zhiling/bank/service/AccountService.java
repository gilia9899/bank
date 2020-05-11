package com.zhiling.bank.service;

import java.util.List;

import com.zhiling.bank.pojo.Account;

public interface AccountService {
	
	void deleteById(Integer accno) ;
	
	void insert(Account acc);
	
	Account findByAccno(Integer accno);
	
	List<Account> findAll();
	
	void updateById(Account acc);
	
	List<Account> findByUserid(Integer userid);
	
	int count(Integer userid);
	
	void batchinsert(List<Account> accounts);
	
	void addToRedis(Account account);
	
	List<Account> getAllFromRedis();
	
	List<Account> getRangeFromRedis(long start,long end);
	
	void deleteFromRedis(long count);
	
}
