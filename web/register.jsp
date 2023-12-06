<%--
  Created by IntelliJ IDEA.
  User: wsql
  Date: 2023/12/5
  Time: 8:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>
<head>
    <title>用户登录</title>
    <link rel="icon" type="image/svg+xml" href="./assets/img/webicon.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="./assets/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="./assets/bootstrap-icons-1.11.2/font/bootstrap-icons.css">
    <style>

        body {
            background-image: url('./assets/img/backimg.jpg');
            background-size: cover;
            background-position: center;
            height: 100vh;
            margin: 0;
        }

    </style>

    <script>
        function validateForm() {
            let username = document.getElementById("rg_username").value;
            let password = document.getElementById("rg_password").value;

            if (username == null || username === ''||password == null || password === '')
            {
                alert("登录信息未正确填写!");
            }
            else {
                window.location.href = "user.jsp";
            }


        }
    </script>

</head>
<!DOCTYPE html>
<body class="bg-light d-flex align-items-center">

<div class="container">
    <div class="row justify-content-center">
        <div class="card col-md-4">
            <div class="card-body">
                <h3 class="card-title text-center">登录</h3>
                <form action="">

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-person-fill text-white"></i></span>
                        <label for="rg_username"></label><input type="text" class="form-control" id="rg_username" placeholder="昵称/邮箱/手机号">
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-key-fill text-white"></i></span>
                        <label for="rg_password"></label><input type="password" class="form-control" id="rg_password" placeholder="密码">
                    </div>


                    <div class="row">
                        <div class="col-md-6">
                            <button type="button" class="btn btn-primary w-100" onclick="validateForm()">登录</button>
                        </div>
                        <div class="col-md-6">
                            <button type="button" class="btn btn-success w-100 bg-success" onclick="window.location.href='login.jsp'">注册</button>
                        </div>
                    </div>


                </form>
            </div>
        </div>
    </div>

</div>

</body>

</html>
