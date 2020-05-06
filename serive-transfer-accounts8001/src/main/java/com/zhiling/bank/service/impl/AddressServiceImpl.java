package com.zhiling.bank.service.impl;

import com.zhiling.bank.entity.Address;
import com.zhiling.bank.dao.AddressDao;
import com.zhiling.bank.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Address)表服务实现类
 *
 * @author makejava
 * @since 2020-04-20 15:22:24
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressDao addressDao;

    /**
     * 通过ID查询单条数据
     *
     * @param accno 主键
     * @return 实例对象
     */
    @Override
    public Address queryById(Integer accno) {
        return this.addressDao.queryById(accno);
    }

    @Override
    public List<Address> queryByUserid(Integer userid) {
        return addressDao.queryByUserid(userid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Address> queryAllByLimit(int offset, int limit) {
        return this.addressDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param address 实例对象
     * @return 实例对象
     */
    @Override
    public Address insert(Address address) {
        this.addressDao.insert(address);
        return address;
    }

    /**
     * 修改数据
     *
     * @param address 实例对象
     * @return 实例对象
     */
    @Override
    public Address update(Address address) {
        this.addressDao.update(address);
        return this.queryById(address.getAccno());
    }

    /**
     * 通过主键删除数据
     *
     * @param accno 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer accno) {
        return this.addressDao.deleteById(accno) > 0;
    }
}