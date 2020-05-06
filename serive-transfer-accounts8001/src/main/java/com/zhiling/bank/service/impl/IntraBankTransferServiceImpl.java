package com.zhiling.bank.service.impl;

import com.zhiling.bank.dao.AccountDao;
import com.zhiling.bank.dao.TransationDao;
import com.zhiling.bank.entity.Transation;
import com.zhiling.bank.service.IntraBankTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

/**
 * @author LiZheng
 * @date 2020/4/22 8:47
 */
@Service
public class IntraBankTransferServiceImpl implements IntraBankTransferService {
    @Resource
    private AccountDao accountDao;
    @Resource
    private TransationDao transationDao;

    @Autowired
    private DataSourceTransactionManager transactionManager;

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
            transactionManager.commit(status);
            return true;
        }catch (Exception e){
            transactionManager.rollback(status);
            return false;
        }
    }
}
