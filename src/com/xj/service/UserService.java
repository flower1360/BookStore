package com.xj.service;

import com.xj.bean.User;

/**
 * 完成用户的登录注册
 */
public interface UserService {
    public User login(User user);
    public boolean regist(User user);
}
