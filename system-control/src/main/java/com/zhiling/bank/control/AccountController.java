package com.zhiling.bank.control;

import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.entity.User;
import com.zhiling.bank.service.AccountService;
import com.zhiling.bank.tool.Md5UUIDSaltUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AccountController {

    @Resource
    private AccountService acservice;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/acc/checkpwd")
    public CommonResult checkPWD(Account vo) {
        Account ac = acservice.checkPWD(vo);
        if (ac != null) {
            return new CommonResult(200, "查询成功 ", ac);
        } else {
            return new CommonResult(404, "查询失败", null);
        }
    }
}
