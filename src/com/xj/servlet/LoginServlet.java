package com.xj.servlet;

import com.xj.bean.User;
import com.xj.service.Impl.UserServiceImpl;
import com.xj.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UserService us = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = us.login(new User(null, username, password, null));
        if(user == null){
            //返回登录页面
            req.setAttribute("msg","用户名密码错误");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            resp.sendRedirect(req.getContextPath() + "/pages/user/login_success.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
