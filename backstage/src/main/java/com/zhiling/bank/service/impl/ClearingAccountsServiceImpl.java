package com.zhiling.bank.service.impl;

import com.zhiling.bank.dao.AccountDAO;
import com.zhiling.bank.dao.TransationDAO;
import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.Transation;
import com.zhiling.bank.service.ClearingAccountsService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
@Service
public class ClearingAccountsServiceImpl implements ClearingAccountsService {



    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Transation> redisTemplate;

    @Resource
    private AccountDAO dao;

    @Resource
    private TransationDAO dao2;



    @Override
    public void read(String key) {

        List<Transation> list = redisTemplate.opsForList().range("AllTranstation",0,-1);
        for (int i=0;i<list.size();i++){
            Integer acc = list.get(i).getAccno();
            Integer tar = list.get(i).getTargetno();
            Integer bal = Integer.valueOf(list.get(i).getBalance());
            clear(acc,bal);
            clear(tar,-bal);


        }

    }

    @Override
    public boolean clear(Integer acc,  Integer bal) {

        Account account = dao.selectByPrimaryKey(acc);
        Integer bal2 = Integer.valueOf(account.getBalance());
        account.setBalance(String.valueOf(bal2-bal));
        int flag = dao.updateByPrimaryKey(account);

        if (flag>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Integer running(String key) {
        Timer timer = new Timer(true);


        timer.schedule(

                new java.util.TimerTask() { public void run()

                {read(key); } }, 0, 24*60*60*1000);
        return 1;
    }

    @Override
    public List<Transation> sel(String key) {

        List<Transation> list2 = dao2.selectAll();
        List<Transation> list1 = redisTemplate.opsForList().range("AllTranstation",0,-1);
        List<Transation> list = new ArrayList<>();
        for (Transation a :list2) {
            list.add(a);
        }
        for (Transation b :list1) {
            list.add(b);
        }
        return  list;

    }
}
