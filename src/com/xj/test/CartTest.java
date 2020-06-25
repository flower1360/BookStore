package com.xj.test;

import com.xj.bean.Book;
import com.xj.bean.Cart;
import com.xj.bean.CartItem;

import java.util.List;

public class CartTest {

    static Book book1 = new Book(1,"java使用1","",11.1,11,111,"");
    static Book book2 = new Book(2,"java使用2","",12.2,22,222,"");
    static Book book3 = new Book(3,"java使用3","",13.3,33,333,"");
    static Book book4 = new Book(4,"java使用4","",14.4,44,444,"");
    static Book book5 = new Book(5,"java使用5","",15.5,55,555,"");
    
    public static  void test01(){
        Cart cart = new Cart();
        cart.addBookToCart(book1);
        System.out.println("总计"+cart.getTotalCount()+"本书");
        System.out.println("总价格"+cart.getTotalMoney()+"元");
        System.out.println("当前项目:"+cart.getAllItems());
        cart.addBookToCart(book2);
        System.out.println("总计"+cart.getTotalCount()+"本书");
        System.out.println("总价格"+cart.getTotalMoney()+"元");
        System.out.println("当前项目:"+cart.getAllItems());
        cart.addBookToCart(book1);
        System.out.println("总计"+cart.getTotalCount()+"本书");
        System.out.println("总价格"+cart.getTotalMoney()+"元");
        System.out.println("当前项目:"+cart.getAllItems());

        System.out.println("删除之后");
        cart.deleteItem(book1.getId().toString());
        System.out.println("总计"+cart.getTotalCount()+"本书");
        System.out.println("总价格"+cart.getTotalMoney()+"元");
        System.out.println("当前项目:"+cart.getAllItems());
        
        System.out.println("修改之后");
        cart.updateCount(book2.getId().toString(),"4");
        System.out.println("总计"+cart.getTotalCount()+"本书");
        System.out.println("总价格"+cart.getTotalMoney()+"元");
        System.out.println("当前项目:"+cart.getAllItems());
    }

    public static void main(String[] args) {
        test01();
    }
}
