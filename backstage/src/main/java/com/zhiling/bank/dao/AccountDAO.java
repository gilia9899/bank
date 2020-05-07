package com.zhiling.bank.dao;

import com.zhiling.bank.entity.Account;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface AccountDAO extends Mapper<Account> {
}
