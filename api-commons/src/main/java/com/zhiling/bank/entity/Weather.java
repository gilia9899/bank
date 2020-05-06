package com.zhiling.bank.entity;

import java.io.Serializable;
import java.util.List;

public class Weather implements Serializable {

    /**
     * 天气工具类
     */

    private String status;               //返回状态
    private String count;               //返回结果总数目
    private String info;                //返回的状态信息
    private String infocode;            //返回状态说明,10000代表正确
    private List<Lives> lives;      //实况天气数据信息
   // private String lives;

    public Weather(){}

    public Weather(String status, String count, String info, String infocode, List<Lives> livesList) {
        this.status = status;
        this.count = count;
        this.info = info;
        this.infocode = infocode;
        this.lives = livesList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public List<Lives> getLivesList() {
        return lives;
    }

    public void setLivesList(List<Lives> livesList) {
        this.lives = livesList;
    }


}
