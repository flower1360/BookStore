package com.xj.servlet;

import com.xj.bean.User;
import com.xj.service.Impl.UserServiceImpl;
import com.xj.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    private UserService us = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        boolean b = us.regist(new User(null, username, password, email));
        if(b){
            resp.sendRedirect(req.getContextPath() + "/pages/user/regist_success.jsp");
        }else {
            req.setAttribute("msg","用户名已存在");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
