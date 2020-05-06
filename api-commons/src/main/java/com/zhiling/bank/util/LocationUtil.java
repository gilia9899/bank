package com.zhiling.bank.util;

import cn.hutool.json.JSONObject;
import com.zhiling.bank.entity.Location;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class LocationUtil {

    private static String url_str="https://restapi.amap.com/v3/ip?key=5fc83e70a0b8f3bf3cc1a248b18efe7d";

    /**
     * 获得一个查询到的城市JSONObject对象
     * @return
     */
    public JSONObject getLocationByIp(){
        StringBuffer stringBuffer=new StringBuffer();

        try {
            URL url=new URL(url_str);
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


        return jsonObject;
    }


    /**
     * 把json对象封装到Location实体类中
     * @return
     */
    public Location getLocation(){

        //LocationUtil locationUtil=new LocationUtil();
        JSONObject jsonObject= this.getLocationByIp();

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




}
