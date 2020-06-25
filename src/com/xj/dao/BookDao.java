package com.xj.dao;

import com.xj.bean.Book;

import java.util.List;

public interface BookDao {
    public List<Book> getAllBook();
    boolean addBook(Book book);
    boolean delBook(Book book);
    boolean updateBook(Book book);
    Book getBook(Book book);

    /**
     * 分页查找图书的方法
     * @param index
     * @param size
     * @return
     */
    public List<Book> getPageList(int index, int size);

    /**
     * 按-价格-分页查找图书
     * @param index
     * @param size
     * @param minPrice
     * @param maxPrice
     * @return
     */
    public List<Book> getPageByPrice(int index, int size, double minPrice, double maxPrice);

    /**
     * 获取所有图书的总记录数
     * @return
     */
    public int getTotalCount();

    /**
     * 按-价格-分页获取图书的总记录数
     * @return
     */
    public int getTotalCountByPrice(double minPrice, double maxPrice);
}
