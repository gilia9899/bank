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
        if (vo.getUsername()==null||vo.getUsername().equals("")){
            return null;
        }
        if (vo.getUserpwd()==null||vo.getUserpwd().equals("")){
            return null;
        }
        return dao.islogin(vo);
    }

    @Override
    public int register(User vo) {
        if (vo.getUserpwd()==null||vo.getUserpwd().equals("")){
            return -1;
        }
        if (vo.getUsername()==null||vo.getUsername().equals("")){
            return -1;
        }
        if (vo.getEmail()==null||vo.getEmail().equals("")){
            return -1;
        }
        if (vo.getIdcard()==null||vo.getIdcard().equals("")){
            return -1;
        }
        if (vo.getPhone()==null||vo.getPhone().equals("")){
            return -1;
        }
        if (vo.getRealname()==null||vo.getRealname().equals("")){
            return -1;
        }
        return dao.register(vo);
    }

    @Override
    public User findById(Integer userid) {
        if (userid==null){
            return null;
        }
        return dao.findById(userid);
    }

    @Override
    public int update(User vo) {
        if (vo.getUserid()==null||vo.getUserid().equals("")){
            return -1;
        }
        return dao.update(vo);
    }
}
