package com.hjq.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-30  10:28
 * @Description: 退出登录状态
 * @Version: 1.0
 */
@WebServlet("/exitServlet")
public class ExitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.销毁session
        req.getSession().invalidate();
        //2.跳转到登录页面
        resp.sendRedirect(req.getContextPath()+"/login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
