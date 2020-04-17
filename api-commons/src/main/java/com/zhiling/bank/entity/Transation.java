package com.zhiling.bank.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Transation)实体类
 *
 * @author makejava
 * @since 2020-04-17 14:14:14
 */
public class Transation implements Serializable {
    private static final long serialVersionUID = 734713807019793771L;
    
    private String code;
    
    private Integer userid;
    
    private Integer accno;
    
    private Integer targetno;
    
    private String phone;
    
    private Date createdate;
    
    private Date targetdate;
    
    private String balance;
    
    private String type;
    
    private String risk;
    
    private String message;
    
    private String info1;
    
    private String info2;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getAccno() {
        return accno;
    }

    public void setAccno(Integer accno) {
        this.accno = accno;
    }

    public Integer getTargetno() {
        return targetno;
    }

    public void setTargetno(Integer targetno) {
        this.targetno = targetno;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getTargetdate() {
        return targetdate;
    }

    public void setTargetdate(Date targetdate) {
        this.targetdate = targetdate;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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