package com.xj.filter;

import com.xj.utils.JDBCUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * TrasactionFilter类用于管理事务
 */
public class TransactionFilter implements Filter {

	public TransactionFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Connection connection = JDBCUtils.getConnection();
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
		}
		
		try {
			// 放行操作
			chain.doFilter(request, response);
			// 没有异常，并提交事务
			connection.commit();
		} catch (Exception e) {
			System.out.println("filter收到了异常:"+e.getMessage());
			// 回滚 事务
			try {
				JDBCUtils.getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			HttpServletRequest req = (HttpServletRequest)request;
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.sendRedirect(req.getContextPath()+"/pages/exception.jsp");
		}
		JDBCUtils.releaseConnection();
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
