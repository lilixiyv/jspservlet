<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/10
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>
<head>
    <title>修改用户信息</title>
    <link rel="icon" type="image/svg+xml" href="assets/img/web_icon.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="./assets/js/bootstrap.bundle.min.js"></script>

    <link rel="stylesheet" href="./assets/bootstrap-icons-1.11.2/font/bootstrap-icons.css">
</head>
<!DOCTYPE html>
<body class="bg-light d-flex align-items-center" style="height: 100vh">

<div class="container">
    <div class="row justify-content-center">
        <div class="card col-md-4">
            <div class="card-body">
                <h3 class="card-title text-center">修改密码</h3>
                <form action="RegisterServlet" method="post" id="rg_form">
                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-key-fill text-white"></i></span>
                        <label for="old_password"></label><input type="password" class="form-control" id="old_password" name="rg_password" placeholder="旧密码">
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-key-fill text-white"></i></span>
                        <label for="rg_password"></label><input type="password" class="form-control" id="rg_password" name="rg_password" placeholder="新密码">
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-key-fill text-white"></i></span>
                        <label for="rg_confirm_password"></label><input type="password" class="form-control" id="rg_confirm_password" placeholder="确认新密码">
                    </div>

                    <div class="row mt-2">
                        <div class="col-md-6">
                            <button type="button" class="btn btn-outline-dark w-100" onclick="validateForm()">修改</button>
                        </div>
                        <div class="col-md-6">
                            <button type="button" class="btn btn-outline-secondary w-100" onclick="window.history.back()">返回</button>
                        </div>
                    </div>


                </form>
            </div>
        </div>
    </div>

</div>


<%--@elvariable id="errorMessage" type="com.jspservlet.servlet.RegisterServlet"--%>
<c:if test = "${not empty errorMessage}">


    <script>
        alert("${errorMessage}");
    </script>
</c:if>


</body>

</html>
