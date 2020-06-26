package com.xj.test;

import com.xj.bean.Order;
import com.xj.dao.Impl.OrderDaoImpl;
import com.xj.dao.OrderDao;

import java.util.Date;
import java.util.List;

public class OrderDaoTest {
    static OrderDao od = new OrderDaoImpl();
    static void test01(){
        Order o1 = new Order("1360",new Date(),1,105,12);
        int i = od.saveOrder(o1);
        System.out.println(i);
    }
    static void test02(){
        List<Order> orderByUserId = od.getOrderByUserId(12);
        System.out.println(orderByUserId);
    }
    static void test03(){
        Order order = od.getOrderByUserId(12).get(0);
        order.setStatus(2);
        int i = od.updateStatus(order);
    }
    static void test04(){
        List<Order> orderList = od.getOrderList();
        System.out.println(orderList);
    }
    public static void main(String[] args) {
        test04();
    }
}
