package com.xj.servlet;

import com.xj.bean.Constants;
import com.xj.bean.Order;
import com.xj.service.Impl.OrderServiceImpl;
import com.xj.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderManagerServlet extends BaseServlet  {
    
    private OrderService os = new OrderServiceImpl();
    
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Order> allOrder = os.getAllOrder();
        request.setAttribute("allOrder",allOrder);

        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request,response);
    }

    protected void deliver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        os.updateStatus(orderId, Constants.DELIVERING+"");
        
        String referer = request.getHeader("referer");
        response.sendRedirect(referer);
    }
}
