package com.zhiling.bank.controller;

import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.entity.Transation;
import com.zhiling.bank.service.AccountService;
import com.zhiling.bank.service.IntraBankTransferService;
import com.zhiling.bank.service.TransationService;
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
     * @param request
     * @return
     */
    @PostMapping("intraBankTransfer")
    public CommonResult intraBankTransfer(HttpServletRequest request){
        int userid = Integer.parseInt(request.getParameter("userid"));
        String phone = request.getParameter("phone");
        String type = request.getParameter("type");
        String risk = request.getParameter("risk");
        String massage= request.getParameter("massage");

        int inner = Integer.parseInt(request.getParameter("inner"));
        int outer = Integer.parseInt(request.getParameter("outer"));
        double money = Double.parseDouble(request.getParameter("money"));

        CommonResult commonResult = new CommonResult();

        //查询目标用户，若用户不存在，则返回错误
        Account account = accountService.queryById(inner);
        if(account == null || "中国银行".equals(account.getBank())){
            commonResult.setCode(2);
            commonResult.setMessage("没找到该用户");
            return commonResult;
        }
        Transation transation = new Transation();
        //主键另有算法
        String code = "code";
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