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
    <title>历史订单</title>
    <link rel="icon" type="image/svg+xml" href="assets/img/web_icon.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="./assets/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="./assets/bootstrap-icons-1.11.2/font/bootstrap-icons.css">

    <%-- 导入自定义的主体样式 --%>
    <link rel="stylesheet" href="assets/self_css/self_content.css">
    <%-- 导入自定义的cookie判断，防止绕过正常登录流程 --%>
<%--    <script src="assets/self_js/verify_cookies.js"></script>--%>
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
            <h1>历史订单</h1>

            <div style="height: 80vh; overflow: auto;">
                <table class="table table-light table-striped table-hover">
                    <thead>
                    <tr class="table-dark">
                        <th>订单号</th>
                        <th>更新时间</th>
                        <th>收货地</th>
                        <th>费用</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- 使用 JSTL 遍历书籍列表，并生成表格行 -->
                        <%--@elvariable id="order_list" type="java.util.List"--%>
                        <c:forEach var="order" items="${order_list}">
                            <tr>
                                <!-- book.getName() 可以根据实际的 Book 类属性来调整 -->
                                <td>${order.orderNumber}</td>
                                <td>${order.updateTime}</td>
                                <td>${order.address}</td>
                                <td>${order.price}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
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
