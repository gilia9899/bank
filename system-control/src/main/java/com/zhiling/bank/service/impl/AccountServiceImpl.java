package com.zhiling.bank.service.impl;

import com.zhiling.bank.dao.AccountDAO;
import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO adao;

    public CommonResult checkPWD(Account vo){
        //Example example=new Example(Account.class);
        //Example.Criteria criteria=example.createCriteria();
        //criteria.andBetween("price",10,99);
        //List<Account> accounts = adao.selectByExample(example);
        adao.selectOne(vo);

        return new CommonResult(200,"查询成功",null);
    }

}
