package com.zhiling.bank.controller;

import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.entity.User;
import com.zhiling.bank.serivce.UserServiceClint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

        System.out.println("消费者的获取："+vo.getUsername());
        return clint.islogin(vo);
    }

    @PostMapping(value = "/user/res")
    public CommonResult register(User vo) {
        return clint.register(vo);
    }

    @GetMapping(value = "/user/find/{userid}")
    public CommonResult findById(@PathVariable("userid") Integer userid) {
        return clint.findById(userid);
    }

}
