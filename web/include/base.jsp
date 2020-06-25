<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--base标签动态获取,而不是指定的-->
<base href="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">

<link type="text/css" rel="stylesheet" href="pages/static/css/style.css" >
<script type="text/javascript" src="pages/static/js/jquery-1.7.2.js"></script>