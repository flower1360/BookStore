package com.xj.dao;

import com.xj.bean.Order;

import java.util.List;

public interface OrderDao {
    
    public int saveOrder(Order order);
    
    public int updateStatus(Order order);
    
    public List<Order> getOrderList();
    
    public List<Order> getOrderByUserId(Integer userId);
    public Order getOrderByOrderId(String orderId);
}
