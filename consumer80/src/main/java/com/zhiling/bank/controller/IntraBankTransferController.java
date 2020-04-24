package com.zhiling.bank.controller;

import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.Address;
import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.entity.Transation;
import com.zhiling.bank.serivce.IntraBankTransferServiceClint;
import com.zhiling.bank.tool.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author LiZheng
 * @date 2020/4/22 20:57
 */
@RestController
@RequestMapping("transation")
public class IntraBankTransferController {
    @Resource
    private IntraBankTransferServiceClint clint;

    @PostMapping("intraBankTransfer")
    public CommonResult intraBankTransfer(HttpServletRequest request){
        System.out.println(request.getParameter("massage"));
        int userid = Integer.parseInt(request.getParameter("userid"));
        String phone = request.getParameter("phone");
        String type = request.getParameter("type");
        String risk = request.getParameter("risk");
        String massage= request.getParameter("massage");

        int inner = Integer.parseInt(request.getParameter("inner"));
        int outer = Integer.parseInt(request.getParameter("outer"));
        double money = Double.parseDouble(request.getParameter("money"));

        Transation transation = new Transation();
        //添加主键
        String code = OrderUtil.getOrderNoByUUID("");
        transation.setCode(code);

        transation.setUserid(userid);
        transation.setAccno(outer);
        transation.setTargetno(inner);
        transation.setPhone(phone);
        transation.setCreatedate(new Date());
        transation.setBalance(money+"");
        transation.setType(type);
        transation.setRisk(risk);
        transation.setMessage(massage);

        return clint.intraBankTransfer(transation,inner,outer,money);
    }

    @GetMapping("queryAccountByUserid/{userid}")
    CommonResult<List<Account>> queryAccountByUserid(@PathVariable int userid){
        System.out.println("所有卡");
        return clint.queryAccountByUserid(userid);
    }

    @GetMapping("queryAddressByUserid/{userid}")
    CommonResult<List<Address>> queryAddressByUserid(@PathVariable int userid){
        return clint.queryAddressByUserid(userid);
    }
}
