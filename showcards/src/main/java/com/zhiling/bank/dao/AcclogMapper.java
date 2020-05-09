package com.zhiling.bank.dao;

import com.zhiling.bank.pojo.Acclog;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface AcclogMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Acclog record);

    Acclog selectByPrimaryKey(Integer id);

    List<Acclog> selectAll();

    int updateByPrimaryKey(Acclog record);
    
    void batchinsert(List<Acclog> logs);
}