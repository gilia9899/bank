package com.zhiling.bank.controller;

import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Account)表控制层
 *
 * @author makejava
 * @since 2020-04-20 15:20:06
 */
@RestController
@RequestMapping("account")
public class AccountController {
    /**
     * 服务对象
     */
    @Autowired
    private AccountService accountService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne/{id}")
    public Account selectOne(@PathVariable Integer id) {
        return this.accountService.queryById(id);
    }

    @GetMapping("queryByAccountUserid/{userid}")
    public CommonResult<List<Account>> queryAccountByUserid(@PathVariable int userid){
        List<Account> list = accountService.queryByUserid(userid);
        System.out.println(list);
        CommonResult<List<Account>> result = new CommonResult<>();
        result.setCode(1);
        result.setData(list);
        return result;
    }
}