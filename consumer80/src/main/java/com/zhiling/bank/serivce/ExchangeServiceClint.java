package com.zhiling.bank.serivce;

import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.entity.Exchange;
import com.zhiling.bank.entity.User;
import com.zhiling.bank.serivce.impl.UserServiceClintImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(value = "BACKSTAGE" )
public interface ExchangeServiceClint {

    @GetMapping (value = "/fandall/{pageNum}")
    public CommonResult fandall(@PathVariable(value = "pageNum") int pageNum);

    @PostMapping(value = "/update")
    public CommonResult update(Exchange vo);

    @GetMapping (value = "/alltransation/{pageNum}")
    public CommonResult manageMember(@PathVariable(value = "pageNum") int pageNum);

    @PostMapping(value = "/clear")
    public CommonResult clear();
}
