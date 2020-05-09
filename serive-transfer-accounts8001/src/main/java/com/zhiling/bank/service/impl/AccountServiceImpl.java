package com.zhiling.bank.service.impl;

import com.zhiling.bank.entity.Account;
import com.zhiling.bank.dao.AccountDao;
import com.zhiling.bank.entity.Transation;
import com.zhiling.bank.service.AccountService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * (Account)表服务实现类
 *
 * @author makejava
 * @since 2020-04-20 15:20:06
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Transation> redisTemplate;

    @Override
    public List<Account> queryByUserid(Integer userid) {
        //获取该用户名下所有账户
        List<Account> accounts = accountDao.queryByUserid(userid);


        //从redis中查询所有记录
        List<Transation> transations = redisTemplate.opsForList().range("AllTranstation", 0, -1);

        //当redis中存在改用户记录时，直接返回账户记录
        if (transations == null) {
            return accounts;
        }
        //修改后的集合
        List<Account> newAccounts = new ArrayList<>();
        //判断记录中是否存在改用户账户的信息
        for (Account a : accounts) {
            for (Transation t : Objects.requireNonNull(transations)) {
                //当记录账户和用户一样，则转账相减
                if (a.getAccno().equals(t.getAccno())) {
                    String result = (Double.parseDouble(a.getBalance()) - Double.parseDouble(t.getBalance())) + "";
                    a.setBalance(result);
                }
            }
            newAccounts.add(a);
        }
        return newAccounts;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param accno 主键
     * @return 实例对象
     */
    @Override
    public Account queryById(Integer accno) {
        Account accounts = accountDao.queryById(accno);
        //从redis中查询所有记录
        List<Transation> transations = redisTemplate.opsForList().range("AllTranstation", 0, -1);

        //当redis中存在改用户记录时，直接返回账户记录
        if (transations == null) {
            return accounts;
        }
        //判断记录中是否存在改用户账户的信息
        for (Transation t : Objects.requireNonNull(transations)) {
            //当记录账户和用户一样，则转账相减
            if (accounts.getAccno().equals(t.getAccno())) {
                String result = (Double.parseDouble(accounts.getBalance()) - Double.parseDouble(t.getBalance())) + "";
                accounts.setBalance(result);
            }
        }
        return accounts;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Account> queryAllByLimit(int offset, int limit) {
        return this.accountDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @Override
    public Account insert(Account account) {
        this.accountDao.insert(account);
        return account;
    }

    /**
     * 通过主键删除数据
     *
     * @param accno 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer accno) {
        return this.accountDao.deleteById(accno) > 0;
    }
}