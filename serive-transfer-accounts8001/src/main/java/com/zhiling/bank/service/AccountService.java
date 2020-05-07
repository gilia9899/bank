package com.zhiling.bank.service;

import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.Transation;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * (Account)表服务接口
 *
 * @author makejava
 * @since 2020-04-20 15:20:06
 */
public interface AccountService {

    /**
     * 按用户查询银行卡
     * @param userid
     * @return
     */
    List<Account> queryByUserid(Integer userid);

    /**
     * 通过ID查询单条数据
     *
     * @param accno 主键
     * @return 实例对象
     */
    Account queryById(Integer accno);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Account> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    Account insert(Account account);
    /**
     * 通过主键删除数据
     *
     * @param accno 主键
     * @return 是否成功
     */
    boolean deleteById(Integer accno);

}