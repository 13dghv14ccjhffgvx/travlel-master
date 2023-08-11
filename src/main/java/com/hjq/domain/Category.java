package com.hjq.domain;

import java.io.Serializable;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-28  17:03
 * @Description: 分类实体类
 * @Version: 1.0
 */
public class Category implements Serializable {

    private int cid;//分类id
    private String cname;//分类名称

    public Category(){
    }

    public Category(int cid,String cname){
        this.cid=cid;
        this.cname=cname;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
