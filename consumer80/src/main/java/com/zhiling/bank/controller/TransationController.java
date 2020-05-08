package com.zhiling.bank.controller;

import com.zhiling.bank.entity.PageBean;
import com.zhiling.bank.entity.Transation;
import com.zhiling.bank.serivce.TransationServiceClint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuxinchao
 * @date 2020/4/22 20:57
 */
@RestController
@RequestMapping("/tc")
public class TransationController {

    @Resource
    private TransationServiceClint transationServiceClint;

    @RequestMapping(value = "listTransations", method = RequestMethod.GET)
    public PageBean<Transation> listTransations(String currentPage, String pagesize,String userid){
        return transationServiceClint.listTransations(currentPage,pagesize,userid);
    }


    @RequestMapping(value = "getTransationByCode", method = RequestMethod.POST)
    public Transation getTransationByCode(String code) {
        return transationServiceClint.getTransationByCode(code);
    }


}
