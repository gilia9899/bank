package com.zhiling.bank.service;

import com.zhiling.bank.entity.Transation;

/**
 * @author LiZheng
 * @date 2020/4/22 8:47
 */
public interface IntraBankTransferService {
    /**
     * 行内转账
     * @param inner
     * @param outer
     * @param transation
     * @param money
     * @return
     */
    boolean intraBankTransfer(Transation transation, int inner, int outer, double money);
}
