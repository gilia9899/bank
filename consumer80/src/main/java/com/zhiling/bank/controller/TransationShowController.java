package com.zhiling.bank.controller;

import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.serivce.ExchangeServiceClint;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class TransationShowController {

    @Resource
    private ExchangeServiceClint clint;


    @GetMapping (value = "/alltransation/{pageNum}")
    public CommonResult manageMember(@PathVariable(value = "pageNum") int pageNum){return clint.manageMember(pageNum);}

    @GetMapping(value = "/clear")
    public CommonResult clear(){return clint.clear();}

}
