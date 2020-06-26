package com.xj.test;

import com.xj.bean.OrderItem;
import com.xj.dao.Impl.OrderItemDaoImpl;
import com.xj.dao.OrderItemDao;

import java.util.List;

public class OrderItemDaoTest {
    static OrderItemDao oid = new OrderItemDaoImpl();
    static void test01(){
        OrderItem oi1 = new OrderItem("mingzi",10,10.5,105,"1360");
        oid.saveOrderItem(oi1);
    }
    
    static void test02(){
        List<OrderItem> orderItems = oid.getOrderItems("1360");
        System.out.println(orderItems);
    }
    
    public static void main(String[] args) {
        test01();
        test02();
    }
}
