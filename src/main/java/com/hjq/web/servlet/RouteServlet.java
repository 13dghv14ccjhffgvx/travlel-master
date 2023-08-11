package com.hjq.web.servlet;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.hjq.domain.Favorite;
import com.hjq.domain.PageBean;
import com.hjq.domain.Route;
import com.hjq.domain.User;
import com.hjq.service.FavoriteService;
import com.hjq.service.RouteService;
import com.hjq.service.impl.FavoriteServiceImpl;
import com.hjq.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-30  21:58
 * @Description: TODO
 * @Version: 1.0
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * 分页查询
     * @param request
     * @param response
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response){
        //1.接收参数
        String currentPageStr = (String) request.getParameter("currentPage");
        String pageSizeStr = (String) request.getParameter("pageSize");
        //int cid = (int) request.getAttribute("cid");
        String cidStr = (String) request.getParameter("cid");

        //接收线路名称 rname
        String routeName = request.getParameter("rname");//tomcat7没有自动处理中文乱码问题，所以这里会出现参数传递乱码
        //处理中文乱码
        try {
            routeName = new String(routeName.getBytes("iso-8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            //之后改进：使用日志log打印
            System.out.println("参数routeName接收，中文乱码问题");
        }

        //2.处理参数
        int cid = 0;
        if(cidStr != null && cidStr.length()>0 && !"null".equals(cidStr)) { //这三个判断需要弄懂
            System.out.println("cidStr不为null");
            cid = Integer.parseInt(cidStr);
        }

        int currentPage = 0; //当前页码，如果不传递，则默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;//每页显示条数，如果不传递，则默认每页显示5条记录
        if (pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 5;
        }

        //3.调用service查询PageBean对象
        PageBean<Route> pageBean = routeService.pageQuery(cid, currentPage, pageSize,routeName);
        //4.将pageBean对象序列化为json,返回
        try {
            writeValue(pageBean,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("RouteServlet End...");

    }

    /**
     * 根据id查询一个旅游线路的详细信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {

        //1.接收id
        String rid = request.getParameter("rid");
        //2.调用service查询route对象
        Route route = routeService.findOne(rid);
        //3.转为json写回客户端
        writeValue(route,response);

    }

    /**
     * 判断当前登录用户是否收藏过该线路
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        //1.获取线路id
        String rid = request.getParameter("rid");

        //2.获取当前登录的用户uid
        User user = (User) request.getSession().getAttribute("user");
        int uid; //用户id
        if (user == null){ //用户尚未登录
            uid = 0;
        }else{ //用户已经登录
            uid = user.getUid();
        }

        //3.调用FavoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);

        //4.转为json,写回客户端
        writeValue(flag,response);
    }

    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        //1.获取线路id
        String rid = request.getParameter("rid");
        //2.获取用户id
        User user = (User) request.getSession().getAttribute("user");

        int uid;
        //判断用户是否登录
        if (user == null){ //未登录
            return;
        }else{
            uid = user.getUid();
        }

        //3.调用favoriteService查询该线路的收藏次数
        favoriteService.addFavorite(Integer.parseInt(rid),uid);
    }

    /**
     * 人气旅游推荐
     */
    public void popularRoute(HttpServletRequest request,HttpServletResponse response){

        //调用service查询受欢迎的前几个线路
        List<Route> routes = favoriteService.popularRoute();
//        List<Integer> list = null;
//        for (int i = 0; i < routes.size(); i++) {
//            list.add(routes.get(i).getRid());
//        }

        for (int i = 0; i < routes.size(); i++) {
            System.out.println(routes.get(i));
        }
        //4.转为json,写回客户端
        try {
            writeValue(routes,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 最新旅游
     * @param request
     * @param response
     */
    public void newestRoute(HttpServletRequest request,HttpServletResponse response){

        //调用routeService层的方法
        List<Route> routes = routeService.newestRoute();
        try {
            //转为json,写回客户端
            writeValue(routes,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 主题旅游推荐
     * @param request
     * @param response
     */
    public void themeRoutes(HttpServletRequest request,HttpServletResponse response){

        //调用routeService层
        List<Route> routes = routeService.themeRoutes();
        try {
            //将数据转为json,写回客户端
            writeValue(routes,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 国内旅游推荐
     * @param request
     * @param response
     */
    public void domesticTour(HttpServletRequest request,HttpServletResponse response){
        //调用service层
        List<Route> routes = routeService.domesticTours();
        try {
            //将数据转为json,写回客户端
            writeValue(routes,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 国外旅游推荐
     * @param request
     * @param response
     */
    public void abroadTour(HttpServletRequest request,HttpServletResponse response){
        //调用service层
        List<Route> routes = routeService.abroadTours();
        try{
            //将数据转为json,写回客户端
            writeValue(routes,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
