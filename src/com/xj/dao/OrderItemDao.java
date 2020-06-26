package com.xj.dao;

import com.xj.bean.Order;
import com.xj.bean.OrderItem;

import java.util.List;

public interface OrderItemDao {
    public List<OrderItem> getOrderItems(String orderId);
    
    public int saveOrderItem(OrderItem item);

    public int saveBatch(List<OrderItem> params);
}
