package com.xj.dao.Impl;

import com.xj.bean.Book;
import com.xj.dao.BaseDao;
import com.xj.dao.BookDao;

import java.util.List;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    
    @Override
    public List<Book> getAllBook() {
        String sql = "select id,title,author,price,sales,stock,img_path imgpath from bs_book";
        return getBeanList(sql);
    }

    @Override
    public boolean addBook(Book book) {
        String sql = "insert into bs_book(title,author,price,sales,stock,img_path) value(?,?,?,?,?,?)";
        int update = update(sql,book.getTitle()
                                ,book.getAuthor()
                                ,book.getPrice()
                                ,book.getSales()
                                ,book.getStock()
                                ,book.getImgPath());
        
        return update > 0;
    }

    @Override
    public boolean delBook(Book book) {
        String sql = "delete from bs_book where id=?";
        int update = update(sql,book.getId());
        return update > 0;
    }

    @Override
    public boolean updateBook(Book book) {
        String sql = "update bs_book set title=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
        int update = update(sql,book.getTitle()
                                ,book.getAuthor()
                                ,book.getPrice()
                                ,book.getSales()
                                ,book.getStock()
                                ,book.getImgPath()
                                ,book.getId());
        return update > 0;
    }

    @Override
    public Book getBook(Book book) {
        String sql = "select id,title,author,price,sales,stock,img_path imgpath from bs_book where id=?";
        return getBean(sql,book.getId());
    }

    @Override
    public List<Book> getPageList(int index, int size) {
        String sql = "select id,title,author,price,sales,stock,img_path imgpath " +
                "from bs_book limit ?,?";
        return getBeanList(sql,index,size);    
    }
    //根据图书价格查询
    @Override
    public List<Book> getPageByPrice(int index, int size,double minPrice,double maxPrice) {
        String sql = "select id,title,author,price,sales,stock,img_path imgpath " +
                "from bs_book where price between ? and ? limit ?,?";
        return getBeanList(sql,minPrice,maxPrice,index,size);
    }
    
    
    @Override
    public int getTotalCount() {
        String sql = "select count(*) from bs_book";
        Object singleValue = getSingleValue(sql);
        int parseint = 0;
        try {
            parseint = Integer.parseInt(singleValue.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return parseint;
    }

    @Override
    public int getTotalCountByPrice(double minPrice, double maxPrice) {
        String sql = "select count(*) from bs_book where price between ? and ?";
        Object singleValue = getSingleValue(sql,minPrice,maxPrice);
        int parseint = 0;
        try {
            parseint = Integer.parseInt(singleValue.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return parseint;
    }
}
