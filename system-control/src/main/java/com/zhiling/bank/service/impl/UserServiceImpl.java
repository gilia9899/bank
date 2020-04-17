package com.zhiling.bank.service.impl;

import com.zhiling.bank.dao.UserDAO;
import com.zhiling.bank.entity.User;
import com.zhiling.bank.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO dao;

    @Override
    public User islogin(User vo) {
        return dao.islogin(vo);
    }

    @Override
    public int register(User vo) {
        return dao.register(vo);
    }

    @Override
    public User findById(Integer userid) {
        return dao.findById(userid);
    }

    @Override
    public int update(User vo) {
        return dao.update(vo);
    }
}
