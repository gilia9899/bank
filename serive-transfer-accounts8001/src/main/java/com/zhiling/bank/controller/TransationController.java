package com.zhiling.bank.controller;

import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.entity.Transation;
import com.zhiling.bank.service.AccountService;
import com.zhiling.bank.service.IntraBankTransferService;
import com.zhiling.bank.service.OffBankTransferService;
import com.zhiling.bank.service.TransationService;
import com.zhiling.bank.tool.Md5UUIDSaltUtil;
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

    @Autowired
    private OffBankTransferService offBankTransferService;

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
    public CommonResult intraBankTransfer(@RequestBody Transation transation,@RequestParam("pwd") String pwd, @RequestParam("inner") Integer inner, @RequestParam("outer") Integer outer, @RequestParam("money") Double money){
        CommonResult commonResult = new CommonResult();
        Account check = accountService.queryById(outer);

        if(!check.getAccpwd().equals(pwd)){
            commonResult.setCode(2);
            commonResult.setMessage("密码错误");
            return commonResult;
        }

        System.out.println("当前转账模式为：" + transation.getType());
        //余额校验
        if(Double.parseDouble(check.getBalance()) < money){
            commonResult.setCode(2);
            commonResult.setMessage("余额不足");
            return commonResult;
        }
        //当不是行外转账时
        if(!"行内转账".equals(transation.getType())){

            //查询目标用户，若用户不存在，则返回错误
            Account account = accountService.queryById(inner);
            System.out.println(account);
            if(account == null || "中国银行".equals(account.getBank())){
                commonResult.setCode(2);
                commonResult.setMessage("没找到该用户");
                return commonResult;
            }
            transation.setRisk("审核中");
            offBankTransferService.offBankTransfer(transation);
            commonResult.setCode(1);
            commonResult.setMessage("转账成功，请等待审核");
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

        transation.setRisk("完成");
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

    //密码校验
    private boolean checkPwd(Integer outer,String pwd){
        Account check = accountService.queryById(outer);
        return Md5UUIDSaltUtil.checkPassword(pwd, check.getInfo1());
    }

}