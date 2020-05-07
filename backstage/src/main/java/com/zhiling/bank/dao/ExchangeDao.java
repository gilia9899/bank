package com.zhiling.bank.dao;

import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.Exchange;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface ExchangeDao extends Mapper<Exchange> {
}
