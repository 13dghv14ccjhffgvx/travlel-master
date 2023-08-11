package com.hjq.dao;

import com.hjq.domain.Route;

import java.util.List;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-30  22:20
 * @Description: TODO
 * @Version: 1.0
 */
public interface RouteDao {

    /**
     * 查询cid总记录数
     * @param cid
     * @return
     */
    public int findTotalCount(int cid,String routeName);

    /**
     * 根据当前的页码查询数据集合
     * @param cid
     * @param start 从哪条记录开始
     * @param pageSize
     * @return
     */
    public List<Route> findByPage(int cid,int start,int pageSize,String routeName);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    public Route findOne(int rid);


    /**
     * 最新旅游
     * @return 线路集合
     */
    public List<Route> newestRoute();

    /**
     * 主题旅游
     * @return
     */
    public List<Route> themeRoutes();

    /**
     * 国内旅游推荐
     * @return
     */
    public List<Route> domesticTours();

    /**
     * 国外旅游推荐
     * @return
     */
    public List<Route> abroadTours();


}
