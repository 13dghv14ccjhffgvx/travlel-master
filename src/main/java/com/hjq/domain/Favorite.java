package com.hjq.domain;

import java.io.Serializable;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-28  17:07
 * @Description: 收藏实体类
 * @Version: 1.0
 */
public class Favorite implements Serializable {

    private int rid;//旅游线路对象
    private String date;//收藏时间
    private int uid;//所属用户

    /**
     * 无参构造方法
     */
    public Favorite(){}

    public Favorite(int rid, String date, int uid) {
        this.rid = rid;
        this.date = date;
        this.uid = uid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
