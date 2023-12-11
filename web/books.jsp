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
    <title>书目管理</title>
    <link rel="icon" type="image/svg+xml" href="assets/img/web_icon.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="./assets/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="./assets/bootstrap-icons-1.11.2/font/bootstrap-icons.css">

    <%-- 导入自定义的主体样式 --%>
    <link rel="stylesheet" href="assets/self_css/self_content.css">
    <%-- 导入自定义的cookie判断，防止绕过正常登录流程 --%>
    <script src="assets/self_js/verify_cookies.js"></script>
    <%-- 导入自定义的表格样式 --%>
    <link rel="stylesheet" href="assets/self_css/self_table.css">

</head>
<body>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <!-- 侧边导航栏 -->
        <%@include file="admin_sidebar.jsp"%>

        <!-- 主要内容区域 -->
        <div id="content">
            <h1>书籍管理</h1>
            <div class="container mt-4">
                <%--                将标签和输入分为两行来解决对齐问题，但当页面过窄时依然会效果不佳--%>
                <div class="row g-3 align-items-center">

                    <div class="col-md-2">
                        <label for="bookName" class="form-label">书名</label>
                    </div>
                    <div class="col-md-1">
                        <label for="publishDate" class="form-label">出版时间</label>
                    </div>
                    <div class="col-md-1">
                        <label for="author" class="form-label">作者</label>
                    </div>
                    <div class="col-md-1">
                        <label for="publisher" class="form-label">出版社</label>
                    </div>
                    <div class="col-md-1">
                        <label for="category" class="form-label">分类</label>
                    </div>
                    <div class="col-md-1">
                        <label for="rating" class="form-label">好评率</label>
                    </div>
                    <div class="col-md-1">
                        <label for="commentCount" class="form-label">评论数</label>
                    </div>
                    <div class="col-md-1">
                        <label for="price" class="form-label">价格</label>
                    </div>
                    <div class="col-md-2">
                        <label for="sortOrder" class="form-label">排序</label>
                    </div>
                </div>
                <form class="row g-3 align-items-center" method="post" action="HomeServlet">
                    <div class="col-md-2">
                        <input type="text" class="form-control" id="bookName" name="book_name" placeholder="任意">
                    </div>
                    <div class="col-md-1">
                        <select class="form-select" id="publishDate" name="time">
                            <option selected value="-1">任意</option>
                            <option value="0">2010之前</option>
                            <option value="1">2010-15</option>
                            <option value="2">2015-20</option>
                            <option value="3">2020及之后</option>

                        </select>
                    </div>
                    <div class="col-md-1">
                        <input type="text" class="form-control" id="author" name="author" placeholder="任意">
                    </div>
                    <div class="col-md-1">
                        <input type="text" class="form-control" id="publisher" name="press_name" placeholder="任意">
                    </div>
                    <div class="col-md-1">
                        <select class="form-select" id="category" name="category">
                            <option selected value="-1">任意</option>
                            <option value="1">中国当代小说</option>
                            <option value="2">中国现当代随笔</option>
                            <option value="3">休闲</option>
                            <option value="4">传记</option>
                            <option value="5">历史</option>
                            <option value="6">名家文学</option>
                            <option value="7">哲学</option>
                            <option value="8">外国小说</option>
                            <option value="9">外国随笔</option>
                            <option value="10">情感小说</option>
                            <option value="11">推理小说</option>
                            <option value="12">文学评论</option>
                            <option value="13">文集</option>
                            <option value="14">法律</option>
                            <option value="15">社会小说</option>
                            <option value="16">艺术</option>
                            <option value="17">青春文学</option>
                        </select>
                    </div>
                    <div class="col-md-1">
                        <select class="form-select" id="rating" name="pos_rate">
                            <option selected value="-1">任意</option>
                            <option value="20">&lt;20%</option>
                            <option value="50">20%-50%</option>
                            <option value="80">50%-80%</option>
                            <option value="90">80%-90%</option>
                            <option value="100">&ge;90%</option>

                        </select>
                    </div>
                    <div class="col-md-1">
                        <select class="form-select" id="commentCount" name="comment_name">
                            <option selected value="-1">任意</option>
                            <option value="0">&lt;5</option>
                            <option value="1">5-10</option>
                            <option value="2">10-15</option>
                            <option value="3">15-20</option>
                            <option value="4">&ge;20</option>

                        </select>
                    </div>
                    <div class="col-md-1">
                        <select class="form-select" id="price" name="price">
                            <option selected value="-1">任意</option>
                            <option value="0">&lt;20</option>
                            <option value="1">20-50</option>
                            <option value="2">50-100</option>
                            <option value="3">&ge;100</option>

                        </select>
                    </div>
                    <div class="col-md-2">
                        <select class="form-select" id="sortOrder" name="sort_order">
                            <option selected value="0">按价格升序</option>
                            <option value="1">按价格降序</option>
                            <option value="2">按好评率升序</option>
                            <option value="3">按好评率降序</option>>

                        </select>
                    </div>
                    <div class="col-md-1">
                        <button type="submit" class="btn btn-secondary w-100">搜索</button>
                    </div>
                </form>
            </div>

            <div style="height: 65vh; overflow: auto;">
                <table class="table table-light table-striped table-hover">
                    <thead>
                    <tr class="table-dark">
                        <th>书名</th>
                        <th>出版时间</th>
                        <th>作者</th>
                        <th>出版社</th>
                        <th>分类</th>
                        <th>好评率</th>
                        <th>评论数</th>
                        <th>价格</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- 使用 JSTL 遍历书籍列表，并生成表格行 -->
                    <%--                    <c:forEach var="book" items="${bookList}">--%>
                    <%--                        <tr>--%>
                    <%--                            <!-- book.getName() 可以根据实际的 Book 类属性来调整 -->--%>
                    <%--                            <td><a href="bookDetail.jsp?bookId=${book.id}">${book.name}</a></td>--%>
                    <%--                            <td>${book.authorName}</td>--%>
                    <%--                            <td>${book.pressName}</td>--%>
                    <%--                            <td>${book.categoryName}</td>--%>
                    <%--                            <td>${book.price}</td>--%>
                    <%--                        </tr>--%>
                    <%--                    </c:forEach>--%>
                    <%for (int i = 1; i <=50; i++) {%>
                    <tr>
                        <%--跳转页面待改变--%>
<%--                        TODO 链接待改--%>
                        <td><a class="custom-link" href="book_detail.jsp?isbn=test"> test </a></td>
                        <td> test </td>
                        <td> <a class="custom-link" href="author_detail.jsp?author_name=test"> test </a> </td>
                        <td> <a class="custom-link" href="press_detail.jsp?press_name=test"> test </a></td>
                        <td> test </td>
                        <td> test </td>
                        <td> test </td>
                        <td> test </td>
                        <td> <a class="custom-link" href="press_detail.jsp?author_name=test"> test </a></td>
                    </tr>
                    <%}%>
                    </tbody>
                </table>


            </div>
            <div class="row mt-2 justify-content-center">
                <%--TODO 按钮方法待改变--%>
                <div class="col-md-6">
                    <button type="button" class="btn btn-outline-secondary w-100" onclick="window.location.href='add_book.jsp'">添加书籍</button>
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
