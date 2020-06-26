package com.xj.servlet;

import com.xj.bean.*;
import com.xj.service.Impl.OrderItemServiceImpl;
import com.xj.service.Impl.OrderServiceImpl;
import com.xj.service.OrderItemService;
import com.xj.service.OrderService;
import com.xj.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderClientServlet extends BaseServlet {
    
    OrderService os = new OrderServiceImpl();
    OrderItemService ois = new OrderItemServiceImpl();
    
    protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //1.验证用户是否登录
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        
        if(user != null){
            //2.登录则结算
            Cart cart = WebUtils.getCart(request);
            String orderId = os.checkout(cart, user);
            session.setAttribute("orderId",orderId);
            response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
        }else {
            //3.否则返回登录界面
            request.setAttribute("msg","此操作需要登录,请先登录");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        List<Order> myOrder = os.getMyOrder(user.getId());
        
        request.setAttribute("orders",myOrder);
        
        request.getRequestDispatcher("/pages/order/order.jsp").forward(request,response);
    }

    protected void received(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        os.updateStatus(orderId, Constants.DELIVERED+"");

        String referer = request.getHeader("referer");
        response.sendRedirect(referer);
    }

    protected void showDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        List<OrderItem> orderItems = ois.getOrderItems(orderId);
        Order order = os.getMyOrder(orderId);
        request.setAttribute("orderItems",orderItems);
        request.setAttribute("order",order);
        request.getRequestDispatcher("/pages/order/orderDetails.jsp").forward(request,response);
    }
}
