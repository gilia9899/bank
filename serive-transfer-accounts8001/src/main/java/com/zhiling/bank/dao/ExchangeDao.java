package com.zhiling.bank.dao;

import com.zhiling.bank.entity.Exchange;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Exchange)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-27 15:55:05
 */
@Mapper
public interface ExchangeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param localnum 主键
     * @return 实例对象
     */
    Exchange queryById(Integer localnum);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Exchange> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param exchange 实例对象
     * @return 对象列表
     */
    List<Exchange> queryAll(Exchange exchange);

    /**
     * 新增数据
     *
     * @param exchange 实例对象
     * @return 影响行数
     */
    int insert(Exchange exchange);

    /**
     * 修改数据
     *
     * @param exchange 实例对象
     * @return 影响行数
     */
    int update(Exchange exchange);

    /**
     * 通过主键删除数据
     *
     * @param localnum 主键
     * @return 影响行数
     */
    int deleteById(Integer localnum);

}