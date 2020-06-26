<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%@include file="/include/base.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="pages/static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
			<%@include file="/include/user-info.jsp"%>
	</div>
	
	<div id="main">
		<c:if test="${empty orders}">
			<h1>没有对应的订单</h1>
			<h2 align="center"><a href="index.jsp">去书城</a></h2>
		</c:if>
		<c:if test="${!empty orders}">
			<table>
				<tr>
					<td>订单号</td>
					<td>日期</td>
					<td>金额</td>
					<td style="width: 160px;">状态</td>
					<td>详情</td>
				</tr>
				<c:forEach var="order" items="${orders}">
					<tr>
						<td>${order.orderId}</td>
						<td>${order.createDate}</td>
						<td>${order.totalMoney}</td>
						<td style="width: 160px;">
							<c:choose>
								<c:when test="${order.status==0}">
									未发货
								</c:when>
								<c:when test="${order.status==1}">
									已发货:<a href="OrderClientServlet?method=received&orderId=${order.orderId}" >确认收货</a>
								</c:when>
								<c:when test="${order.status==2}">
									交易完成
								</c:when>
							</c:choose>
						</td>
						<td><a href="OrderClientServlet?method=showDetails&orderId=${order.orderId}">查看详情</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		
		
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>