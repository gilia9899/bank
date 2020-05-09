package com.zhiling.bank.controller;

import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.entity.Location;
import com.zhiling.bank.entity.Weather;
import com.zhiling.bank.serivce.WeatherServiceClint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuxinchao
 * @date 2020/4/22 20:57
 */
@RestController
@RequestMapping("/wc")
public class WeatherController {

    @Resource
    private WeatherServiceClint weatherServiceClint;

    @RequestMapping(value = "getLocation",method = RequestMethod.GET)
    public Location getLocation(){
        return  weatherServiceClint.getLocation();
    }

    @RequestMapping(value = "getWeatherByAdcode",method = RequestMethod.GET)
    public CommonResult getWeatherByAdcode(){
        System.out.println("进入天气consumer服务");
        return weatherServiceClint.getWeatherByAdcode();
    }


}
