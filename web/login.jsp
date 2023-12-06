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
            let username = document.getElementById("lg_username").value;
            let email = document.getElementById("lg_email").value;
            let phone_number = document.getElementById("lg_phone_number").value;
            let password = document.getElementById("lg_password").value;
            let confirm_password = document.getElementById("lg_confirm_password").value;
            let form = document.getElementById("login_form")

            // 使用 JSTL 输出 JavaScript 代码
            if (username === '' || email === '' || phone_number === '' || password === '' || confirm_password === '')
            {
                alert("注册信息填写不完整！");
                return false;
            }
            else if(password !== confirm_password){
                alert("两处密码填写不一致！")
            }else{
                form.submit();
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
                <h3 class="card-title text-center">注册</h3>
                <form action="LoginServlet" method="post" id="login_form">

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-person-fill text-white"></i></span>
                        <label for="lg_username"></label><input type="text" class="form-control" id="lg_username" placeholder="昵称">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-envelope-fill text-white"></i></span>
                        <label for="lg_email"></label><input type="text" class="form-control" id="lg_email" placeholder="邮箱">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-phone-fill text-white"></i></span>
                        <label for="lg_phone_number"></label><input type="text" class="form-control" id="lg_phone_number" placeholder="手机号">
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-key-fill text-white"></i></span>
                        <label for="lg_password"></label><input type="password" class="form-control" id="lg_password" placeholder="密码">
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-key-fill text-white"></i></span>
                        <label for="lg_confirm_password"></label><input type="password" class="form-control" id="lg_confirm_password" placeholder="确认密码">
                    </div>

                    <div class="d-grid">

                            <button type="button" class="btn btn-success w-100 bg-success" onclick="validateForm()">注册</button>

                    </div>


                </form>
            </div>
        </div>
    </div>

</div>

</body>

</html>
