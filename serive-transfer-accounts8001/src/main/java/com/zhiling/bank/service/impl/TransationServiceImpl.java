package com.zhiling.bank.service.impl;

import com.zhiling.bank.entity.Transation;
import com.zhiling.bank.dao.TransationDao;
import com.zhiling.bank.service.TransationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Transation)表服务实现类
 *
 * @author makejava
 * @since 2020-04-20 15:20:32
 */
@Service
public class TransationServiceImpl implements TransationService {
    @Resource
    private TransationDao transationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param code 主键
     * @return 实例对象
     */
    @Override
    public Transation queryById(String code) {
        return this.transationDao.queryById(code);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Transation> queryAllByLimit(int offset, int limit) {
        return this.transationDao.queryAllByLimit(offset, limit);
    }

    /**
     * 修改数据
     *
     * @param transation 实例对象
     * @return 实例对象
     */
    @Override
    public Transation update(Transation transation) {
        this.transationDao.update(transation);
        return this.queryById(transation.getCode());
    }

    /**
     * 通过主键删除数据
     *
     * @param code 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String code) {
        return this.transationDao.deleteById(code) > 0;
    }

    /**
     * 插入数据
     * @param transation
     * @return
     */
    @Override
    public int insert(Transation transation) {
        return transationDao.insert(transation);
    }
}