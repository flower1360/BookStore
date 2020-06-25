package com.xj.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {
    //表示购买的哪本书
    private Book book;
    //买了多少本
    private int count;
    //当前项的总金额
    private double totalPrice;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotalPrice() {
        BigDecimal bigDecimal = new BigDecimal(book.getPrice() + "");
        BigDecimal bigDecimal1 = new BigDecimal(getCount() + "");
        return bigDecimal.multiply(bigDecimal1).doubleValue();
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartItem() {
    }

    public CartItem(Book book, int count, double totalPrice) {
        this.book = book;
        this.count = count;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
