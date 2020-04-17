package com.zhiling.bank.service;

import com.zhiling.bank.entity.User;

public interface UserService {
    User islogin(User vo);

    int register(User vo);

    User findById(Integer userid);

    int update(User vo);

}
