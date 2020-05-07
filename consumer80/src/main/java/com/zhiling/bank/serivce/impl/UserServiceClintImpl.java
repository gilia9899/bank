package com.zhiling.bank.serivce.impl;

import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.entity.User;
import com.zhiling.bank.serivce.UserServiceClint;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClintImpl implements UserServiceClint {
    @Override
    public CommonResult test(String name) {
        return new CommonResult(405,"Oh,error");
    }

    @Override
    public CommonResult islogin(User vo) {
        return new CommonResult(405,"Oh,error");
    }

    @Override
    public CommonResult register(User vo) {
        return new CommonResult(405,"Oh,error");
    }

    @Override
    public CommonResult findById(Integer userid) {
        return new CommonResult(405,"Oh,error");
    }

    @Override
    public CommonResult update(User vo) {
        return new CommonResult(405,"Oh,error");
    }

    @Override
    public CommonResult getCode(String phone) {
        return new CommonResult(405,"Oh,error");
    }
}
