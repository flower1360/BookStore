package com.xj.dao;

import com.xj.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseDao<T> {

    private QueryRunner runner = new QueryRunner();

    private Class<T> type;

    public BaseDao(){
        //获取父类的类型,父类是带参数的
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        System.out.println(superclass.getClass());
        type = (Class<T>) superclass.getActualTypeArguments()[0];
    }

    public T getBean(String sql,Object ...params){
        Connection connection = JDBCUtils.getConnection();
        T query = null;
        try {
            query = runner.query(connection, sql, new BeanHandler<>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.releaseConnection(connection);
        }
        return query;
    }
    //查询一组数据
    public List<T> getBeanList(String sql,Object ...params){
        Connection connection = JDBCUtils.getConnection();
        List<T> query = null;
        try {
            query = runner.query(connection, sql, new BeanListHandler<>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.releaseConnection(connection);
        }
        return query;
    }


    public int update(String sql, Object ...params){
        int count = 0;
        Connection connection = JDBCUtils.getConnection();
        try {
            count = runner.update(connection,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.releaseConnection(connection);
        }
        return count;
    }
    
    public Object getSingleValue(String sql, Object... params){
        Object query = null;
        Connection connection = JDBCUtils.getConnection();
        try {
            query = runner.query(connection, sql, new ScalarHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.releaseConnection(connection);
        }
        return query;
    }

    /**
     * 批处理方法
     * @return
     */
    public int batch(String sql, Object[][] params){
        Connection connection = JDBCUtils.getConnection();
        try {
            //object[][]
            //一维代表sql的执行次数,二维代表存放当前sql要执行的可变参数
            int[] batch = runner.batch(connection, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.releaseConnection(connection);
        }
        return 0;
    }
}
