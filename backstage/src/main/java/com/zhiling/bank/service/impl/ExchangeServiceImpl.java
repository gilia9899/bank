package com.zhiling.bank.service.impl;

import com.zhiling.bank.dao.ExchangeDao;
import com.zhiling.bank.entity.Exchange;
import com.zhiling.bank.entity.User;
import com.zhiling.bank.service.ExchangeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ExchangeServiceImpl implements ExchangeService {
    @Resource
    private ExchangeDao dao;
    @Override
    public int update(Exchange vo) {
        if (vo.getLocalnum()==null||vo.getLocalnum().equals("")){
            return -1;
        }
        return dao.updateByPrimaryKey(vo);
    }


    @Override
    public List<Exchange> fandall() {

        return dao.selectAll();
    }

//    @Override
//    public String fandRateByLocal(String local) {
//        dao.selectByPrimaryKey();
//        return null;
//    }
}
