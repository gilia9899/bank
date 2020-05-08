package com.zhiling.bank.serivce;

import com.zhiling.bank.entity.Address;
import com.zhiling.bank.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Component
@FeignClient(value = "adressservice")
public interface AddressServiceClint {

    @GetMapping("ac/listAddress")
     PageBean<Address> listAddress(@RequestParam("currentPage") String currentPage,@RequestParam("pagesize")String pagesize);


    @PostMapping("ac/addAddress")
     void addAddress( @RequestBody Address address);//@RequestBody


    @PostMapping("ac/dropAddress")
    void dropAddress(@RequestBody Address address);

}
