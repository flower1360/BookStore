package com.xj.service;

import com.xj.bean.Cart;
import com.xj.bean.Order;
import com.xj.bean.User;

import java.util.List;

public interface OrderService {
    //结账
    public String checkout(Cart cart, User user);
    //修改订单状态
    public void updateStatus(String orderId, String status);
    //管理员查询所有订单
    public List<Order> getAllOrder();
    //用户获取自己的订单
    public List<Order> getMyOrder(Integer userId);
    //查询某个具体订单
    public Order getMyOrder(String orderId);
}
