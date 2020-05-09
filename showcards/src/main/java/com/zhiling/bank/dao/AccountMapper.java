package com.zhiling.bank.dao;

import com.zhiling.bank.pojo.Account;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface AccountMapper {
    int deleteByPrimaryKey(Integer accno);

    int insert(Account record);

    Account selectByPrimaryKey(Integer accno);

    List<Account> selectAll();

    int updateByPrimaryKey(Account record);
    
    List<Account> findByUserid(Integer userid);
    
    int count(Integer userid);  
    
    void batchinsert(List<Account> accounts);
    
}