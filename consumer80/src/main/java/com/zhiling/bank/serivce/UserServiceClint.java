package com.zhiling.bank.serivce;

import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.entity.User;
import com.zhiling.bank.serivce.impl.UserServiceClintImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "SYSTEM-CONTROL")
public interface UserServiceClint {

    @GetMapping(value = "/user/test/{name}")
    CommonResult test(@PathVariable("name") String name);

    @PostMapping(value = "/user/islogin")
    CommonResult islogin(@RequestBody User vo);

    @PostMapping(value = "/user/res")
    CommonResult register(@RequestBody User vo);

    @GetMapping(value = "/user/find/{userid}")
    CommonResult findById(@PathVariable("userid") Integer userid);

    @PostMapping(value = "/user/update")
    CommonResult update(@RequestBody User vo);

    @GetMapping(value = "/user/getcode/{phone}")
    CommonResult getCode(@PathVariable("phone") String phone);
}
