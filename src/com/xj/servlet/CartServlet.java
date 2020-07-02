package com.xj.servlet;

import com.xj.bean.Book;
import com.xj.bean.Cart;
import com.xj.service.BookService;
import com.xj.service.Impl.BookServiceImpl;
import com.xj.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CartServlet extends BaseServlet {
    
    private BookService bs = new BookServiceImpl();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Book book = WebUtils.paramToBean2(request, new Book());
        //购物车的整个内容保存在session

        HttpSession session = request.getSession();
        Cart cart = WebUtils.getCart(request);

        Book one = bs.getOne(book);
        cart.addBookToCart(one);
        
        int totalCount = cart.getTotalCount();
        String title = one.getTitle();
        String s = "{\"totalCount\":"+totalCount+",\"title\":\""+title+"\"}";
        System.out.println(s);
        response.getWriter().write(s);
        //session.setAttribute("title",one.getTitle());
        
//        String referer = request.getHeader("referer");
//        response.sendRedirect(referer);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String bookId = request.getParameter("bookId");

        Cart cart = WebUtils.getCart(request);

        cart.deleteItem(bookId);
        
        String referer = request.getHeader("referer");
        response.sendRedirect(referer);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String count = request.getParameter("count");
        String id = request.getParameter("id");

        Cart cart = WebUtils.getCart(request);

        cart.updateCount(id,count);
        
        String referer = request.getHeader("referer");
        response.sendRedirect(referer);
    }

    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = WebUtils.getCart(request);
        cart.clear();

        String referer = request.getHeader("referer");
        response.sendRedirect(referer);
    }
}
