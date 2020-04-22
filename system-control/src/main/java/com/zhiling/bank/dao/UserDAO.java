package com.zhiling.bank.dao;

import com.zhiling.bank.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {

    User islogin(User vo);

    int register(User vo);

    User findById(Integer userid);

    int update(User vo);

    int updateLoginDate(User vo);

}
