<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/10
  Time: 9:05
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

<%--    <link rel="stylesheet" href="./assets/bootstrap-icons-1.11.2/font/bootstrap-icons.css">--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">

    <script>
        function validateForm(){
            let account = document.getElementById("rg_account").value;
            let username = document.getElementById("rg_username").value;
            let email = document.getElementById("rg_email").value;
            let phone_number = document.getElementById("rg_phone_number").value;
            let form = document.getElementById("rg_form")
            let is_valid_account = /^[a-zA-Z0-9]{8,16}$/.test(account);

            if (account === '' || username === '' || email === '' || phone_number === '')
            {
                alert("信息填写不完整！");
                return false;
            } else if (!is_valid_account) {
                alert("账号必须由字母和数字组成，且长度在8到16之间！");
                return false;
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
<body class="bg-light d-flex align-items-center" style="height: 100vh">

<div class="container">
    <div class="row justify-content-center">
        <div class="card col-md-4">
            <div class="card-body">
                <h3 class="card-title text-center">修改个人信息</h3>
                <form action="ChangeAccountInfoServlet" method="post" id="rg_form">

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

                    <% HttpSession session1 = request.getSession(); %>
                    <% if (session1 == null || session1.getAttribute("session_identity") == null || Objects.equals(session1.getAttribute("session_identity").toString(), "0")){ %>

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info"><i class="bi bi-buildings text-white"></i></span>
                        <label for="rg_address"></label><input type="text" class="form-control" id="rg_address" name="rg_address" placeholder="地址">
                    </div>
                    <%}%>



                    <div class="row mt-2">
                        <div class="col-md-6">
                            <button type="button" class="btn btn-success w-100" onclick="validateForm()">修改</button>
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
<%--@elvariable id="successMessage" type="com.jspservlet.servlet.ChangeAccountInfoServlet"--%>
<c:if test = "${not empty successMessage}">


    <script>
        alert("${successMessage}");
    </script>
</c:if>

</body>

</html>
