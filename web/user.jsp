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
    <title>用户界面</title>
    <link rel="icon" type="image/svg+xml" href="./assets/img/webicon.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="./assets/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="./assets/bootstrap-icons-1.11.2/font/bootstrap-icons.css">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
        }

        #sidebar {
            position: fixed;
            width: 20%; /* 相对于视口宽度的 25% */
            height: 100%;
            background-color: #343a40;
            padding-top: 5%; /* 相对于父元素高度的 5% */
        }

        #sidebar .nav-link {
            color: #fff;
            padding: 1em; /* 相对于字体大小的 1em */
        }

        #sidebar .nav-link:hover {
            background-color: #495057; /* 鼠标悬停时的背景色 */
        }

        #content {
            flex: 1;
            margin-left: 20%; /* 相对于父元素宽度的 25% */
            padding: 2em; /* 相对于字体大小的 2em */
            overflow-x: auto;
        }

        #sidebar-heading {
            font-size: 1.2rem;
            color: #fff;
            padding-left: 5%; /* 相对于父元素宽度的 5% */
        }

        /*@media (max-width: 768px) {*/
        /*    !* 在较小屏幕宽度下，减小侧边栏宽度 *!*/
        /*    #sidebar {*/
        /*        width: 30%; !* 相对于视口宽度的 30% *!*/
        /*    }*/

        /*    #content {*/
        /*        margin-left: 30%; !* 相对于父元素宽度的 30% *!*/
        /*    }*/
        /*}*/

    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <!-- 侧边导航栏 -->
        <nav id="sidebar">
            <p id="sidebar-heading">New Chat</p>
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link active" href="#">
                        Chat 1
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        Chat 2
                    </a>
                </li>
                <!-- 可以根据需要添加更多聊天室链接 -->
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
<%
    Cookie[] cookies = request.getCookies();
    String account = null;
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("account".equals(cookie.getName())) {
                account = cookie.getValue();
                break;
            }
        }
    }
    if (cookies == null || account == null){
       request.setAttribute("errorMessage", "请先登录");
       request.getRequestDispatcher("login.jsp").forward(request,response);
    }
%>
</body>
</html>
