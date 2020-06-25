package com.xj.dao;

import com.xj.bean.User;

public interface UserDao {
    //按照用户名和密码查询详细信息
    public User getUserByUserNameAndPassWord(User user);

    //注册,保存用户
    public boolean registUser(User user);
}
