package com.zhiling.bank.dao;

import com.zhiling.bank.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Account)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-20 15:20:06
 */
@Mapper
public interface AccountDao {

    /**
     * 通过ID查询单条数据
     *
     * @param accno 主键
     * @return 实例对象
     */
    Account queryById(Integer accno);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Account> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 按用户查询银行卡
     * @param userid
     * @return
     */
    List<Account> queryByUserid(int userid);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param account 实例对象
     * @return 对象列表
     */
    List<Account> queryAll(Account account);

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 影响行数
     */
    int insert(Account account);

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 影响行数
     */
    int update(Account account);

    /**
     * 转入
     * @param inner
     * @param money
     * @return
     */
    int inner(@Param("inner")int inner,@Param("money") double money);

    /**
     * 转入
     * @param outer
     * @param money
     * @return
     */
    int outer(@Param("outer")int outer,@Param("money") double money);
    /**
     * 通过主键删除数据
     *
     * @param accno 主键
     * @return 影响行数
     */
    int deleteById(Integer accno);

}