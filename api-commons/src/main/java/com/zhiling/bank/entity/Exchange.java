package com.zhiling.bank.entity;

import java.io.Serializable;

/**
 * (Exchange)实体类
 *
 * @author makejava
 * @since 2020-04-17 14:14:14
 */
public class Exchange implements Serializable {
    private static final long serialVersionUID = 594604546176720729L;
    
    private Integer localnum;
    
    private String local;
    
    private String rate;
    
    private String info1;
    
    private String info2;


    public Integer getLocalnum() {
        return localnum;
    }

    public void setLocalnum(Integer localnum) {
        this.localnum = localnum;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

}