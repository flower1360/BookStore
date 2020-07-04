<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@include file="/include/base.jsp"%>
	<script type="text/javascript">
		$(function () {
			$(".delBtn").click(function () {
				var text = $(this).parents("tr").children(":first").text();
				if(!confirm("确认删除["+text+"]")){
					return false;
				}
			});
			$(".changeInput").change(function () {
				var count = $(this).val();
				var id = $(this).attr("updateId");//获取要修改的id
				$.getJSON("CartServlet?method=update&count="+count+"&id="+id,function (data) {
					$("#price_"+id).text(data.totalPrice);	
					$("#count").text(data.totalCount);	
					$("#money").text(data.totalMoney);	
				});
				return false;
				// location.href = "CartServlet?method=update&count="+count+"&id="+id;
			});
			$(".clearBtn").click(function () {
				return confirm("确认清空购物车?");
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="pages/static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@include file="/include/user-info.jsp"%>
	</div>
	
	<div id="main">
		<c:if test="${empty cart.allItems}">
			<div style="width: 200px; height: 100px; margin: auto;margin-top: 200px;text-align: center">
				<h1>购物车当前为空</h1>
				<h2><a href="index.jsp">书店</a></h2>
			</div>
		</c:if>
		<c:if test="${!empty cart.allItems}">
			<table>
				<tr>
					<td>商品名称</td>
					<td>数量</td>
					<td>单价</td>
					<td>金额</td>
					<td>操作</td>
				</tr>
				<c:forEach var="item" items="${cart.allItems}">
					<tr>
						<td>${item.book.title}</td>
						<td>
							<input updateId="${item.book.id}" class="changeInput" type="text" style="width: 40px" value="${item.count}">
						</td>
						<td>${item.book.price}</td>
						<td id="price_${item.book.id}">${item.totalPrice}</td>
						<td><a class="delBtn" href="CartServlet?method=delete&bookId=${item.book.id}">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span id="count" class="b_count">${cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span id="money" class="b_price">${cart.totalMoney}</span>元</span>
				<span class="cart_span"><a class="clearBtn" href="CartServlet?method=clear">清空购物车</a></span>
				<span class="cart_span"><a href="OrderClientServlet?method=checkout">去结账</a></span>
			</div>
		</c:if>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>