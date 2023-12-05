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

        #content {
            margin-left: 25%; /* 相对于父元素宽度的 25% */
            padding: 2em; /* 相对于字体大小的 2em */
        }

        #sidebar-heading {
            font-size: 1.2rem;
            color: #fff;
            padding-left: 5%; /* 相对于父元素宽度的 5% */
        }

    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
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
        <main id="content">
            <!-- 这里放置你的主要内容 -->
            <h1>欢迎使用 New Chat</h1>
            <p>这是一个演示 New Chat 侧边栏的页面。</p>
        </main>
    </div>
</div>

</body>
</html>
