package com.zhiling.bank.controller;

import com.zhiling.bank.entity.Account;
import com.zhiling.bank.entity.Address;
import com.zhiling.bank.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zhiling.bank.entity.PageBean;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ac")
public class AddressController {

    @Resource
    private AddressService addressService;


    @RequestMapping(value = "la",method = RequestMethod.GET)
    public String show(){
        return "test";
    }



    @ResponseBody
    @RequestMapping(value = "listAddress",method = RequestMethod.GET)
    public PageBean<Address> listAddress(String currentPage, String pagesize){
        System.out.println("进入listadress服务");
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

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("start", (currPage-1)*pageSize);
        map.put("pageSize", pageSize);

        List<Address> addressList=addressService.listAddress(map);

        //总记录数
        int count=addressService.getCount();
        //总页码数
        int totalPage=(count-1)/pageSize+1;
        //新建PageBean对象
        PageBean<Address>  addressPageBean= new PageBean<Address>();
        addressPageBean.setCount(count);
        addressPageBean.setCurrPage(currPage);
        addressPageBean.setPageSize(pageSize);
        addressPageBean.setTotalPage(totalPage);
        addressPageBean.setObjList(addressList);
        System.out.println("listAddress返回之前");
        return addressPageBean;

    }

    @ResponseBody
    @RequestMapping(value = "addAddress",method = RequestMethod.POST)
    public void addAddress(@RequestBody Address address){
        System.out.println("进入新增后台服务");
        int accno=address.getAccno();
        Account account= new Account();
        account.setAccno(accno);
        Integer userId=addressService.getUseridByAccno(account);
        if(userId!=null){
            address.setUserid(userId);
            addressService.addAddress(address);
            System.out.println("新增成功");
        }else {
            System.out.println("没有这个卡号");

        }

    }



    @ResponseBody
    @RequestMapping(value = "dropAddress",method = RequestMethod.POST)
    public void dropAddress(@RequestBody Address address){
        System.out.println("进入服务者dropAddress方法");
        System.out.println("accno:"+address.getAccno());
        Integer result=addressService.dropAddressByAccno(address);
        System.out.println("result:"+result);
        if (result>0){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }

    }





}
