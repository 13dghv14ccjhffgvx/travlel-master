package com.hjq.web.servlet;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.hjq.service.UserService;
import com.hjq.service.impl.UserServiceImpl;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-30  11:06
 * @Description: TODO
 * @Version: 1.0
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    //声明UserService业务对象
    private UserService service = new UserServiceImpl();

    /**
     * 注册功能
     * @param request
     * @param response
     */
    public void register(HttpServletRequest request, HttpServletResponse response){

    }

    /**
     * 账号激活功能
     * @param request
     * @param response
     */
    public void active(HttpServletRequest request,HttpServletResponse response){

    }

    /**
     * 登录功能
     * @param request
     * @param response
     */
    public void login(HttpServletRequest request,HttpServletResponse response){

    }

    /**
     * 退出功能
     * @param request
     * @param response
     */
    public void exit(HttpServletRequest request,HttpServletResponse response){

    }

    /**
     * 查询单个用户
     * @param request
     * @param response
     */
    public void findOne(HttpServletRequest request,HttpServletResponse response){

    }


}
