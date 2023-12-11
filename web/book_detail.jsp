<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/9
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>书籍详情</title>
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

            <%--书名--%>
<%--            <div class="card-header">  </div>--%>
            <div class="card-body">
                <h3 class="card-title text-center">书名</h3>
                <form>

<%--                    <div class="input-group mb-3">--%>
<%--                        <span class="input-group-text bg-info text-white col-2">书名</span>--%>
<%--                        <label for="book_name"></label>--%>
<%--                        <input type="text" class="form-control" id="book_name" value="测试书名" disabled>--%>
<%--                        &lt;%&ndash;                        <div id="lg_account_feedback" class="invalid-feedback"></div>&ndash;%&gt;--%>
<%--                    </div>--%>

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info text-white col-2">出版时间</span>
                        <label for="time"></label><input type="text" class="form-control" id="time" value="出版时间" disabled>
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info text-white col-2">评论数</span>
                        <label for="comment_num"></label><input type="text" class="form-control" id="comment_num" value="评论数" disabled>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info text-white col-2">好评数</span>
                        <label for="pos_rate"></label><input type="text" class="form-control" id="pos_rate" value="好评率" disabled>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info text-white col-2">作者</span>
                        <label for="author_name"></label><input type="text" class="form-control" id="author_name" value="作者" disabled>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info text-white col-2">种类</span>
                        <label for="category_name"></label><input type="text" class="form-control" id="category_name" value="种类" disabled>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info text-white col-2">价格</span>
                        <label for="price"></label><input type="text" class="form-control" id="price" name="rg_address" value="价格" disabled>
                    </div>

                </form>
                <div class="card">
                    <h5 class="card-title text-center text-success">简介</h5>
                    <div class="card-body">
                        aosidhgpoighpwei
                        asdopghapoiewg
                        asdl;ghpweihg[
                        ';asdgpohawegihap
                        adsguoeuhiuh
                    </div>
                </div>

                <% HttpSession session1 = request.getSession(); %>
                <% if (session1 == null || session1.getAttribute("session_identity") == null || Objects.equals(session1.getAttribute("session_identity").toString(), "0")){ %>

                    <div class="row mt-2">
                        <div class="col-md-6">
                            <button type="button" class="btn btn-warning w-100">添加至当前订单</button>
                        </div>
                        <div class="col-md-6">
                            <button type="button" class="btn btn-outline-secondary w-100" onclick="window.history.back()">返回</button>
                        </div>
                    </div>

                <%} else {%>
                    <div class="row mt-2 justify-content-center">

                        <div class="col-md-6">
                            <button type="button" class="btn btn-outline-secondary w-100" onclick="window.history.back()">返回</button>
                        </div>
                    </div>

                <%}%>
            </div>
        </div>
    </div>

</div>
<%--@elvariable id="errorMessage" type="com.jspservlet.servlet.LoginServlet"--%>
<c:if test = "${not empty errorMessage}">
    <script>
        alert("${errorMessage}");
    </script>
</c:if>
</body>

</html>
