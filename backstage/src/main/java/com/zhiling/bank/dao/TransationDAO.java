package com.zhiling.bank.dao;

import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.Transation;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface TransationDAO extends Mapper<Transation> {

}
