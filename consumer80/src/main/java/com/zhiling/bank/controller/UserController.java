package com.zhiling.bank.controller;

import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.entity.User;
import com.zhiling.bank.serivce.UserServiceClint;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    UserServiceClint clint;


    @GetMapping("/user/test/{name}")
    public CommonResult test(@PathVariable("name") String name) {
        return clint.test(name);
    }

    @PostMapping(value = "/user/islogin")
    public CommonResult islogin(User vo) {
        return clint.islogin(vo);
    }

    @PostMapping(value = "/user/res")
    public CommonResult register(User vo) {
        System.out.println(vo.getRealname());
        System.out.println(vo.getUsername());
        System.out.println(vo.getUserpwd());
        return clint.register(vo);
    }

    @PostMapping(value = "/user/update")
    public CommonResult update(User vo){
        return clint.update(vo);
    }

    @GetMapping(value = "/user/find/{userid}")
    public CommonResult findById(@PathVariable("userid") Integer userid) {
        return clint.findById(userid);
    }

    @GetMapping(value = "/user/getcode/{phone}")
    public CommonResult getCode(@PathVariable("phone")String phone){
        System.out.println("发送短信:"+phone);
        return clint.getCode(phone);
    }

}
