package com.zhiling.bank.dao;

import com.zhiling.bank.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserDAO extends Mapper<User> {

}
