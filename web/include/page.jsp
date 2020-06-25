<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    $(function () {
        $("#gotopage").click(function () {
            window.location.href = "${page.url}&pn=" + $("#pn_input").val();
        });
    })
</script>

<div id="page_nav">
    <a href="${page.url}&pn=1">首页</a>
    <c:if test="${page.hasPrev}">
        <a href="${page.url}&pn=${page.pageNo-1}">上一页</a>
    </c:if>

    <c:choose>
        <%-- 当总页码小于等于5的情况 --%>
        <c:when test="${page.totalPage<=5}">

            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${page.totalPage}"></c:set>

        </c:when>
        <c:otherwise>
            <%-- 当总页码大于5的情况 --%>
            <c:choose>
                <%-- 当前页码是前三 --%>
                <c:when test="${page.pageNo<= 3}">

                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="5"></c:set>

                </c:when>
                <%-- 当前页码是后三 --%>
                <c:when test="${ page.pageNo >= page.totalPage - 2 }">

                    <c:set var="begin" value="${ page.totalPage - 4 }"></c:set>
                    <c:set var="end" value="${ page.totalPage }"></c:set>

                </c:when>
                <c:otherwise>

                    <c:set var="begin" value="${ page.pageNo - 2 }"></c:set>
                    <c:set var="end" value="${  page.pageNo + 2 }"></c:set>

                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
    <%--显示所有的页码--%>
    <c:forEach begin="${begin}" end="${end}" var="pnum">
        <c:if test="${pnum==page.pageNo}">
            <span style="color: blue">【${pnum}】</span>
        </c:if>
        <c:if test="${pnum!=page.pageNo}">
            <a href="${page.url}&pn=${pnum}">${pnum}</a>
        </c:if>
    </c:forEach>


    <c:if test="${page.hasNext}">
        <a href="${page.url}&pn=${page.pageNo+1}">下一页</a>
    </c:if>
    <a href="${page.url}&pn=${page.totalPage}">末页</a>
    共${page.totalPage}页，${page.totalCount}条记录 到第<input value="${page.pageNo}" name="pn" id="pn_input"/>页
    <input type="button" value="确定" id="gotopage"/>
</div>