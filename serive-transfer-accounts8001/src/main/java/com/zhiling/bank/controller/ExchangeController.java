package com.zhiling.bank.controller;

import com.zhiling.bank.entity.Exchange;
import com.zhiling.bank.service.ExchangeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Exchange)表控制层
 *
 * @author makejava
 * @since 2020-04-27 15:55:05
 */
@RestController
@RequestMapping("exchange")
public class ExchangeController {
    /**
     * 服务对象
     */
    @Resource
    private ExchangeService exchangeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Exchange selectOne(Integer id) {
        return this.exchangeService.queryById(id);
    }

    @GetMapping("queryAll")
    public List<Exchange> queryAll(){
        return exchangeService.queryAll();
    }
}