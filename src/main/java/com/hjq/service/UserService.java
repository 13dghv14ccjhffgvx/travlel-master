package com.hjq.service;

import com.hjq.domain.User;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-28  15:49
 * @Description: TODO
 * @Version: 1.0
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 用户激活
     * @param code 激活码
     * @return
     */
    boolean active(String code);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);
}
