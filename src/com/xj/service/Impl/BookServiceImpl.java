package com.xj.service.Impl;

import com.xj.bean.Book;
import com.xj.bean.Page;
import com.xj.dao.BookDao;
import com.xj.dao.Impl.BookDaoImpl;
import com.xj.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bd = new BookDaoImpl();
    
    @Override
    public boolean add(Book book) {
        bd.addBook(book);
        return bd.addBook(book);
    }

    @Override
    public boolean update(Book book) {
        return bd.updateBook(book);
    }

    @Override
    public boolean delete(Book book) {
        return bd.delBook(book);
    }

    @Override
    public Book getOne(Book book) {
        return bd.getBook(book);
    }

    @Override
    public List<Book> getAll() {
        return bd.getAllBook();
    }

    @Override
    public Page<Book> getPage(String pageNo, String pageSize) {
        //将用户传入的数据先封装
        Page<Book> page = new Page<>();
        //设置默认值
        int pn = 1;
        int ps = page.getPageSize();
        try {
            pn = Integer.parseInt(pageNo);
            ps = Integer.parseInt(pageSize);
        } catch (NumberFormatException e) {
            System.out.println("getPage:类型转换异常");
        }
        //先要设置页面大小
        page.setPageSize(ps);
        
        //在设置总记录数
        int totalCount = bd.getTotalCount();
        page.setTotalCount(totalCount);
        
        //这样可以算出总页面getTotalPage
        
        page.setPageNo(pn);
        
        //3.查询数据并封装
        List<Book> pageList = bd.getPageList(page.getIndex(), page.getPageSize());
        page.setPageData(pageList);
        return page;
    }
    @Override
    public Page<Book> getPageByPrice(String pageNo, String pageSize, String minPrice, String maxPrice) {
        double min = 0.0;
        double max = Double.MAX_VALUE;
        try {
            min = Double.parseDouble(minPrice);
            max = Double.parseDouble(maxPrice);
        } catch (Exception e) {
            System.out.println("getPageByPrice:类型转换异常");
        }
        //1.将用户传入的数据先封装
        Page<Book> page = new Page<>();
        //设置默认值
        int pn = 1;
        int ps = page.getPageSize();
        try {
            pn = Integer.parseInt(pageNo);
            ps = Integer.parseInt(pageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        //2.将页面及页面大小设置进page对象
        //3.按照价格区间获得总记录数
        int count = bd.getTotalCountByPrice(min, max);
        page.setTotalCount(count);
        page.setPageSize(ps);
        //最后设置
        page.setPageNo(pn);

        //4.查询相应的数据
        List<Book> list = bd.getPageByPrice(page.getIndex(), page.getPageSize(), min, max);
        page.setPageData(list);
        return page;
    }
}
