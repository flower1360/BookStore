package com.xj.servlet;

import com.xj.bean.Book;
import com.xj.bean.Page;
import com.xj.service.BookService;
import com.xj.service.Impl.BookServiceImpl;
import com.xj.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookManagerServlet extends BaseServlet {
    
    private BookService bs = new BookServiceImpl();

    //只显示分页数据
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pn = req.getParameter("pn");
        Page<Book> page = bs.getPage(pn, "4");
        page.setUrl("manager/BookManagerServlet?method=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

    }

    //显示全部数据 已弃用
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        List<Book> list = bs.getAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //将提交的图书信息封装成对象
        Book book = WebUtils.paramToBean2(req, new Book());
        boolean b = bs.add(book);
        if(b){
            resp.sendRedirect(req.getContextPath() + "/manager/BookManagerServlet?method=page");
        }else {
            System.out.println("BookManagerServlet_add_error");
        }
    }
    
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //referer代表我从哪里来
        String url = req.getHeader("Referer");
        Book book = WebUtils.paramToBean2(req, new Book());
        boolean b = bs.delete(book);
        if(b){
//            resp.sendRedirect(req.getContextPath() + "/manager/BookManagerServlet?method=page");
            resp.sendRedirect(url);
        }else {
            System.out.println("BookManagerServlet_delete_error");
        }
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.paramToBean2(req, new Book());
        Book one = bs.getOne(book);
        req.setAttribute("book",one);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

        String pn = req.getParameter("pn");
        Book book = WebUtils.paramToBean2(req, new Book());
        //由于添加和修改操作封装出的book的id有差别,添加的id是0,修改的是当前id,所以根据id判断
        boolean b;
        if(book.getId() == 0){  //--添加
            b = bs.add(book);
        }else {                 //--修改
            b = bs.update(book);
        }
        if(b){
            resp.sendRedirect(req.getContextPath() + "/manager/BookManagerServlet?method=page&pn="+pn);
        }else {
            System.out.println("BookManagerServlet_update_error");
        }
    }
}
