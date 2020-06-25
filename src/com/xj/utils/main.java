package com.xj.utils;

import java.sql.Connection;

public class main {
    public static void main(String[] args) {
        Connection connection = JDBCUtils.getConnection();
        System.out.println("helloworld");
        System.out.println(connection);
    }
}
