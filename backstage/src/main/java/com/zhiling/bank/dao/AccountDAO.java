package com.zhiling.bank.dao;

import com.zhiling.bank.entity.Account;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface AccountDAO extends Mapper<Account> {
    @Select("select * from account where accno = #{id}")
    Account selectById(Integer id);

    @Update("update account set balance = #{balance} where accno = #{accno}")
    int update(Account account);
}
