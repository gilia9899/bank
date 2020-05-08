package com.zhiling.bank.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Account)实体类
 *
 * @author makejava
 * @since 2020-04-17 14:14:10
 */
public class Account implements Serializable {
    private static final long serialVersionUID = 480456070837132952L;
    
    private Integer accno;
    
    private String accpwd;
    
    private Integer userid;
    
    private String balance;
    /**
    * 开卡日期
    */
    private Date createdate;
    /**
    * 开户行
    */
    private String bank;
    
    private String info1;
    
    private String info2;

    @Override
    public String toString() {
        return "Account{" +
                "accno=" + accno +
                ", accpwd='" + accpwd + '\'' +
                ", userid=" + userid +
                ", balance='" + balance + '\'' +
                ", createdate=" + createdate +
                ", bank='" + bank + '\'' +
                ", info1='" + info1 + '\'' +
                ", info2='" + info2 + '\'' +
                '}';
    }

    public Integer getAccno() {
        return accno;
    }

    public void setAccno(Integer accno) {
        this.accno = accno;
    }

    public String getAccpwd() {
        return accpwd;
    }

    public void setAccpwd(String accpwd) {
        this.accpwd = accpwd;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
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