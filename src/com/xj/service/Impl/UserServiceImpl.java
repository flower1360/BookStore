package com.xj.service.Impl;

import com.xj.bean.User;
import com.xj.dao.Impl.UserDaoImpl;
import com.xj.dao.UserDao;
import com.xj.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao ud = new UserDaoImpl();
    @Override
    public User login(User user) {
        return ud.getUserByUserNameAndPassWord(user);
    }

    @Override
    public boolean regist(User user) {
        return ud.registUser(user);
    }

    @Override
    public boolean checkName(User user) {
        return ud.getUserByUserName(user) == null;
    }
}
