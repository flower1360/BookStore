package com.xj.servlet;

import com.google.code.kaptcha.Constants;
import com.xj.bean.User;
import com.xj.service.Impl.UserServiceImpl;
import com.xj.service.UserService;
import com.xj.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 处理所有和用户相关的请求
 */
public class UserServlet extends BaseServlet {
    
    private UserService us = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        User user1 = WebUtils.paramToBean2(req, new User());

        User user = us.login(user1);
        
        
        if(user == null){
            //返回登录页面
            req.setAttribute("msg","用户名密码错误");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            resp.sendRedirect(req.getContextPath() + "/pages/user/login_success.jsp");
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        /*String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");*/
        User user = WebUtils.paramToBean(req, new User());

        //获取session中
        HttpSession session = req.getSession();
        String sessionCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //获取用户输入
        String userCode = req.getParameter("code");

        System.out.println("session中：" + sessionCode);
        System.out.println("user输入：" + userCode);

        if(!sessionCode.equals(userCode)){
            req.setAttribute("msg","验证码错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            return ;
        }
        boolean b = us.regist(user);
        if(b){
            resp.sendRedirect(req.getContextPath() + "/pages/user/regist_success.jsp");
        }else {
            req.setAttribute("msg","用户名已存在");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

    protected void checkName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.paramToBean2(request, new User());
        if(us.checkName(user)){
            response.getWriter().write("该用户名可以注册");
        }else {
            response.getWriter().write("该用户名已被占用");
        }
    }

    /**
     * 用户登出操作
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //销毁session
        session.invalidate();
        //返回首页
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }
}
