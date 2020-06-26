package com.xj.dao.Impl;

import com.xj.bean.OrderItem;
import com.xj.dao.BaseDao;
import com.xj.dao.OrderItemDao;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {
    @Override
    public List<OrderItem> getOrderItems(String orderId) {
        String sql = "select id,title,count,price,total_price totalPrice,order_id orderId from bs_order_item where order_id=?";
        List<OrderItem> beanList = getBeanList(sql,orderId);
        return beanList;
    }

    @Override
    public int saveOrderItem(OrderItem item) {
        String sql = "insert into bs_order_item(title,count,price,total_price,order_id) value(?,?,?,?,?)";
        int update = update(sql,
                item.getTitle(),
                item.getCount(),
                item.getPrice(),
                item.getTotalPrice(),
                item.getOrderId()
        );
        return update;
    }

    /**
     * 批量执行sql语句
     * @param params
     * @return
     */
    @Override
    public int saveBatch(List<OrderItem> params){
        String sql = "insert into bs_order_item(title,count,price,total_price,order_id) value(?,?,?,?,?)";
        
        Object[][] objs = new Object[params.size()][5];
        int count = 0;
        for(OrderItem item : params){
            objs[count++] = new Object[]{
                    item.getTitle(),
                    item.getCount(),
                    item.getPrice(),
                    item.getTotalPrice(),
                    item.getOrderId()};
        }
        int batch = batch(sql, objs);

        return batch;
    }
}
