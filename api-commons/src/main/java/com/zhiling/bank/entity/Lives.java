package com.zhiling.bank.entity;

import java.io.Serializable;
import java.util.Date;

public class Lives implements Serializable {

    /**
     * 实况天气数据信息
     */
    private String province;        //省份名
    private String city;            //城市名
    private String adcode;          //区域编码
    private String weather;         //天气现象（汉字描述）
    private String temperature;     //实时气温，单位：摄氏度
    private String winddirection;   //风向描述
    private String windpower;       //风力级别，单位：级
    private String humidity;        //空气湿度
    private Date reporttime;      //数据发布的时间

    public Lives(){}


    public Lives(String province, String city, String adcode, String weather, String temperature, String winddirection, String windpower, String humidity, Date reporttime) {
        this.province = province;
        this.city = city;
        this.adcode = adcode;
        this.weather = weather;
        this.temperature = temperature;
        this.winddirection = winddirection;
        this.windpower = windpower;
        this.humidity = humidity;
        this.reporttime = reporttime;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWinddirection() {
        return winddirection;
    }

    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }

    public String getWindpower() {
        return windpower;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public Date getReporttime() {
        return reporttime;
    }

    public void setReporttime(Date reporttime) {
        this.reporttime = reporttime;
    }
}
