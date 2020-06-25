package com.xj.test;

import com.xj.bean.Book;
import com.xj.bean.Page;
import com.xj.dao.BookDao;
import com.xj.dao.Impl.BookDaoImpl;
import com.xj.service.BookService;
import com.xj.service.Impl.BookServiceImpl;

import java.util.List;

public class BookDaoTest {
    
    static BookDao bd = new BookDaoImpl();
    static BookService bs = new BookServiceImpl();
    
    public static void test01(){
        List<Book> list = bd.getAllBook();
        System.out.println(list);
    }
    
    public static void test02(){
        Book book = new Book(null,"javaxx开发","张晓健健健",50.23,0,200,null);
        System.out.println(book);
        Boolean b = bd.addBook(book);
    }
    
    public static void test03(){
        Book book = new Book();
        book.setId(4);
        Boolean b = bd.delBook(book);
    }
    public static void test04(){
        Book book = new Book(2,"c++高级","梁宸儿",60,3,50,"statuc/img/default.jpg");
        Boolean b = bd.updateBook(book);
    }
    public static void test05(){
        Book book = new Book();
        book.setId(2);
        Book b = bd.getBook(book);
        System.out.println(b);
    }
    //分页测试
    public static void test06(){
        Page<Book> page = bs.getPage("2", "4");
        System.out.println("getPageData:"+page.getPageData());
        System.out.println("getIndex:"+page.getIndex());
        System.out.println("getPageNo:"+page.getPageNo());
        System.out.println("getTotalCount:"+page.getTotalCount());
    }
    public static void main(String[] args) {
        test06();
    }
}
