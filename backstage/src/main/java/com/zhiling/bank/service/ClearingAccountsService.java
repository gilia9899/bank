package com.zhiling.bank.service;

import com.zhiling.bank.entity.Transation;
import com.zhiling.bank.entity.User;

import java.util.List;

public interface ClearingAccountsService {

    public void  read(String key);

    public boolean clear(Integer a,Double c);

    public Integer running(String key);

    public List<Transation> sel(String key);


}
