package com.zhiling.bank.controller;

import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.entity.Exchange;
import com.zhiling.bank.entity.User;
import com.zhiling.bank.serivce.ExchangeServiceClint;
import com.zhiling.bank.serivce.UserServiceClint;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class ExchangeController {

    @Resource
    private ExchangeServiceClint clint;


    @GetMapping (value = "/fandall/{pageNum}")
    public CommonResult fandall(@PathVariable int pageNum){
        return clint.fandall(pageNum);
    }

    @PostMapping(value = "/update")
    public CommonResult update(Exchange vo){
        return clint.update(vo);
    };

}
