package com.zhiling.bank.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import com.zhiling.bank.dao.AccountDAO;
import com.zhiling.bank.dao.UserDAO;
import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.User;
import com.zhiling.bank.service.UserService;
import com.zhiling.bank.tool.Md5UUIDSaltUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO dao;

    @Resource
    private AccountDAO acdao;

    @Override
    public User islogin(User vo) {
        //System.out.println("服务提供者的获取："+vo.getUsername());
        //System.out.println("服务提供者的获取："+vo.getUserpwd());
        if (vo.getUsername() == null || "".equals(vo.getUsername())) {
            return null;
        }
        if (vo.getUserpwd() == null || "".equals(vo.getUserpwd())) {
            return null;
        }
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", vo.getUsername());
        List<User> all = dao.selectByExample(example);
        for (User u : all){
            String salt = u.getInfo1();
            String password = vo.getUserpwd();
            String md5Code = Md5UUIDSaltUtil.createMd5Code(password + salt);
            if (u.getUserpwd().equals(md5Code)) {
                this.updateLoginDate(u);
                return u;
            }
        }
            return null;
    }

    @Override
    public int register(User vo) {
        if (vo.getUserpwd() == null || "".equals(vo.getUserpwd())) {
            return -1;
        }
        if (vo.getUsername() == null || "".equals(vo.getUsername())) {
            return -1;
        }
        if (vo.getEmail() == null || "".equals(vo.getEmail())) {
            return -1;
        }
        if (vo.getIdcard() == null || "".equals(vo.getIdcard())) {
            return -1;
        }
        if (vo.getPhone() == null || "".equals(vo.getPhone())) {
            return -1;
        }
        if (vo.getRealname() == null || "".equals(vo.getRealname())) {
            return -1;
        }
        if (vo.getType() == null || "".equals(vo.getType())) {
            vo.setType("0");
        }
        //检测手机是否注册过
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phone", vo.getPhone());
        User checkU = dao.selectOneByExample(example);
        if (checkU!=null) {
            return -1;
        }

        String salt = Md5UUIDSaltUtil.getSalt();
        String initialPWD = vo.getUserpwd();
        String password = Md5UUIDSaltUtil.createMd5Code(vo.getUserpwd() + salt);
        vo.setInfo1(salt);
        vo.setUserpwd(password);
        int flag = dao.insert(vo);
        System.out.println("flag:"+flag);
        if (flag > -1) {
            User u = dao.selectOne(vo);
            if (u != null) {
                System.out.println("自动创建account");
                System.out.println(u.getUserid());
                Account ac = new Account();
                ac.setBalance("500");
                ac.setAccpwd(initialPWD);
                ac.setBank("中国银行");
                ac.setCreatedate(new Date());
                ac.setUserid(u.getUserid());
                String accno = RandomUtil.randomNumbers(10);
                ac.setAccno(Convert.toInt(accno));
                acdao.insert(ac);
            }

        }
        return 1;
    }

    @Override
    public User findById(Integer userid) {
        if (userid == null) {
            return null;
        }
        User u = new User();
        u.setUserid(userid);
        return dao.selectOne(u);
    }

    @Override
    public int update(User vo) {
        if (vo.getUserid() == null || "".equals(vo.getUserid())) {
            return -1;
        }
        return dao.updateByPrimaryKey(vo);
    }

    @Override
    public int updateLoginDate(User vo) {
        if (vo.getUserid() == null | vo.getUserid().equals("")) {
            return -1;
        }
        vo.setLogintime(new Date());
        return dao.updateByPrimaryKey(vo);
    }
}
