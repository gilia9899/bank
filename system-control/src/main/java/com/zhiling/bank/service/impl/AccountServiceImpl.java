package com.zhiling.bank.service.impl;

import com.zhiling.bank.dao.AccountDAO;
import com.zhiling.bank.entity.Account;
import com.zhiling.bank.service.AccountService;
import com.zhiling.bank.tool.Md5UUIDSaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO adao;

    public Account checkPWD(Account vo){
        //Example example=new Example(Account.class);
        //Example.Criteria criteria=example.createCriteria();
        //criteria.andBetween("price",10,99);
        //List<Account> accounts = adao.selectByExample(example);
        if (vo.getAccno()==null||vo.getAccno().equals("")){
            return null;
        }
        if (vo.getAccpwd()==null||vo.getAccpwd().equals("")){
            return null;
        }
        Account ac = adao.selectOne(vo);

        String salt = ac.getInfo1();
        String password = vo.getAccpwd();
        String md5Code = Md5UUIDSaltUtil.createMd5Code(password+salt);
        if (ac.getAccpwd().equals(md5Code)) {
            return ac;
        }else {
            return null;
        }
    }

}
