package com.xj.servlet;

import com.xj.bean.Book;
import com.xj.bean.Page;
import com.xj.service.BookService;
import com.xj.service.Impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookClientServlet extends BaseServlet {

    private BookService bs = new BookServiceImpl();
    
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pn = req.getParameter("pn");
        Page<Book> page = bs.getPage(pn, "4");
        page.setUrl("client/BookClientServlet?method=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/book/bookList.jsp").forward(req,resp);
        
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String min = req.getParameter("min");
        String max = req.getParameter("max");
        String pn = req.getParameter("pn");
        //查出价格区间的所有图书
        Page<Book> page = bs.getPageByPrice(pn, "4", min, max);
        page.setUrl("client/BookClientServlet?method=pageByPrice&min="+min+"&max="+max);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/book/bookList.jsp").forward(req,resp);
    }
}
