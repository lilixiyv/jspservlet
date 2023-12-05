<%--
  Created by IntelliJ IDEA.
  User: wsql
  Date: 2023/12/5
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>
<head>
    <title>用户注册</title>
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
        function validateForm(){
            let username = document.getElementById("username").value;
            let email = document.getElementById("email").value;
            let phone_number = document.getElementById("phone_number").value;
            let password = document.getElementById("password").value;

            // 使用 JSTL 输出 JavaScript 代码
            if (username == null || username === ''||password == null || password === '' || email == null )
            {
                alert("登录信息未正确填写！");
                return false;
            }

            return true;
        }
    </script>

</head>
<!DOCTYPE html>
<body class="bg-light d-flex align-items-center">

<div class="container">
    <div class="row justify-content-center">
        <div class="card col-md-4">
            <div class="card-body">
                <h3 class="card-title text-center">注册</h3>
                <form onsubmit="">

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-person-fill text-white"></i></span>
                        <label for="username"></label><input type="text" class="form-control" id="username" placeholder="昵称">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-envelope-fill text-white"></i></span>
                        <label for="email"></label><input type="text" class="form-control" id="email" placeholder="邮箱">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-phone-fill text-white"></i></span>
                        <label for="phone_number"></label><input type="text" class="form-control" id="phone_number" placeholder="手机号">
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-key-fill text-white"></i></span>
                        <label for="password"></label><input type="password" class="form-control" id="password" placeholder="密码">
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-key-fill text-white"></i></span>
                        <label for="confirm_password"></label><input type="password" class="form-control" id="confirm_password" placeholder="确认密码">
                    </div>

                    <div class="d-grid">

                            <button type="button" class="btn btn-success w-100 bg-success">注册</button>

                    </div>


                </form>
            </div>
        </div>
    </div>

</div>

</body>

</html>
