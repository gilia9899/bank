package com.zhiling.bank.controller;

import com.zhiling.bank.entity.Address;
import com.zhiling.bank.entity.PageBean;
import com.zhiling.bank.serivce.AddressServiceClint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuxinchao
 * @date 2020/4/22 20:57
 */
@RestController
@RequestMapping("/ac")
public class AddressController {
    @Resource
    private AddressServiceClint addressServiceClint;

    @RequestMapping(value = "listAddress",method = RequestMethod.GET)
    public PageBean<Address> listAddress(String currentPage, String pagesize,String userid){
        System.out.println("进入消费者listadress");
        return addressServiceClint.listAddress(currentPage,pagesize,userid);
    }

    @RequestMapping(value = "addAddress",method = RequestMethod.POST)
    public void addAddress(Address address){
        System.out.println("进入消费者addAddress");
        addressServiceClint.addAddress(address);
    }

    @RequestMapping(value = "dropAddress",method = RequestMethod.POST)
    public void dropAddress(Address address){
        System.out.println("进入消费者dropAddress");
        addressServiceClint.dropAddress(address);
    }



}
