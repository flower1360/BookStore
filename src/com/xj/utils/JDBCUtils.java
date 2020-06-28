package com.xj.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取数据库连接的工具类
 *
 * @author wzg
 *
 */
public class JDBCUtils {

    private static ComboPooledDataSource dataSource = new ComboPooledDataSource(
            "book_devoloper");
    
    private static Map<Long,Connection> conns = new HashMap<>();
    
    private JDBCUtils() {
    }

    /**
     * 获取数据库连接
     *
     * @return 如果获取连接成功，返回数据的连接对象。<br/>
     *         如果获取数据库连接失败，则返回null
     */
    public static Connection getConnection() {
        long id = Thread.currentThread().getId();
        System.out.println("jdbcutils中的线程号:"+id);
        //获取当前线程的链接
        Connection connection = conns.get(id);
        if(connection == null){
            try {
                connection = dataSource.getConnection();
                //把链接保存在map中
                conns.put(id,connection);
                //connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * 释放数据库连接
     */
    public static void releaseConnection() {
        Connection connection = getConnection();
        try {
            connection.close();
            conns.remove(Thread.currentThread().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        System.out.println(getConnection());
    }
}
