package com.zhiling.bank.controller;

import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.Address;
import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Address)表控制层
 *
 * @author makejava
 * @since 2020-04-20 15:22:24
 */
@RestController
@RequestMapping("address")
public class AddressController {
    /**
     * 服务对象
     */
    @Autowired
    private AddressService addressService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Address selectOne(Integer id) {
        return this.addressService.queryById(id);
    }

    @GetMapping("queryByAddressUserid/{userid}")
    public CommonResult<List<Address>> queryAddressByUserid(@PathVariable int userid){
        List<Address> list = addressService.queryByUserid(userid);
        CommonResult<List<Address>> result = new CommonResult<>();
        result.setCode(1);
        result.setData(list);
        return result;
    }

}