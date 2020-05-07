package com.zhiling.bank.service;

import com.zhiling.bank.entity.Exchange;

import java.util.List;

public interface ExchangeService {

    public int update(Exchange vo);

    public List<Exchange> fandall();

//    public String fandRateByLocal(String local);
}
