package com.zhiling.bank.entity;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable {

    private Integer currPage;   //当前页码
    private Integer pageSize;   //每页显示条数
    private Integer count;      //总记录数
    private Integer totalPage;  //总页码数
    private List<T> objList;    //每页要显示的数据


    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getObjList() {
        return objList;
    }

    public void setObjList(List<T> objList) {
        this.objList = objList;
    }

}
