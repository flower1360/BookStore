package com.xj.service;

import com.xj.bean.Book;
import com.xj.bean.Page;

import java.util.List;

public interface BookService {
    
    public boolean add(Book book);
    public boolean update(Book book);
    public boolean delete(Book book);
    public Book getOne(Book book);
    public List<Book> getAll();
    
    public Page<Book> getPage(String pageNo, String pageSize);
    public Page<Book> getPageByPrice(String pageNo, String pageSize, String minPrice, String maxPrice);
}
