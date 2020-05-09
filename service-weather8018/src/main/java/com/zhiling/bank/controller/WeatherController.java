package com.zhiling.bank.controller;

import cn.hutool.json.JSONObject;
import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.entity.Lives;
import com.zhiling.bank.entity.Location;
import com.zhiling.bank.entity.Weather;
import com.zhiling.bank.util.LocationUtil;
import com.zhiling.bank.util.WeatherUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/wc")
public class WeatherController {




    @RequestMapping(value = "getLocation",method = RequestMethod.GET)
    public Location getLocation(){

        LocationUtil locationUtil=new LocationUtil();
        JSONObject jsonObject= locationUtil.getLocationByIp();

        String status=(String)jsonObject.get("status");
        String info=(String)jsonObject.get("info");
        String infocode=(String)jsonObject.get("infocode");
        String province=(String)jsonObject.get("province");
        String city=(String)jsonObject.get("city");
        String adcode=(String)jsonObject.get("adcode");
        String rectangle=(String)jsonObject.get("rectangle");

        Location location=new Location(status,info,infocode,province,city,adcode,rectangle);
        return location;
    }

    @RequestMapping(value = "getWeatherByAdcode",method = RequestMethod.GET)
    public CommonResult getWeatherByAdcode(){
        LocationUtil locationUtil=new LocationUtil();
        Location location=locationUtil.getLocation();
        //获得一个城市编码
        String code=location.getAdcode();
        System.out.println("城市编码："+code);
        WeatherUtil weatherUtil=new WeatherUtil();
        Weather wea=weatherUtil.getWeather(code);
        System.out.println("status:"+wea.getStatus());


        System.out.println("查询天气返回之前");
        return new CommonResult(200,"成功",wea);
    }





}
