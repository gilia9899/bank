package com.zhiling.bank.service.impl;

import com.zhiling.bank.dao.TransationDao;
import com.zhiling.bank.entity.Account;
import com.zhiling.bank.dao.AccountDao;
import com.zhiling.bank.entity.Transation;
import com.zhiling.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private TransationDao transationDao;

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Override
    public List<Account> queryByUserid(Integer userid) {
        return accountDao.queryByUserid(userid);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param accno 主键
     * @return 实例对象
     */
    @Override
    public Account queryById(Integer accno) {
        return this.accountDao.queryById(accno);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
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
     * 行内转账用户余额变更
     * @param inner
     * @param outer
     * @param money
     *
     */
    @Override
    @Transactional
    public boolean intraBankTransfer(Transation transation,int inner, int outer, double money) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //判断是否修改成功，成功则返回true，失败则回滚并返回false
            accountDao.inner(inner,money);
            accountDao.outer(outer,money);
            transationDao.insert(transation);
            return true;
        }catch (Exception e){
            transactionManager.rollback(status);
            return false;
        }
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