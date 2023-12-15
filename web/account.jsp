<%--@elvariable id="account" type="com.jspservlet.entity.Customer"--%>
<%--@elvariable id="identity" type="com.jspservlet.servlet.HomeServlet"--%>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/9
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>账号管理</title>
    <link rel="icon" type="image/svg+xml" href="assets/img/web_icon.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="./assets/js/bootstrap.bundle.min.js"></script>
    <%--    <link rel="stylesheet" href="./assets/bootstrap-icons-1.11.2/font/bootstrap-icons.css">--%>

    <%-- 导入自定义的主体样式 --%>
    <link rel="stylesheet" href="assets/self_css/self_content.css">
    <%-- 导入自定义的cookie判断，防止绕过正常登录流程 --%>
    <%--    <script src="assets/self_js/verify_cookies.js"></script>--%>
    <script>
        function deleteAllCookies() {
            // 获取所有的 cookie
            let cookies = document.cookie.split(";");

            // 循环遍历所有 cookie 并设置过期时间为过去
            for (let i = 0; i < cookies.length; i++) {
                let cookie = cookies[i];
                let eqPos = cookie.indexOf("=");
                let name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
                document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/";
            }

            // 刷新页面或执行其他操作
            location.href="login.jsp"; // 刷新页面
        }
        function confirmAndRedirect() {
            // 使用 confirm() 函数显示确认对话框
            let confirmed = confirm("确定要销号吗？");

            // 如果用户点击了确定按钮
            if (confirmed) {
                // 执行跳转逻辑，可以使用 window.location.href 跳转到指定页面
                window.location.href = "DeleteSelfServlet?confirm=1";
            } else {
                // 用户点击了取消按钮，可以不执行任何跳转逻辑
            }
        }
    </script>

</head>
<%--<body class="bg-light d-flex align-items-center">--%>
<body class="d-flex">
<div class="container-fluid">
    <div class="row flex-nowrap" style="height: 100vh">
        <%@include file="sidebar.jsp"%>
        <div id="content" class="d-flex align-items-center">
            <div class="container">
                <%--            <div class="container bg-light d-flex align-items-center">--%>
                <div class="row justify-content-center">
                    <div class="card col-md-6">
                        <div class="card-body">
                            <h3 class="card-title text-center">账号信息</h3>
                            <form>

                                <div class="input-group mb-3">
                                    <%--                                <span class="input-group-text bg-info"><i class="bi bi-person-fill text-white"></i></span>--%>
                                    <span class="input-group-text bg-info text-white col-2">账号</span>
                                    <label for="rg_account"></label><input type="text" class="form-control" id="rg_account" name="rg_account" value="${account.id}" disabled>
                                    <div id="rg_account_feedback" class="invalid-feedback"></div>
                                </div>
                                <div class="input-group mb-3">
                                    <%--                                <span class="input-group-text bg-info"><i class="bi bi-emoji-sunglasses-fill text-white"></i></span>--%>
                                    <span class="input-group-text bg-info text-white col-2">昵称</span>
                                    <label for="rg_username"></label><input type="text" class="form-control" id="rg_username" name="rg_username" value="${account.name}" disabled>
                                </div>
                                <div class="input-group mb-3">
                                    <%--                                <span class="input-group-text bg-info"><i class="bi bi-envelope-fill text-white"></i></span>--%>
                                    <span class="input-group-text bg-info text-white col-2">邮箱</span>
                                    <label for="rg_email"></label><input type="text" class="form-control" id="rg_email" name="rg_email" value="${account.email}" disabled>
                                </div>
                                <div class="input-group mb-3">
                                    <%--                                <span class="input-group-text bg-info"><i class="bi bi-phone-fill text-white"></i></span>--%>
                                    <span class="input-group-text bg-info text-white col-2">手机</span>
                                    <label for="rg_phone_number"></label><input type="text" class="form-control" id="rg_phone_number" name="rg_phone_number" value="${account.telephone}" disabled>
                                </div>

                                <div class="input-group mb-3">

                                    <span class="input-group-text bg-info text-white col-2">等级</span>
                                    <label for="level"></label><input type="text" class="form-control" id="level" value="${account.vipLevel}" disabled>
                                </div>

                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-info text-white col-2">已花费</span>
                                    <label for="purchase_sum"></label><input type="text" class="form-control" id="purchase_sum" value="${account.totalCost}" disabled>
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-info text-white col-2">默认收货地</span>
                                    <label for="def_location"></label><input type="text" class="form-control" id="def_location" value="${account.location}" disabled>
                                </div>




                                <div class="row">
                                    <div class="col-md-6">
                                        <button type="button" class="btn btn-primary w-100" onclick="window.location.href='change_account_info.jsp'">修改信息</button>
                                    </div>
                                    <div class="col-md-6">
                                        <button type="button" class="btn btn-dark w-100" onclick="window.location.href='change_account_passwd.jsp'">修改密码</button>
                                    </div>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-md-6">
                                        <button type="button" class="btn btn-warning w-100" onclick="deleteAllCookies()">登出</button>
                                        <%--                                    <button type="button" class="btn btn-warning w-100" onclick="window.location.href='<%=request.getContextPath()+"/assets/self_js/delete_cookies.jsp"%>'">登出</button>--%>
                                    </div>
                                    <div class="col-md-6">
                                        <%--                                    待写--%>
                                        <button type="button" class="btn btn-danger w-100 bg-danger" onclick="confirmAndRedirect()">销号</button>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
