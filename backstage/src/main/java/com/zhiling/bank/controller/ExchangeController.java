package com.zhiling.bank.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.entity.Exchange;
import com.zhiling.bank.entity.User;
import com.zhiling.bank.service.ExchangeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ExchangeController {
    @Resource
    private ExchangeService exchangeService;

    @GetMapping(value = "/fandall/{pageNum}")
    public CommonResult fandall(@PathVariable int pageNum){
        int pageSize = 10;
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo=new PageInfo(exchangeService.fandall());
        CommonResult c = new CommonResult();
        c.setData(pageInfo);
        return  c;
    }

    @PostMapping(value = "/update")
    public CommonResult update(Exchange vo) {
        int flag = exchangeService.update(vo);
        if (flag > 0) {
            return new CommonResult(200, "操作成功", true);
        } else {
            return new CommonResult(404, "操作失败", false);
        }
    }
}
