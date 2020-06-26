package com.xj.service.Impl;

import com.xj.bean.OrderItem;
import com.xj.dao.Impl.OrderItemDaoImpl;
import com.xj.dao.OrderItemDao;
import com.xj.service.OrderItemService;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    
    private OrderItemDao oid = new OrderItemDaoImpl();
    
    @Override
    public List<OrderItem> getOrderItems(String orderId) {
        return oid.getOrderItems(orderId);
    }

    @Override
    public void saveItems(List<OrderItem> item) {
//        for(OrderItem oi : item){
//            oid.saveOrderItem(oi);
//        }
        oid.saveBatch(item);
    }
}
