package com.zhiling.bank.control;

import com.zhiling.bank.entity.User;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class UserController {

    private static final String Stu_URL = "http://STUDENT-SERVICE";//服务名

    @Resource
    private RestTemplate restTemplate;

    public User islogin(){
        return null ;
    }


}
