package com.xj.test;

import com.xj.utils.JDBCUtils;

import java.sql.Connection;

public class JDBCUtilsTest {

    public void getConnection(){
        Connection connection = JDBCUtils.getConnection();
        System.out.println("helloworld");
        System.out.println(connection);
    }
}
