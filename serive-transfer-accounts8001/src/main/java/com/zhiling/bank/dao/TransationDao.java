package com.zhiling.bank.dao;

import com.zhiling.bank.entity.Transation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Transation)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-20 15:20:32
 */
@Mapper
public interface TransationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param code 主键
     * @return 实例对象
     */
    Transation queryById(String code);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Transation> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param transation 实例对象
     * @return 对象列表
     */
    List<Transation> queryAll(Transation transation);

    /**
     * 新增数据
     *
     * @param transation 实例对象
     * @return 影响行数
     */
    int insert(Transation transation);

    /**
     * 修改数据
     *
     * @param transation 实例对象
     * @return 影响行数
     */
    int update(Transation transation);

    /**
     * 通过主键删除数据
     *
     * @param code 主键
     * @return 影响行数
     */
    int deleteById(String code);

}