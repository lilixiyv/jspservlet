<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/5
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>首页</title>
    <link rel="icon" type="image/svg+xml" href="./assets/img/webicon.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="./assets/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="./assets/bootstrap-icons-1.11.2/font/bootstrap-icons.css">
<%--    导入自定义的侧边栏样式--%>
    <link rel="stylesheet" href="./assets/css/sidebar.css">
    <%-- 导入自定义的cookie判断，防止绕过正常登录流程 --%>
    <script src="assets/js/verify_cookies.js"></script>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
        }

        #content {
            flex: 1;
            margin-left: 20%; /* 相对于父元素宽度的 25% */
            padding: 2em; /* 相对于字体大小的 2em */
            overflow-x: auto;
        }



    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <!-- 侧边导航栏 -->
<%--        <%@include file="sidebar.jsp"%>--%>
        <nav id="sidebar">
            <p id="sidebar-heading">网上书店</p>
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link active" href="#">
                        首页
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        当前订单
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        历史订单
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        账号管理
                    </a>
                </li>

            </ul>

        </nav>
        <!-- 主要内容区域 -->
        <div id="content">
            <!-- 这里放置你的主要内容 -->
            <h1>Hello</h1>
            <p>world</p>
        </div>
    </div>
</div>

<%--@elvariable id="successMessage" type="com.jspservlet.servlet.RegisterServlet"--%>
<c:if test = "${not empty successMessage}">


    <script>
        alert("${successMessage}");
    </script>
</c:if>

<%--使用cookie防止用户绕过登录直接进入网页--%>
<%--<%--%>
<%--    Cookie[] cookies = request.getCookies();--%>
<%--    String account = null;--%>
<%--    if (cookies != null) {--%>
<%--        for (Cookie cookie : cookies) {--%>
<%--            if ("account".equals(cookie.getName())) {--%>
<%--                account = cookie.getValue();--%>
<%--                break;--%>
<%--            }--%>
<%--        }--%>
<%--    }--%>
<%--    if (cookies == null || account == null){--%>
<%--       request.setAttribute("errorMessage", "请先登录！");--%>
<%--       request.getRequestDispatcher("login.jsp").forward(request,response);--%>
<%--    }--%>
<%--%>--%>
</body>
</html>
