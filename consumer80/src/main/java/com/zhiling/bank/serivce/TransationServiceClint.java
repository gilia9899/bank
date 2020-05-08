package com.zhiling.bank.serivce;


import com.zhiling.bank.entity.PageBean;
import com.zhiling.bank.entity.Transation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "transationservice")
public interface TransationServiceClint {

    @GetMapping("tc/listTransations")
    PageBean<Transation> listTransations(@RequestParam("currentPage") String currentPage,@RequestParam("pagesize") String pagesize);


    @PostMapping("tc/getTransationByCode")
    Transation getTransationByCode(@RequestParam("code") String code);


}
