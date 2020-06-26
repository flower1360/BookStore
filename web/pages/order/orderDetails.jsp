<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <%@include file="/include/base.jsp" %>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="pages/static/img/logo.gif">
    <span class="wel_word">订单详情</span>
    <%@include file="/include/user-info.jsp" %>
</div>

<div id="main">
    <h1 style="margin-top: 50px" align="center">订单号:${order.orderId}|总计:${order.totalMoney}元</h1>
    <table>
        <tr>
            <td>书名</td>
            <td>数量</td>
            <td>单价</td>
            <td>总价</td>
        </tr>
        <c:forEach var="item" items="${orderItems}">
            <tr>
                <td>${item.title}</td>
                <td>${item.count}</td>
                <td>${item.price}</td>
                <td>${item.totalPrice}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>