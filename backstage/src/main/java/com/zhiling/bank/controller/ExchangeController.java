package com.zhiling.bank.controller;

import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.entity.Exchange;
import com.zhiling.bank.entity.User;
import com.zhiling.bank.service.ExchangeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ExchangeController {
    @Resource
    private ExchangeService exchangeService;

    @PostMapping(value = "/fandall")
    public CommonResult fandall() {
        List<Exchange> e = exchangeService.fandall();
        if (e != null) {
            return new CommonResult(200, "查询成功", e);
        } else {
            return new CommonResult(404, "查询失败", null);
        }
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
