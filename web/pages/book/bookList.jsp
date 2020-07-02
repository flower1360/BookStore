<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%@include file="/include/base.jsp" %>
<script type="text/javascript">
    $(function () {
        $(".addBook").click(function () {
            var id = $(this).attr("bookId");
            $.getJSON("CartServlet?method=add&id="+id,function (data) {
                $("#count").text("您的购物车中有"+data.totalCount+"件商品");
                $("#msg").text("您刚刚将"+data.title+"加入到了购物车中");
            });
            return false;
        });
    });
</script>
</head>
<body>
<div id="header">
    <!--dwa-->
    <img class="logo_img" alt="" src="pages/static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <%@include file="/include/user-info.jsp" %>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/BookClientServlet" method="get">
                <input type="hidden" name="method" value="pageByPrice" >
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>
        <div style="text-align: center">
            <span id="count">
                您的购物车中有${cart.totalCount}件商品
                <%--<c:out value="${cart.totalCount}" default="0"></c:out>--%>
            </span>
            <div><span id="msg" style="color: #39987c">&nbsp;</span></div>
        </div>
        <c:forEach var="book" items="${page.pageData}">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.imgPath}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.title}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <a class="addBook" href="" style="color: blue" bookId="${book.id}">加入购物车</a>
                    </div>
                </div>
            </div>
        </c:forEach>
        
    </div>
    <%@include file="/include/page.jsp"%>

</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>