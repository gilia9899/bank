package com.zhiling.bank.service.impl;

import com.zhiling.bank.dao.AccountDao;
import com.zhiling.bank.dao.TransationDao;
import com.zhiling.bank.entity.Transation;
import com.zhiling.bank.service.OffBankTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

/**
 * @author LiZheng
 * @date 2020/4/27 9:41
 */
@Service
public class OffBankTransferServiceImpl implements OffBankTransferService {

    @Resource(name = "redisTemplate")
    private RedisTemplate<String,Transation> redisTemplate;

    @Override
    public boolean offBankTransfer(Transation transation) {
        redisTemplate.opsForList().rightPush("AllTranstation",transation);
        return true;
    }
}
