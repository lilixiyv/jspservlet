<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/10
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>管理员用户管理</title>
    <link rel="icon" type="image/svg+xml" href="assets/img/web_icon.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="./assets/js/bootstrap.bundle.min.js"></script>
<%--    <link rel="stylesheet" href="./assets/bootstrap-icons-1.11.2/font/bootstrap-icons.css">--%>

    <%-- 导入自定义的主体样式 --%>
    <link rel="stylesheet" href="assets/self_css/self_content.css">
    <%-- 导入自定义的cookie判断，防止绕过正常登录流程 --%>
<%--    <script src="assets/self_js/verify_cookies.js"></script>--%>
    <%-- 导入自定义的表格样式 --%>
    <link rel="stylesheet" href="assets/self_css/self_table.css">

    <script>
        function deleteAdmin(admin_id){
            let confirmed = confirm("确定要移除该账号吗？");

            // 如果用户点击了确定按钮
            if (confirmed) {
                // 执行跳转逻辑，可以使用 window.location.href 跳转到指定页面
                window.location.href = "DeleteCustomerServlet?confirm=2&account="+admin_id;
            } else {
                // 用户点击了取消按钮，可以不执行任何跳转逻辑
            }
        }
    </script>

</head>
<body>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <!-- 侧边导航栏 -->
        <%@include file="admin_sidebar.jsp"%>

        <!-- 主要内容区域 -->
        <div id="content">
            <h1>管理员账号</h1>

            <div style="height: 80vh; overflow: auto;">
                <table class="table table-light table-striped table-hover">
                    <thead>
                    <tr class="table-dark">
                        <th>账号</th>
                        <th>昵称</th>
                        <th>邮箱</th>
                        <th>电话</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--@elvariable id="administratorList" type="java.util.List"--%>
                    <c:forEach var="admin" items="${administratorList}">
                        <tr>
                            <td>${admin.id}</td>
                            <td>${admin.name}</td>
                            <td>${admin.email}</td>
                            <td>${admin.telephone}</td>
                            <td><button onclick="deleteAdmin('${admin.id}')">移除</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="row mt-2 justify-content-center">

                <div class="col-md-6">

                    <button type="button" class="btn btn-outline-secondary w-100" onclick="window.location.href='add_admin.jsp'">添加用户</button>
                </div>
            </div>


        </div>
    </div>
</div>
<%--TODO errorMessage--%>
<%--@elvariable id="successMessage" type="com.jspservlet.servlet.RegisterServlet"--%>
<c:if test = "${not empty successMessage}">
    <script>
        alert("${successMessage}");
    </script>
</c:if>
<%--@elvariable id="errorMessage" type="com.jspservlet.servlet.AdminsServlet"--%>
<c:if test = "${not empty errorMessage}">
    <script>
        alert("${errorMessage}");
    </script>
</c:if>
</body>
</html>