package com.xj.service;

import com.xj.bean.OrderItem;

import java.util.List;

public interface OrderItemService {
    //根据订单号查询订单所有项
    public List<OrderItem> getOrderItems(String orderId);
    //保存所有订单项
    public void saveItems(List<OrderItem> item);
}
