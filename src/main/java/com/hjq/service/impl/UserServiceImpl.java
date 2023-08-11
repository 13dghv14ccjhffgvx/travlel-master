package com.hjq.service.impl;

import com.hjq.dao.UserDao;
import com.hjq.dao.impl.UserDaoImpl;
import com.hjq.domain.User;
import com.hjq.service.UserService;
import com.hjq.util.MailUtils;
import com.hjq.util.UuidUtil;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-28  15:49
 * @Description: 用户业务层
 * @Version: 1.0
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean register(User user) {

        //1.根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        if(u!=null){
            //用户名存在，注册失败
            return false;
        }
        //2.保存用户信息
        //2.1设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        //2.2设置激活状态
        user.setStatus("N");
        userDao.save(user);

        //3.激活邮件发送，邮件正文
        String content = "<a href='http://localhost/travel/activeUserServlet?code="+user.getCode()+"'>点击激活【黑马旅游网】</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        System.out.println("激活邮件发送成功!!");
        return true;
    }

    /**
     * 激活用户
     * @param code 激活码
     * @return
     */
    @Override
    public boolean active(String code) {
        //1.根据激活码查询用户对象
        User user = userDao.findByCode(code);
        if(user!=null){
            //2.调用dao的修改激活状态的方法
            userDao.updateStatus(user);
            return true;
        }else {
            return false;
        }
    }

    /**
     * 登录方法
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
