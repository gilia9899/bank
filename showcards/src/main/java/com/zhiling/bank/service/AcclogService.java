package com.zhiling.bank.service;

import java.util.List;

import com.zhiling.bank.pojo.Acclog;


public interface AcclogService {
	
	void deleteById(Integer id) ;
	
	void insert(Acclog acclog);
	
	Acclog findById(Integer id);
	
	List<Acclog> findAll();
	
	void updateById(Acclog acclog);
	
	void batchinsert(List<Acclog> logs);
	
	void addToRedis(Acclog acclog);
	
	List<Acclog> getAllFromRedis();
	
	List<Acclog> getRangeFromRedis(long start,long end);
	
	boolean existsInRedis(String msgid);
	
	void addMsgidToRedis(String msgid);
	
	void deleteFromRedis(long count);
}
