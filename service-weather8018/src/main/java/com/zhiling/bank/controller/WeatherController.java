package com.zhiling.bank.controller;

import cn.hutool.json.JSONObject;
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
    public Weather getWeatherByAdcode(){
        LocationUtil locationUtil=new LocationUtil();
        Location location=locationUtil.getLocation();
        //获得一个城市编码
        String code=location.getAdcode();
        WeatherUtil weatherUtil=new WeatherUtil();
        Weather wea=weatherUtil.getWeather(code);

        /*String status=wea.getStatus();
        String count=wea.getCount();
        String info=wea.getInfo();
        String infocode=wea.getInfocode();
        List<Lives> livesList=wea.getLivesList();

        for (Lives lives:livesList){
            String province=lives.getProvince();            //省份名
            String city=lives.getCity();                    //城市名
            String adcode=lives.getAdcode();                //区域编码
            String weather=lives.getWeather();              //天气现象（汉字描述）
            String temperature=lives.getTemperature();      //实时气温，单位：摄氏度
            String winddirection=lives.getWinddirection();  //风向描述
            String windpower=lives.getWindpower();          //风力级别，单位：级
            String humidity=lives.getHumidity();            //空气湿度
            Date reporttime=lives.getReporttime();          //数据发布的时间


        }*/

        return wea;
    }





}
