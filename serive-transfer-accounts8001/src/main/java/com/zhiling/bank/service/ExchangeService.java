package com.zhiling.bank.service;

import com.zhiling.bank.entity.Exchange;
import java.util.List;

/**
 * (Exchange)表服务接口
 *
 * @author makejava
 * @since 2020-04-27 15:55:05
 */
public interface ExchangeService {

    /**
     * 通过ID查询单条数据
     *
     * @param localnum 主键
     * @return 实例对象
     */
    Exchange queryById(Integer localnum);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Exchange> queryAllByLimit(int offset, int limit);

    /**
     * 查询所有
     * @return
     */
    List<Exchange> queryAll();

    /**
     * 新增数据
     *
     * @param exchange 实例对象
     * @return 实例对象
     */
    Exchange insert(Exchange exchange);

    /**
     * 修改数据
     *
     * @param exchange 实例对象
     * @return 实例对象
     */
    Exchange update(Exchange exchange);

    /**
     * 通过主键删除数据
     *
     * @param localnum 主键
     * @return 是否成功
     */
    boolean deleteById(Integer localnum);

}