package com.xj.test;

import com.xj.bean.User;
import com.xj.dao.Impl.UserDaoImpl;
import com.xj.dao.UserDao;

public class UserDaoTest {
    public static void test(){
        UserDao ud = new UserDaoImpl();
        User user = ud.getUserByUserNameAndPassWord(new User(null, "梁宸", "654321", null));
        System.out.println(user);
    }
    public static void test2(){
        UserDao ud = new UserDaoImpl();
        boolean b = ud.registUser(new User(null, "王玮", "321654", null));
        System.out.println(b);
    }
    public static void main(String[] args) {
        test2();
    }
}
