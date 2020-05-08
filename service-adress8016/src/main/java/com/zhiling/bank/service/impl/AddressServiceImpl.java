package com.zhiling.bank.service.impl;

import com.zhiling.bank.dao.AddressDao;
import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.Address;
import com.zhiling.bank.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressDao addressDao;


    @Override
    public List<Address> listAddress(Map map) {
        return addressDao.listAddress(map);
    }

    @Override
    public int getCount() {
        return addressDao.getCount();
    }

    @Override
    public void addAddress(Address address) {

        addressDao.addAddress(address);
    }

    @Override
    public Integer getUseridByAccno(Account account) {
        Integer re=addressDao.getUseridByAccno(account);
        return re;
    }

    @Override
    public Integer dropAddressByAccno(Address address) {
        return addressDao.dropAddressByAccno(address);
    }


}
