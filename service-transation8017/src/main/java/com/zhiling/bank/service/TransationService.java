package com.zhiling.bank.service;

import com.zhiling.bank.entity.Transation;

import java.util.List;
import java.util.Map;

public interface TransationService {


    List<Transation> listTransation(Map map);

    Integer getCount();

    Transation getTransationByCode(String code);



    String getRealNameByUserId(Integer userid);

    String getRealNameByTargetno(Integer targetno);

}
