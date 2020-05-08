package com.zhiling.bank.dao;

import com.zhiling.bank.entity.Transation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface TransationDao {


    List<Transation> listTransation(Map map);

    Integer getCount();

    Transation getTransationByCode(String code);




    String getRealNameByUserId(Integer userid);

    String getRealNameByTargetno(Integer targetno);



}
