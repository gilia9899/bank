package com.zhiling.bank.controller;

import com.zhiling.bank.entity.*;
import com.zhiling.bank.serivce.TransferServiceClint;
import com.zhiling.bank.tool.OrderUtil;
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
public class TransferController {
    @Resource
    private TransferServiceClint clint;

    @PostMapping("intraBankTransfer")
    public CommonResult intraBankTransfer(HttpServletRequest request){
        System.out.println(request.getParameter("massage"));
        int userid = Integer.parseInt(request.getParameter("userid"));
        String phone = request.getParameter("phone");
        String type = request.getParameter("type");
        String massage= request.getParameter("massage");
        String pwd = request.getParameter("pwd");
        System.out.println("消费者pwd");

        int inner = Integer.parseInt(request.getParameter("inner"));
        int outer = Integer.parseInt(request.getParameter("outer"));
        double money = Double.parseDouble(request.getParameter("money"));

        String info1 = request.getParameter("select_change");
        String info2 = request.getParameter("rate");

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
        transation.setMessage(massage);
        transation.setInfo1(info1);
        transation.setInfo2(info2);

        return clint.intraBankTransfer(transation,pwd,inner,outer,money);
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

    @GetMapping("queryAll")
    List<Exchange> queryAll(){
        return clint.queryAll();
    }
}
