<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/5
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>网上书店</title>
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
            <h1>书目</h1>
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
                    <%--                    从返回值中获得相应的属性进行显示--%>
                    <%
                        String book_name = request.getParameter("book_name");
                        String time = request.getParameter("time");
                        String author = request.getParameter("author");
                        String press_name = request.getParameter("press_name");
                        String category = request.getParameter("category");
                        String pos_rate = request.getParameter("pos_rate");
                        String comment_num = request.getParameter("comment_num");
                        String price = request.getParameter("price");
                        String sort_order = request.getParameter("sort_order");
                    %>
                        
                    <div class="col-md-2">
                        <input type="text" class="form-control" id="bookName" name="book_name" placeholder="任意" <%=(book_name==null|| book_name.isEmpty())? "":"value=\""+book_name+"\""%>>
                    </div>
                    <div class="col-md-1">
                        <select class="form-select" id="publishDate" name="time">
                            <option value="0 3000" <%=time == null || time.isEmpty() || time.equals("0 3000") ? "selected" : ""%>>任意</option>
                            <option value="0 2010" <%=time != null && time.equals("0 2010") ? "selected" : ""%>>2010之前</option>
                            <option value="2010 2015" <%=time != null && time.equals("2010 2015") ? "selected" : ""%>>2010-15</option>
                            <option value="2015 2020" <%=time != null && time.equals("2015 2020") ? "selected" : ""%>>2015-20</option>
                            <option value="2020 3000" <%=time != null && time.equals("2020 3000") ? "selected" : ""%>>2020及之后</option>
                        </select>
                    </div>
                    <div class="col-md-1">
                        <input type="text" class="form-control" id="author" name="author" placeholder="任意" <%=(author==null|| author.isEmpty())? "":"value=\""+author+"\""%>>
                    </div>
                    <div class="col-md-1">
                        <input type="text" class="form-control" id="publisher" name="press_name" placeholder="任意" <%=(press_name==null|| press_name.isEmpty())? "":"value=\""+press_name+"\""%>>
                    </div>
                    <div class="col-md-1">
                        <select class="form-select" id="category" name="category">
                            <option value="" <%=category == null || category.isEmpty() ? "selected" : ""%>>任意</option>
                            <option value="中国当代小说" <%=category != null && category.equals("中国当代小说") ? "selected" : ""%>>中国当代小说</option>
                            <option value="中国现当代随笔" <%=category != null && category.equals("中国现当代随笔") ? "selected" : ""%>>中国现当代随笔</option>
                            <option value="休闲" <%=category != null && category.equals("休闲") ? "selected" : ""%>>休闲</option>
                            <option value="传记" <%=category != null && category.equals("传记") ? "selected" : ""%>>传记</option>
                            <option value="历史" <%=category != null && category.equals("历史") ? "selected" : ""%>>历史</option>
                            <option value="名家文学" <%=category != null && category.equals("名家文学") ? "selected" : ""%>>名家文学</option>
                            <option value="哲学" <%=category != null && category.equals("哲学") ? "selected" : ""%>>哲学</option>
                            <option value="外国小说" <%=category != null && category.equals("外国小说") ? "selected" : ""%>>外国小说</option>
                            <option value="外国随笔" <%=category != null && category.equals("外国随笔") ? "selected" : ""%>>外国随笔</option>
                            <option value="情感小说" <%=category != null && category.equals("情感小说") ? "selected" : ""%>>情感小说</option>
                            <option value="推理小说" <%=category != null && category.equals("推理小说") ? "selected" : ""%>>推理小说</option>
                            <option value="文学评论" <%=category != null && category.equals("文学评论") ? "selected" : ""%>>文学评论</option>
                            <option value="文集" <%=category != null && category.equals("文集") ? "selected" : ""%>>文集</option>
                            <option value="法律" <%=category != null && category.equals("法律") ? "selected" : ""%>>法律</option>
                            <option value="社会小说" <%=category != null && category.equals("社会小说") ? "selected" : ""%>>社会小说</option>
                            <option value="艺术" <%=category != null && category.equals("艺术") ? "selected" : ""%>>艺术</option>
                            <option value="青春文学" <%=category != null && category.equals("青春文学") ? "selected" : ""%>>青春文学</option>
                        </select>
                    </div>
                    <div class="col-md-1">
                        <select class="form-select" id="rating" name="pos_rate">
                            <option value="-1.0 2.0" <%=pos_rate == null || pos_rate.isEmpty() || pos_rate.equals("-1.0 2.0") ? "selected" : ""%>>任意</option>
                            <option value="-1.0 0.2" <%=pos_rate != null && pos_rate.equals("-1.0 0.2") ? "selected" : ""%>>&lt;20%</option>
                            <option value="0.2 0.5" <%=pos_rate != null && pos_rate.equals("0.2 0.5") ? "selected" : ""%>>20%-50%</option>
                            <option value="0.5 0.8" <%=pos_rate != null && pos_rate.equals("0.5 0.8") ? "selected" : ""%>>50%-80%</option>
                            <option value="0.8 0.9" <%=pos_rate != null && pos_rate.equals("0.8 0.9") ? "selected" : ""%>>80%-90%</option>
                            <option value="0.9 2.0" <%=pos_rate != null && pos_rate.equals("0.9 2.0") ? "selected" : ""%>>&ge;90%</option>

                        </select>
                    </div>
                    <div class="col-md-1">
                        <select class="form-select" id="commentCount" name="comment_num">
                            <option value="-1 1000000" <%=comment_num == null || comment_num.isEmpty() || comment_num.equals("-1 1000000") ? "selected" : ""%>>任意</option>
                            <option value="0 1" <%=comment_num != null && comment_num.equals("0 1") ? "selected" : ""%>>&lt;1</option>
                            <option value="1 1500" <%=comment_num != null && comment_num.equals("1 1500") ? "selected" : ""%>>1-1500</option>
                            <option value="1500 15000" <%=comment_num != null && comment_num.equals("1500 15000") ? "selected" : ""%>>1500-15000</option>
                            <option value="15000 100000" <%=comment_num != null && comment_num.equals("15000 100000") ? "selected" : ""%>>15000-100000</option>
                            <option value="100000 1000000" <%=comment_num != null && comment_num.equals("100000 1000000") ? "selected" : ""%>>&ge;100000</option>

                        </select>
                    </div>
                    <div class="col-md-1">
                        <select class="form-select" id="price" name="price">
                            <option selected value="-1 10000" <%=price == null || price.isEmpty() || price.equals("-1 10000") ? "selected" : ""%>>任意</option>
                            <option value="-1 20" <%=price != null && price.equals("-1 20") ? "selected" : ""%>>&lt;20</option>
                            <option value="20 50" <%=price != null && price.equals("20 50") ? "selected" : ""%>>20-50</option>
                            <option value="50 100" <%=price != null && price.equals("50 100") ? "selected" : ""%>>50-100</option>
                            <option value="100 10000" <%=price != null && price.equals("100 10000") ? "selected" : ""%>>&ge;100</option>

                        </select>
                    </div>
                    <div class="col-md-2">
                        <select class="form-select" id="sortOrder" name="sort_order">
                            <option value="price 0" <%=sort_order == null || sort_order.isEmpty() || sort_order.equals("price 0") ? "selected" : ""%>>按价格升序</option>
                            <option value="price 1" <%=sort_order != null && sort_order.equals("price 1") ? "selected" : ""%>>按价格降序</option>
                            <option value="pos_rate 0" <%=sort_order != null && sort_order.equals("pos_rate 0") ? "selected" : ""%>>按好评率升序</option>
                            <option value="pos_rate 1" <%=sort_order != null && sort_order.equals("pos_rate 1") ? "selected" : ""%>>按好评率降序</option>

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
                    </tr>
                    </thead>
                    <tbody>
                    <!-- 使用 JSTL 遍历书籍列表，并生成表格行 -->

                    <%--@elvariable id="bookList" type="com.jspservlet.entity.Book"--%>
                    <c:forEach var="book" items="${bookList}">
                        <tr>
                            <td><a class="custom-link" href="BookDetailServlet?isbn=${book.isbn}">${book.title}</a></td>
                            <td>${book.publishDate}</td>
                            <td><a class="custom-link" href="AuthorDetailServlet?author_name=${book.author.name}"> ${book.author.name}</a></td>
                            <td><a class="custom-link" href="PressDetailServlet?press_name=${book.publishHouse.name}">${book.publishHouse.name}</a></td>
                            <td>${book.category.categoryName}</td>
                            <td>${book.goodRate}</td>
                            <td>${book.reviewAmount}</td>
                            <td>${book.price}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="row mt-2 justify-content-center">
                <div class="col-md-6">
                    <button type="button" class="btn btn-outline-secondary w-100" onclick="window.location.href='CountServlet'">统计并导出</button>
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

<%--使用cookie防止用户绕过登录直接进入网页--%>
<%--<%--%>
<%--    Cookie[] cookies = request.getCookies();--%>
<%--    String account = null;--%>
<%--    if (cookies != null) {--%>
<%--        for (Cookie cookie : cookies) {--%>
<%--            if ("account".equals(cookie.getName())) {--%>
<%--                account = cookie.getValue();--%>
<%--                break;--%>
<%--            }--%>
<%--        }--%>
<%--    }--%>
<%--    if (cookies == null || account == null){--%>
<%--       request.setAttribute("errorMessage", "请先登录！");--%>
<%--       request.getRequestDispatcher("login.jsp").forward(request,response);--%>
<%--    }--%>
<%--%>--%>