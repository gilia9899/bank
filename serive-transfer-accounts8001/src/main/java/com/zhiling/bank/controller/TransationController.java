package com.zhiling.bank.controller;

import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.entity.Transation;
import com.zhiling.bank.service.AccountService;
import com.zhiling.bank.service.IntraBankTransferService;
import com.zhiling.bank.service.TransationService;
import com.zhiling.bank.tool.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * (Transation)表控制层
 *
 * @author makejava
 * @since 2020-04-20 15:20:32
 */
@RestController
@RequestMapping("transation")
public class TransationController {
    /**
     * 服务对象
     */
    @Autowired
    private TransationService transationService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private IntraBankTransferService intraBankTransferService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Transation selectOne(String id) {

        return this.transationService.queryById(id);
    }

    /**
     * 行内转账
     * @param transation
     * @param inner
     * @param outer
     * @param money
     * @return
     */
    @PostMapping("intraBankTransfer")
    public CommonResult intraBankTransfer(@RequestBody Transation transation, @RequestParam("inner") Integer inner, @RequestParam("outer") Integer outer, @RequestParam("money") Double money){

        CommonResult commonResult = new CommonResult();
        Account checkBalance = accountService.queryById(outer);
        if(Double.parseDouble(checkBalance.getBalance()) < money){
            commonResult.setCode(2);
            commonResult.setMessage("余额不足");
            return commonResult;
        }

        //查询目标用户，若用户不存在，则返回错误
        Account account = accountService.queryById(inner);
        System.out.println(account);
        if(account == null || !"中国银行".equals(account.getBank())){
            commonResult.setCode(2);
            commonResult.setMessage("没找到该用户");
            return commonResult;
        }

        //转账
        boolean isSuccess = intraBankTransferService.intraBankTransfer(transation,inner,outer,money);
        if(isSuccess){
            commonResult.setCode(1);
            commonResult.setMessage("转账成功");
            return commonResult;
        }else {
            commonResult.setCode(2);
            commonResult.setMessage("转账失败");
            return commonResult;
        }
    }

}