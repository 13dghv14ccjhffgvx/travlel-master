package com.hjq.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjq.domain.ResultInfo;
import com.hjq.domain.User;
import com.hjq.service.UserService;
import com.hjq.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-28  15:48
 * @Description: TODO
 * @Version: 1.0
 */
@WebServlet("/registerUserServlet")
public class RegisterUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //验证码校验
        String check = req.getParameter("check");
        System.out.println(check);
        //从session中获取生成的验证码
        HttpSession session = req.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        System.out.println(checkcode_server);
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        //校验验证码
        if(checkcode_server==null||!checkcode_server.equalsIgnoreCase(check)){//验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            System.out.println("验证码校验");
            //将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return;
        }

        //1.获取数据
        Map<String, String[]> map = req.getParameterMap();
        System.out.println(map);

        Iterator<Map.Entry<String,String[]>> entries = map.entrySet().iterator();
        while(entries.hasNext()){
            Map.Entry<String,String[]> entry = entries.next();
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            System.out.println(key+": "+value);
        }

        //2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
            System.out.println("=====================");
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            System.out.println(user.getName());
            System.out.println(user.getBirthday());
            System.out.println(user.getEmail());
            System.out.println(user.getTelephone());
            System.out.println(user.getSex());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //3.调用service完成注册
        UserService service = new UserServiceImpl();
        boolean flag = service.register(user);
        ResultInfo info = new ResultInfo();
        System.out.println(flag);
        //4.响应结果
        if(flag){//注册成功
            info.setFlag(true);
        }else{//注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败！");
            System.out.println("注册失败");
        }

        //将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //将json数据写回客户端
        //设置content-type
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
