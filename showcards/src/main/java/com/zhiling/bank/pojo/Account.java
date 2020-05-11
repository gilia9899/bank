package com.zhiling.bank.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Account implements Serializable {
	
    private Integer accno;
    @NotBlank(message = "密码不能为空")
    private String accpwd;
    
    private Integer userid;
    @NotBlank(message = "balance不能为空")
    private String balance;
    @NotBlank(message = "银行不能为空")
    private String bank;
    
    private Date createdate;

    private String info1;

    private String info2;

    private static final long serialVersionUID = 1L;

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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
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