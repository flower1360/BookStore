package com.xj.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class Cart implements Serializable {
    private Map<Integer,CartItem> items = new LinkedHashMap<>();
    

    public Map<Integer, CartItem> getItems() {
        return items;
    }
    
    //获取商品总数
    public int getTotalCount() {
        List<CartItem> allItems = getAllItems();
        int count = 0;
        for(CartItem cartItem:allItems){
            count += cartItem.getCount();
        }
        return count;
    }
    public double getTotalMoney() {
        List<CartItem> allItems = getAllItems();
        BigDecimal money = new BigDecimal(0.0 + "");
        for(CartItem cartItem:allItems){
            BigDecimal bigDecimal = new BigDecimal(cartItem.getTotalPrice() + "");
            money = money.add(bigDecimal);
        }
        return money.doubleValue();
    }
    
    public List<CartItem> getAllItems(){
        //返回map中所有的值
        Collection<CartItem> values = items.values();
        return new ArrayList<>(values);
    }
    
    //*************定义操作购物车的其他方法***************//
    //添加图书到购物车
    public void addBookToCart(Book book){
        //首先判断是否图书已经存在
        CartItem cartItem = items.get(book.getId());
        if(cartItem == null){
            //如果为空,说明图书不存在
            CartItem item = new CartItem();
            item.setBook(book);
            item.setCount(1);
            items.put(book.getId(),item);
        }else {
            cartItem.setCount(cartItem.getCount()+1);
            items.put(book.getId(),cartItem);
        }
    }
    //删除购物项
    public void deleteItem(String bookId){
        int i = Integer.parseInt(bookId);
        items.remove(i);
    }
    //修改某一项的数量
    public void updateCount(String bookId, String count){

        int m_bookId = -1;
        try {
            m_bookId = Integer.parseInt(bookId);
        } catch (NumberFormatException e) {
            System.out.println("updateCount:m_bookId:异常"+bookId);
        }

        int m_count = 1;
        try {
            m_count = Integer.parseInt(count);
        } catch (NumberFormatException e) {
            System.out.println("updateCount:m_count:异常"+bookId);
        }
        CartItem cartItem = items.get(m_bookId);
        cartItem.setCount(m_count);
    }
    //清空购物车
    public void clear(){
        items.clear();
    }
    
    
    
    public Cart() {
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
}
