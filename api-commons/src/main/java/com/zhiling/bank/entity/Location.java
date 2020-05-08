package com.zhiling.bank.entity;

import java.io.Serializable;

public class Location implements Serializable {

    /**
     * ip查询
     */

    private String status;      //返回结果状态值,值为0或1,0表示失败；1表示成功
    private String info;        //返回状态说明,返回状态说明，status为0时，info返回错误原因，否则返回“OK”。
    private String infocode;    //状态码,返回状态说明,10000代表正确,详情参阅info状态表
    private String province;    //省份名称,	若为直辖市则显示直辖市名称；如果在局域网 IP网段内，则返回“局域网”；非法IP以及国外IP则返回空
    private String city;        //城市名称,若为直辖市则显示直辖市名称；如果为局域网网段内IP或者非法IP或国外IP，则返回空
    private String adcode;      //城市的adcode编码
    private String rectangle;   //所在城市矩形区域范围,所在城市范围的左下右上对标对

    public Location(){}
    public Location(String status, String info, String infocode, String province, String city, String adcode, String rectangle) {
        this.status = status;
        this.info = info;
        this.infocode = infocode;
        this.province = province;
        this.city = city;
        this.adcode = adcode;
        this.rectangle = rectangle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getRectangle() {
        return rectangle;
    }

    public void setRectangle(String rectangle) {
        this.rectangle = rectangle;
    }
}
