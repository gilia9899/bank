package com.zhiling.bank.service.impl;

import com.zhiling.bank.dao.UserDAO;
import com.zhiling.bank.entity.User;
import com.zhiling.bank.service.UserService;
import com.zhiling.bank.tool.Md5UUIDSaltUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

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
        User u = dao.selectOne(vo);
        String salt = u.getInfo1();
        String password = vo.getUserpwd();
        String md5Code = Md5UUIDSaltUtil.createMd5Code(password+salt);
        if (u.getUserpwd().equals(md5Code)){
            this.updateLoginDate(u);
            return u;
        }else {
            return null;
        }
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
        if (vo.getType()==null||vo.getType().equals("")){
            vo.setType("0");
        }

        String salt = Md5UUIDSaltUtil.getSalt();
        String password = Md5UUIDSaltUtil.createMd5Code(vo.getUserpwd()+salt);
        vo.setInfo1(salt);
        vo.setUserpwd(password);
        return dao.insert(vo);
    }

    @Override
    public User findById(Integer userid) {
        if (userid==null){
            return null;
        }
        User u = new User();
        u.setUserid(userid);
        return dao.selectOne(u);
    }

    @Override
    public int update(User vo) {
        if (vo.getUserid()==null||vo.getUserid().equals("")){
            return -1;
        }
        return dao.updateByPrimaryKey(vo);
    }

    @Override
    public int updateLoginDate(User vo) {
        if (vo.getUserid()==null|vo.getUserid().equals("")){
            return -1;
        }
        vo.setLogintime(new Date());
        return dao.updateByPrimaryKey(vo);
    }
}
