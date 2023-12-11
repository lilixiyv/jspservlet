<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/9
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>当前订单</title>
    <link rel="icon" type="image/svg+xml" href="assets/img/web_icon.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="./assets/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="./assets/bootstrap-icons-1.11.2/font/bootstrap-icons.css">

    <%-- 导入自定义的主体样式 --%>
    <link rel="stylesheet" href="assets/self_css/self_content.css">
    <%-- 导入自定义的cookie判断，防止绕过正常登录流程 --%>
    <script src="assets/self_js/verify_cookies.js"></script>
    <%-- 导入自定义的表格样式 --%>
    <link rel="stylesheet" href="assets/self_css/self_table.css">

</head>
<body>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <!-- 侧边导航栏 -->
        <%@include file="sidebar.jsp"%>

        <!-- 主要内容区域 -->
        <div id="content">
            <h1>当前订单</h1>

            <div style="height: 80vh; overflow: auto;">
                <table class="table table-light table-striped table-hover">
                    <thead>
                    <tr class="table-dark">
                        <th>书名</th>
                        <th>出版时间</th>
                        <th>作者</th>
                        <th>出版社</th>
                        <th>分类</th>
                        <th>好评率</th>
                        <th>评论数</th>
                        <th>价格</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- 使用 JSTL 遍历书籍列表，并生成表格行 -->
                    <%--                    <c:forEach var="book" items="${bookList}">--%>
                    <%--                        <tr>--%>
                    <%--                            <!-- book.getName() 可以根据实际的 Book 类属性来调整 -->--%>
                    <%--                            <td><a href="bookDetail.jsp?bookId=${book.id}">${book.name}</a></td>--%>
                    <%--                            <td>${book.authorName}</td>--%>
                    <%--                            <td>${book.pressName}</td>--%>
                    <%--                            <td>${book.categoryName}</td>--%>
                    <%--                            <td>${book.price}</td>--%>
                    <%--                        </tr>--%>
                    <%--                    </c:forEach>--%>
                    <%for (int i = 1; i <=5; i++) {%>
                    <tr>
                        <%--跳转页面待改变--%>
                        <td><a class="custom-link" href="book_detail.jsp?isbn=test"> test </a></td>
                        <td> test </td>
                        <td> <a class="custom-link" href="author_detail.jsp?author_name=test"> test </a> </td>
                        <td> <a class="custom-link" href="press_detail.jsp?author_name=test"> test </a> </td>
                        <td> test </td>
                        <td> test </td>
                        <td> test </td>
                        <td> test </td>
                        <td> <a class="custom-link" href="press_detail.jsp?author_name=test">移除</a></td>
                    </tr>
                    <%}%>
                    </tbody>
                </table>
            </div>
            <div class="row mt-3 justify-content-end">
                <div class="row col-2">
                    总金额：
                </div>
                <div class="row col-2 justify-content-end">
                    <button class="btn btn-warning">
                        购买
                    </button>
                </div>
            </div>



        </div>
    </div>
</div>

<%--@elvariable id="successMessage" type="com.jspservlet.servlet.RegisterServlet"--%>
<c:if test = "${not empty successMessage}">
    <script>
        alert("${successMessage}");
    </script>
</c:if>
</body>
</html>
