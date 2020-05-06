package com.zhiling.bank.service;

import com.zhiling.bank.entity.Transation;

/**
 * @author LiZheng
 * @date 2020/4/22 8:52
 */
public interface OffBankTransferService {
    /**
     * 行外转账
     * @param transation
     * @return
     */
    boolean offBankTransfer(Transation transation);
}
