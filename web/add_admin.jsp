<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/11
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>
<head>
    <title>添加管理员用户</title>
    <link rel="icon" type="image/svg+xml" href="assets/img/web_icon.svg">
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
            let account = document.getElementById("rg_account").value;
            let username = document.getElementById("rg_username").value;
            let email = document.getElementById("rg_email").value;
            let phone_number = document.getElementById("rg_phone_number").value;
            let password = document.getElementById("rg_password").value;
            let confirm_password = document.getElementById("rg_confirm_password").value;
            let form = document.getElementById("rg_form")
            let is_valid_account = /^[a-zA-Z0-9]{8,16}$/.test(account);

            if (account === '' || username === '' || email === '' || phone_number === '' || password === '' || confirm_password === '')
            {
                alert("注册信息填写不完整！");
                return false;
            } else if (!is_valid_account) {
                alert("账号必须由字母和数字组成，且长度在8到16之间！");
                return false;
            } else if (password !== confirm_password){
                alert("两处密码填写不一致！")
            } else {

                form.submit();
            }


        }

        function checkAccount() {
            let account = document.getElementById("rg_account");
            let account_feedback = document.getElementById("rg_account_feedback");
            let is_valid = /^[a-zA-Z0-9]{8,16}$/.test(account.value);
            if (is_valid) {
                account.classList.remove("is-invalid");
                account.classList.add("is-valid");
                account_feedback.innerHTML="";
            } else {
                account.classList.remove("is-valid");
                account.classList.add("is-invalid");
                account_feedback.innerHTML = "账号必须由字母和数字组成，且长度在8到16之间！"
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
                <h3 class="card-title text-center">添加管理员用户</h3>
                <form action="AddAdminServlet" method="post" id="rg_form">

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-person-fill text-white"></i></span>
                        <label for="rg_account"></label><input type="text" class="form-control" id="rg_account" name="rg_account" placeholder="账号" oninput="checkAccount()">
                        <div id="rg_account_feedback" class="invalid-feedback"></div>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-emoji-sunglasses-fill text-white"></i></span>
                        <label for="rg_username"></label><input type="text" class="form-control" id="rg_username" name="rg_username" placeholder="昵称">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-envelope-fill text-white"></i></span>
                        <label for="rg_email"></label><input type="text" class="form-control" id="rg_email" name="rg_email" placeholder="邮箱">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-phone-fill text-white"></i></span>
                        <label for="rg_phone_number"></label><input type="text" class="form-control" id="rg_phone_number" name="rg_phone_number" placeholder="手机号">
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-key-fill text-white"></i></span>
                        <label for="rg_password"></label><input type="password" class="form-control" id="rg_password" name="rg_password" placeholder="密码">
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-key-fill text-white"></i></span>
                        <label for="rg_confirm_password"></label><input type="password" class="form-control" id="rg_confirm_password" placeholder="确认密码">
                    </div>

                    <div class="row mt-2">
                        <div class="col-md-6">
                            <button type="button" class="btn btn-outline-success w-100" onclick="validateForm()">添加用户</button>
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