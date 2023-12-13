<%--@elvariable id="vip_level" type="com.jspservlet.servlet.CurrentOrderServlet"--%>
<%--@elvariable id="current_order" type="com.jspservlet.entity.Order"--%>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/9
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>当前订单</title>
    <link rel="icon" type="image/svg+xml" href="assets/img/web_icon.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="./assets/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="./assets/bootstrap-icons-1.11.2/font/bootstrap-icons.css">

    <%-- 导入自定义的主体样式 --%>
    <link rel="stylesheet" href="assets/self_css/self_content.css">
    <%-- 导入自定义的cookie判断，防止绕过正常登录流程 --%>
<%--    <script src="assets/self_js/verify_cookies.js"></script>--%>
    <%-- 导入自定义的表格样式 --%>
    <link rel="stylesheet" href="assets/self_css/self_table.css">

</head>
<body>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <!-- 侧边导航栏 -->
        <%@include file="sidebar.jsp"%>

        <!-- 主要内容区域 -->
        <div id="content">
            <h1>当前订单</h1>
            <div class="container mt-4">
                <%--                将标签和输入分为两行来解决对齐问题，但当页面过窄时依然会效果不佳--%>
                <div class="row g-3 align-items-center">
                    <div class="col-md-2">
                        <label for="order_id" class="form-label">订单号</label>
                    </div>
                    <div class="col-md-2">
                        <label for="update_time" class="form-label">更新时间</label>
                    </div>
                    <div class="col-md-2">
                        <label for="receipt_place" class="form-label">收货地址</label>
                    </div>
                    <div class="col-md-2">
                        <%
                            int vip_level = Integer.parseInt(request.getAttribute("vip_level").toString());
                        %>
                        <label for="price_sum" class="form-label">总金额<%=vip_level==0?"":"（已打"+(10-vip_level)+"折）"%></label>
                    </div>
                </div>
                <form class="row g-3 align-items-center" method="post" action="HomeServlet">


                    <div class="col-md-2">
                        <input type="text" class="form-control" id="order_id" value="${current_order.orderNumber}" disabled>
                    </div>
                    <div class="col-md-2">
                        <input type="text" class="form-control" id="update_time" name="author" value="${current_order.updateTime}" disabled>
                    </div>
                    <div class="col-md-2">
                        <input type="text" class="form-control" id="receipt_place" name="press_name" value="${current_order.address}">
                    </div>
                    <div class="col-md-2">
                        <input type="text" class="form-control" id="price_sum" name="press_name" value="${current_order.price}" disabled>
                    </div>

                    <div class="col-md-1 ms-auto">
<%--                        TODO--%>
                        <button type="submit" class="btn btn-outline-warning w-100">提交订单</button>
                    </div>
                </form>
            </div>
            <div style="height: 80vh; overflow: auto;">
                <table class="table table-light table-striped table-hover">
                    <thead>
                    <tr class="table-dark">
                        <th>书名</th>
                        <th>价格</th>
                        <th>购买数量</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- 使用 JSTL 遍历书籍列表，并生成表格行 -->
                        <%--@elvariable id="current_order_books" type="java.util.List"--%>
                        <c:forEach var="order_book" items="${current_order_books}">
                            <tr>

                                <td><a class="custom-link" href="BookDetailServlet?isbn=${order_book.ISBN}&in=1">${order_book.book_name}</a></td>
                                <td>${order_book.price}</td>
                                <td>
                                    <label>
                                        <input type="number" name="quantity" min="1" value="${order_book.order_sum}">
                                    </label>
                                </td>

                                <td> <a class="custom-link" href="MoveCurrentOrderBookServlet?isbn=${order_book.ISBN}&order_id=${current_order.orderNumber}">移除</a></td>
                            </tr>
                        </c:forEach>
<%--                    <tr>--%>

<%--                        <td>test</td>--%>
<%--                        <td>test</td>--%>
<%--                        <td>--%>
<%--                            <label>--%>
<%--                                <input type="number" name="quantity" min="1" value="test">--%>
<%--                            </label>--%>
<%--                        </td>--%>
<%--                        <td> <a class="custom-link" href="press_detail.jsp?author_name=test">移除</a></td>--%>
<%--                    </tr>--%>
                    </tbody>
                </table>
            </div>
            <div class="row mt-3 justify-content-end">
                <div class="row col-2">
                    总金额：${current_order.price}
                </div>
                <div class="row col-2 justify-content-end">
                    <button class="btn btn-warning">
                        购买
                    </button>
                </div>
            </div>



        </div>
    </div>
</div>

<%--@elvariable id="successMessage" type="com.jspservlet.servlet.RegisterServlet"--%>
<c:if test = "${not empty successMessage}">
    <script>
        alert("${successMessage}");
    </script>
</c:if>
</body>
</html>
