<%--@elvariable id="author" type="com.jspservlet.entity.Author"--%>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/9
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>作者详情</title>
    <link rel="icon" type="image/svg+xml" href="assets/img/web_icon.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="./assets/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="./assets/bootstrap-icons-1.11.2/font/bootstrap-icons.css">

</head>
<body class="bg-light d-flex align-items-center">

<div class="container">
    <div class="row justify-content-center">
        <div class="card col-md-8">

            <div class="card-body">
                <h3 class="card-title text-center">${author.name}</h3>
                <form>


                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info text-white col-2">国籍</span>
                        <label for="time"></label><input type="text" class="form-control" id="time" value="${author.nationality}" disabled>
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info text-white col-2">生日</span>
                        <label for="comment_num"></label><input type="text" class="form-control" id="comment_num" value="${author.birthDay}" disabled>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info text-white col-2">著作数</span>
                        <label for="pos_rate"></label><input type="text" class="form-control" id="pos_rate" value="${author.publishNumber}" disabled>
                    </div>

                </form>
                <div class="card">
                    <h5 class="card-title text-center text-success">简介</h5>
                    <div class="card-body">
                        ${author.description}
                    </div>
                </div>


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
