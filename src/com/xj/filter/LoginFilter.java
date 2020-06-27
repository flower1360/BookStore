package com.xj.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author : zhangxiaojian
 * @date : ${DATA}
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpSession session = req.getSession();
        if(session.getAttribute("user") != null){
            //已登录
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            req.setAttribute("msg","此操作需要登录,请先登录");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
