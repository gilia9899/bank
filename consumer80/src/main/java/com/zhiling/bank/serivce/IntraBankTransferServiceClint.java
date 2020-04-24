package com.zhiling.bank.serivce;

import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.Address;
import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.entity.Transation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LiZheng
 * @date 2020/4/22 20:57
 */
@Component
@FeignClient(value = "TransationServer")
public interface IntraBankTransferServiceClint {

    /**
     * 行内转账
     * @param inner
     * @param outer
     * @param transation
     * @param money
     * @return
     */
    @PostMapping("transation/intraBankTransfer")
    CommonResult intraBankTransfer(@RequestBody Transation transation, @RequestParam("inner") Integer inner, @RequestParam("outer") Integer outer, @RequestParam("money") Double money);

    @GetMapping("account/queryByAccountUserid/{userid}")
    CommonResult<List<Account>> queryAccountByUserid(@PathVariable(value = "userid") int userid);

    @GetMapping("address/queryByAddressUserid/{userid}")
    CommonResult<List<Address>> queryAddressByUserid(@PathVariable(value = "userid") int userid);
}
