package com.zhiling.bank.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.service.ClearingAccountsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ClearingAccountsController {
    @Resource
    private ClearingAccountsService acservice;

    @PostMapping(value = "/clear")
    public CommonResult checkPWD(String key) {
        int flag = acservice.running(key);
        if (flag == 1) {
            return new CommonResult(200, "操作成功", true);
        } else {
            return new CommonResult(404, "操作失败", false);
        }
    }


    @RequestMapping("/manageMember")
    public CommonResult manageMember(@RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize,
                               String key){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo=new PageInfo(acservice.sel(key));
        CommonResult c = new CommonResult();
        c.setData(pageInfo);
        return  c;
    }
}
