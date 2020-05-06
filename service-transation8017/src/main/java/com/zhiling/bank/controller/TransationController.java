package com.zhiling.bank.controller;

import com.zhiling.bank.entity.PageBean;
import com.zhiling.bank.entity.Transation;
import com.zhiling.bank.service.TransationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tc")
public class TransationController {

    @Resource
    private TransationService transationService;

    @RequestMapping(value = "tt", method = RequestMethod.GET)
    public Integer getTeST(){
        return 1;
    }

    @RequestMapping(value = "listTransations", method = RequestMethod.GET)
    public PageBean<Transation> listTransations(String currentPage, String pagesize){
        System.out.println("进入listTransations方法");
        //当前页码
        int currPage=1;
        //每页显示条数
        int pageSize=5;

        if(currentPage!=null) {
            currPage=Integer.valueOf(currentPage);
        }
        if(pagesize!=null) {
            pageSize=Integer.valueOf(pagesize);
        }

        Map<String,Object> map1=new HashMap<String,Object>();
        map1.put("start", (currPage-1)*pageSize);
        map1.put("pageSize", pageSize);

        System.out.println("准备进入service的listTransation方法");
        List<Transation> tsList=transationService.listTransation(map1);
        System.out.println("准备进入service的getCount方法");
        Integer count=transationService.getCount();
        Integer totalPage=(count-1)/pageSize+1;

        PageBean<Transation> pt=new PageBean<>();
        pt.setCount(count);
        pt.setTotalPage(totalPage);
        pt.setPageSize(pageSize);
        pt.setCurrPage(currPage);
        pt.setObjList(tsList);

       /* Map<String,Object> map=new HashMap<>();
        map.put("PageBean",pt);*/

        System.out.println("准备返回i数据");
        return pt;
    }



    @RequestMapping(value = "getTransationByCode", method = RequestMethod.POST)
    public Transation getTransationByCode(String code){
        Transation transation=transationService.getTransationByCode(code);
        if (transation!=null){
            System.out.println("查询成功");
        }else{
            System.out.println("查询失败");
        }
        return transation;
    }









}
