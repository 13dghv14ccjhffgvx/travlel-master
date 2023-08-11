package com.hjq.dao;

import com.hjq.domain.User;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-28  22:23
 * @Description: TODO
 * @Version: 1.0
 */
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 用户保存
     * @param user
     */
    public void save(User user);

    /**
     * 通过激活码查询用户
     * @param user
     * @return
     */
    User findByCode(String code);

    /**
     * 更新用户状态
     * @param user
     */
    void updateStatus(User user);

    /**
     * 查询用户名和密码
     * @param username 用户名
     * @param password 密码
     * @return
     */
    User findByUsernameAndPassword(String username,String password);
}
