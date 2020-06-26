package com.xj.dao.Impl;

import com.xj.bean.Order;
import com.xj.dao.BaseDao;
import com.xj.dao.OrderDao;

import java.util.Date;
import java.util.List;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into bs_order(order_id,create_date,total_money,status,user_id) value(?,?,?,?,?)";
        int update = update(sql, 
                order.getOrderId(), 
                new Date(), 
                order.getTotalMoney(), 
                order.getStatus(), 
                order.getUserId());
        return update;
    }

    @Override
    public int updateStatus(Order order) {
        String sql = "update bs_order set status=? where order_id=?";
        int update = update(sql, 
                order.getStatus(), 
                order.getOrderId());
        return update;
    }

    @Override
    public List<Order> getOrderList() {
        String sql = "select order_id orderId,create_date createDate,total_money totalMoney,status,user_id userId from bs_order";
        List<Order> beanList = getBeanList(sql);
        return beanList;
    }

    @Override
    public List<Order> getOrderByUserId(Integer userId) {
        String sql = "select order_id orderId,create_date createDate,total_money totalMoney,status,user_id userId from bs_order where user_id=?";
        List<Order> beanList = getBeanList(sql, userId);
        return beanList;
    }

    @Override
    public Order getOrderByOrderId(String orderId) {
        String sql = "select order_id orderId,create_date createDate,total_money totalMoney,status,user_id userId from bs_order where order_id=?";
        Order bean = getBean(sql, orderId);
        return bean;
    }
}
