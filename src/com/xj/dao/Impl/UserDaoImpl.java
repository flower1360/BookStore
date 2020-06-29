package com.xj.dao.Impl;

import com.xj.bean.User;
import com.xj.dao.BaseDao;
import com.xj.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override
    public User getUserByUserNameAndPassWord(User user) {
        String sql = "select * from bs_user where username=? and password=?";
        return getBean(sql, user.getUsername(), user.getPassword());
    }

    @Override
    public boolean registUser(User user) {
        String sql = "insert into bs_user(username,password,email) values(?,?,?)";
        int update = this.update(sql, user.getUsername(), user.getPassword(), user.getEmail());
        return update > 0;
    }

    @Override
    public User getUserByUserName(User user) {
        String sql = "select * from bs_user where username=?";
        return this.getBean(sql, user.getUsername());
    }
}
