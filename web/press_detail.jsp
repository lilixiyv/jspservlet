<%--@elvariable id="press" type="com.jspservlet.entity.PublishHouse"--%>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/10
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>出版社详情</title>
    <link rel="icon" type="image/svg+xml" href="assets/img/web_icon.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="./assets/js/bootstrap.bundle.min.js"></script>
<%--    <link rel="stylesheet" href="./assets/bootstrap-icons-1.11.2/font/bootstrap-icons.css">--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
</head>
<body class="bg-light d-flex align-items-center">

<div class="container">
    <div class="row justify-content-center">
        <div class="card col-md-8">

            <%--书名--%>
            <%--            <div class="card-header">  </div>--%>
            <div class="card-body">
                <h3 class="card-title text-center">${press.name}</h3>
                <form>

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info text-white col-2">地址</span>
                        <label for="comment_num"></label><input type="text" class="form-control" id="comment_num" value="${press.location}" disabled>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info text-white col-2">出版书目数</span>
                        <label for="pos_rate"></label><input type="text" class="form-control" id="pos_rate" value="${press.totalPublish}" disabled>
                    </div>

                </form>

                <div class="row mt-2 justify-content-center">

                    <div class="col-md-6">
                        <button type="button" class="btn btn-outline-secondary w-100" onclick="window.history.back()">返回</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<%--@elvariable id="errorMessage" type="com.jspservlet.servlet.LoginServlet"--%>
<c:if test = "${not empty errorMessage}">
    <script>
        alert("${errorMessage}");
        window.location.href="home.jsp";
    </script>
</c:if>
</body>

</html>

