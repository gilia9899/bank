package com.zhiling.bank.pojo;

import java.io.Serializable;
import java.util.Date;

public class Acclog implements Serializable {
    private Integer id;

    private String msgid;

    private Date createtime;

    private Date nextretrytime;

    private Integer retrycount;

    private String status;

    private Date updatetime;

    private static final long serialVersionUID = 1L;
    
    public Acclog() {
    	
    }
    
    
    public Acclog(String msgid, Date createtime,Date nextretrytime,  Integer retrycount, String status,
			Date updatetime) {
		super();
		this.msgid = msgid;
		this.createtime = createtime;
		this.nextretrytime = nextretrytime;
		this.retrycount = retrycount;
		this.status = status;
		this.updatetime = updatetime;
	}


	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getNextretrytime() {
        return nextretrytime;
    }

    public void setNextretrytime(Date nextretrytime) {
        this.nextretrytime = nextretrytime;
    }

    public Integer getRetrycount() {
        return retrycount;
    }

    public void setRetrycount(Integer retrycount) {
        this.retrycount = retrycount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}