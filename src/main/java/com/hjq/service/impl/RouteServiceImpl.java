package com.hjq.service.impl;

import com.hjq.dao.FavoriteDao;
import com.hjq.dao.RouteDao;
import com.hjq.dao.RouteImgDao;
import com.hjq.dao.SellerDao;
import com.hjq.dao.impl.FavoriteDaoImpl;
import com.hjq.dao.impl.RouteDaoImpl;
import com.hjq.dao.impl.RouteImgDaoImpl;
import com.hjq.dao.impl.SellerDaoImpl;
import com.hjq.domain.PageBean;
import com.hjq.domain.Route;
import com.hjq.domain.RouteImg;
import com.hjq.domain.Seller;
import com.hjq.service.RouteService;

import java.util.List;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-30  22:11
 * @Description: TODO
 * @Version: 1.0
 */
public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String routeName) {
        System.out.println("pageBean Service...");
        //封装PageBean
        PageBean<Route> pb = new PageBean<Route>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid,routeName);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;
        List<Route> list = routeDao.findByPage(cid, start, pageSize,routeName);
        pb.setList(list);
        System.out.println("list的长度为: "+list.size());
        for (int i =0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
        //设置总页数
        int pageCount = totalCount % pageSize == 0? totalCount / pageSize : (totalCount/pageSize)+1;
        pb.setTotalPage(pageCount);
        System.out.println("pageBean RouteService End");

        return pb;
    }

    @Override
    public Route findOne(String rid) {
        //1.根据id查询route对象
        Route route = routeDao.findOne(Integer.parseInt(rid));
        //2.根据route的id查询图片集合信息
        List<RouteImg> routeImgList = routeImgDao.findByRid(route.getRid());
        //将查询到的图片集合设置到route对象
        route.setRouteImgList(routeImgList);
        //3.根据route的sid（商家id）查询商家对象
        Seller seller = sellerDao.findOneById(route.getSid());
        //将查询到的商家对象信息设置到route对象中
        route.setSeller(seller);

        //4.查询此线路的收藏次数
        int favoriteCount = favoriteDao.findFavoriteCount(route.getRid());
        route.setCount(favoriteCount);
        return route;
    }

    @Override
    public List<Route> newestRoute() {
        //调用dao层的方法
        return routeDao.newestRoute();
    }

    @Override
    public List<Route> themeRoutes() {
        return routeDao.themeRoutes();
    }

    @Override
    public List<Route> domesticTours() {
        return routeDao.domesticTours();
    }

    @Override
    public List<Route> abroadTours() {
        return routeDao.abroadTours();
    }
}
