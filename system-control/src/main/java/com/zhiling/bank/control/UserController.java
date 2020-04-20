package com.zhiling.bank.control;

import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.entity.User;
import com.zhiling.bank.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService uservice;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/user/test/{name}")
    public CommonResult test(@PathVariable("name") String name) {
        return new CommonResult(200, "这是一个测试方法", name);
    }


    @GetMapping(value = "/user/islogin")
    public CommonResult islogin(User vo) {
        User u = uservice.islogin(vo);
        if (u == null) {
            return new CommonResult(404, "登录失败", null);
        } else {
            return new CommonResult(200, "登录成功", u);
        }
    }

    @PostMapping(value = "/user/res")
    public CommonResult register(User vo) {
        int flag = uservice.register(vo);
        if (flag > 0) {
            return new CommonResult(200, "注册成功", true);
        } else {
            return new CommonResult(404, "注册失败", false);
        }
    }

    @GetMapping(value = "/user/find/{userid}")
    public CommonResult findById(@PathVariable("userid") Integer userid) {
        User u = uservice.findById(userid);
        if (u != null) {
            return new CommonResult(200, "查询成功", u);
        } else {
            return new CommonResult(404, "查询失败", null);
        }
    }

    public CommonResult update(User vo) {
        int flag = uservice.update(vo);
        if (flag > 0) {
            return new CommonResult(200, "操作成功", true);
        } else {
            return new CommonResult(404, "操作失败", false);
        }
    }
}