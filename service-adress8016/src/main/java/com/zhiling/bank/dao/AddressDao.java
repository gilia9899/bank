package com.zhiling.bank.dao;

import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AddressDao {

    List<Address> listAddress(Map map);

    int getCount();

    void addAddress(Address address);

    Integer getUseridByAccno(Account account);


    Integer dropAddressByAccno(Address address);



}
