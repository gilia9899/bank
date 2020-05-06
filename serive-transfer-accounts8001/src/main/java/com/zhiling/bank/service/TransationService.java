package com.zhiling.bank.service;

import com.zhiling.bank.entity.Transation;
import java.util.List;

/**
 * (Transation)表服务接口
 *
 * @author makejava
 * @since 2020-04-20 15:20:32
 */
public interface TransationService {

    /**
     * 通过ID查询单条数据
     *
     * @param code 主键
     * @return 实例对象
     */
    Transation queryById(String code);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Transation> queryAllByLimit(int offset, int limit);


    /**
     * 修改数据
     *
     * @param transation 实例对象
     * @return 实例对象
     */
    Transation update(Transation transation);

    /**
     * 通过主键删除数据
     *
     * @param code 主键
     * @return 是否成功
     */
    boolean deleteById(String code);

    /**
     * 插入数据
     * @param transation
     * @return
     */
    int insert(Transation transation);

}