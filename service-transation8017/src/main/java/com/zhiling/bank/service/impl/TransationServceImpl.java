package com.zhiling.bank.service.impl;

import com.zhiling.bank.dao.TransationDao;
import com.zhiling.bank.entity.PageBean;
import com.zhiling.bank.entity.Transation;
import com.zhiling.bank.service.TransationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TransationServceImpl implements TransationService {

    @Resource
    private TransationDao transationDao;

    @Override
    public List<Transation> listTransation(Map map) {

        return transationDao.listTransation(map);
    }

    @Override
    public Integer getCount() {
        return transationDao.getCount();
    }

    @Override
    public Transation getTransationByCode(String code) {
        return transationDao.getTransationByCode(code);
    }

    @Override
    public String getRealNameByUserId(Integer userid) {
        return transationDao.getRealNameByUserId(userid);
    }

    @Override
    public String getRealNameByTargetno(Integer targetno) {
        return transationDao.getRealNameByTargetno(targetno);
    }
}
