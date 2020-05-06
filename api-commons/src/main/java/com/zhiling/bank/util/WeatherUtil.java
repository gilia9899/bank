package com.zhiling.bank.util;

import cn.hutool.json.JSONObject;
import com.zhiling.bank.entity.Lives;
import com.zhiling.bank.entity.Weather;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class WeatherUtil {

    private static String url_str="https://restapi.amap.com/v3/weather/weatherInfo?key=5fc83e70a0b8f3bf3cc1a248b18efe7d";

    /**
     * 根据城市编码查询到一个JSONObject对象
     * @param cityCode 城市编码
     * @return
     */
    public JSONObject getWeatherByAdcode(String cityCode){

        String city="&city="+cityCode;
        String nurl=url_str+city;
        StringBuffer stringBuffer=new StringBuffer();
        try {
            URL url=new URL(nurl);
            HttpsURLConnection urlcon = (HttpsURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlcon.getInputStream(),"utf-8"));
            String inputline = null;
            while((inputline=bufferedReader.readLine())!=null) {
                stringBuffer.append(inputline);
            }
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        String result=stringBuffer.toString();
        JSONObject jsonObject=new JSONObject(result);
        if(jsonObject!=null){
            System.out.println("jsonobject有值");
        }
        return jsonObject;
    }

    /**
     * 根据城市编码获取一个Weather对象
     * * @param cityCode 城市编码
     * @return
     */
    public Weather getWeather(String cityCode){

        //System.out.println("进入getWeather方法");
        JSONObject jsonObject=this.getWeatherByAdcode(cityCode);

        String status=(String)jsonObject.get("status");
        String count=(String)jsonObject.get("count");
        String info=(String)jsonObject.get("info");
        String infocode=(String)jsonObject.get("infocode");
        //System.out.println("准备获取lives");
        //String lives=(String)jsonObject.get("lives");
        List<Lives> lives=(List<Lives>)jsonObject.get("lives");
        //System.out.println("lives已经获取");
        //System.out.println("lives"+lives);

        Weather weather=new Weather(status,count,info,infocode,lives);

        return weather;

    }



}
