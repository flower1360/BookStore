package com.xj.service.Impl;

import com.xj.bean.*;
import com.xj.dao.Impl.OrderDaoImpl;
import com.xj.dao.OrderDao;
import com.xj.service.BookService;
import com.xj.service.OrderItemService;
import com.xj.service.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    
    private OrderDao od = new OrderDaoImpl();
    private OrderItemService ois = new OrderItemServiceImpl();
    private BookService bs = new BookServiceImpl();
    
    @Override
    public String checkout(Cart cart, User user) {
        //结账操作:把购物车里的数据封装并保存
        //1.封装订单对象
        
        long l = System.currentTimeMillis();
        String orderId = l+""+user.getId();//生成订单号
        
        Order order = new Order();
        order.setCreateDate(new Date());
        order.setOrderId(orderId);
        order.setTotalMoney(cart.getTotalMoney());
        order.setStatus(0);
        order.setUserId(user.getId());
        
        //2.封装订单项对象
        List<CartItem> allItems = cart.getAllItems();
        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItem cartItem: allItems){
            //封装一个订单项
            OrderItem oi = new OrderItem(
                    cartItem.getBook().getTitle(),
                    cartItem.getCount(),
                    cartItem.getBook().getPrice(),
                    cartItem.getTotalPrice(),
                    orderId);
            orderItems.add(oi);
        }
        //3.保存订单
        od.saveOrder(order);
        //4.保存所有订单项
        ois.saveItems(orderItems);
        //5.修改库存
        for(CartItem cartItem: allItems){
            //获取详细信息:图书的信息应该从数据库得到
            Book book = cartItem.getBook();
            Book one = bs.getOne(book);
            //修改库存和销量--(应修改实时的库存,而不是保存在购物车中的)
            int count = cartItem.getCount();
            one.setStock(one.getStock()-count);
            one.setSales(one.getSales()+count);
            bs.update(one);
        }
        //6.保存后应清空购物车
        cart.clear();
        return orderId;
    }

    @Override
    public void updateStatus(String orderId, String status) {

        int i = 0;
        try {
            i = Integer.parseInt(status);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Order o = new Order();
        o.setOrderId(orderId);
        o.setStatus(i);
        
        od.updateStatus(o);
        
    }

    @Override
    public List<Order> getAllOrder() {
        return od.getOrderList();
    }

    @Override
    public List<Order> getMyOrder(Integer userId) {
        return od.getOrderByUserId(userId);
    }

    @Override
    public Order getMyOrder(String orderId) {
        return od.getOrderByOrderId(orderId);
    }
}
