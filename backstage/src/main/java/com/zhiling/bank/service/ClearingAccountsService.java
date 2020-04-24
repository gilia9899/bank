package com.zhiling.bank.service;

import com.zhiling.bank.entity.User;

import java.util.List;

public interface ClearingAccountsService {
    <T> List<T> read(String key);
}
