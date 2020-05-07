package com.zhiling.bank.service.impl;

import com.zhiling.bank.entity.Exchange;
import com.zhiling.bank.dao.ExchangeDao;
import com.zhiling.bank.service.ExchangeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Exchange)表服务实现类
 *
 * @author makejava
 * @since 2020-04-27 15:55:05
 */
@Service
public class ExchangeServiceImpl implements ExchangeService {
    @Resource
    private ExchangeDao exchangeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param localnum 主键
     * @return 实例对象
     */
    @Override
    public Exchange queryById(Integer localnum) {
        return this.exchangeDao.queryById(localnum);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Exchange> queryAllByLimit(int offset, int limit) {
        return this.exchangeDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<Exchange> queryAll() {
        return exchangeDao.queryAll(null);
    }

    /**
     * 新增数据
     *
     * @param exchange 实例对象
     * @return 实例对象
     */
    @Override
    public Exchange insert(Exchange exchange) {
        this.exchangeDao.insert(exchange);
        return exchange;
    }

    /**
     * 修改数据
     *
     * @param exchange 实例对象
     * @return 实例对象
     */
    @Override
    public Exchange update(Exchange exchange) {
        this.exchangeDao.update(exchange);
        return this.queryById(exchange.getLocalnum());
    }

    /**
     * 通过主键删除数据
     *
     * @param localnum 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer localnum) {
        return this.exchangeDao.deleteById(localnum) > 0;
    }
}