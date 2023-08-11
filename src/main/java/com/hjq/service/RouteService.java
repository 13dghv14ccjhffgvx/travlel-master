package com.hjq.service;

import com.hjq.domain.PageBean;
import com.hjq.domain.Route;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.util.List;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-30  22:11
 * @Description: TODO
 * @Version: 1.0
 */
public interface RouteService {

    /**
     * 根据类别进行分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageBean<Route> pageQuery(int cid,int currentPage,int pageSize,String routeName);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    public Route findOne(String rid);

    /**
     * 最新旅游推荐
     * @return
     */
    public List<Route> newestRoute();

    /**
     * 主题旅游推荐
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
