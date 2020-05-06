package com.zhiling.bank.service;

import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.Address;

import java.util.List;
import java.util.Map;

public interface AddressService {


    /*
    * 分页查询收款人
    * */
    List<Address> listAddress(Map map);

    /*
    * 获取总记录数
    * */
    int getCount();

    /*
    * 新增收款人
    * */
    void addAddress(Address address);

    /*
    * 获取用户id依靠卡号
    * */
    Integer getUseridByAccno(Account account);

    /*
    * 删除收款人依靠卡号
    * */
    Integer dropAddressByAccno(Address address);

}
