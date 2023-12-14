<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/11
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加书籍</title>
    <link rel="icon" type="image/svg+xml" href="assets/img/web_icon.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="./assets/js/bootstrap.bundle.min.js"></script>
<%--    <link rel="stylesheet" href="./assets/bootstrap-icons-1.11.2/font/bootstrap-icons.css">--%>

    <script>
        function checkInputLength() {
            let inputElement = document.getElementById("book_description");
            let inputValue = inputElement.value;

            if (inputValue.length > 180) {
                alert("输入长度不能超过180个字符");
                inputElement.value = inputValue.substring(0, 180);
            }
        }
    </script>
</head>
<body class="bg-light d-flex align-items-center">

<div class="container">
    <div class="row justify-content-center">
        <div class="card col-md-8">

            <div class="card-body">
                <h3 class="card-title text-center">添加书籍</h3>
                <form action="AddBookServlet" method="post">

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info text-white col-2">ISBN</span>
                        <label for="isbn"></label><input type="text" class="form-control" id="isbn" name="isbn">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info text-white col-2">书名</span>
                        <label for="book_name"></label><input type="text" class="form-control" id="book_name" name="book_name">
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info text-white col-2">出版时间</span>
                        <label for="time"></label><input type="text" class="form-control" id="time" name="time">
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info text-white col-2">作者</span>
                        <label for="author_name"></label><input type="text" class="form-control" id="author_name" name="author_name">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info text-white col-2">出版商</span>
                        <label for="press_name"></label><input type="text" class="form-control" id="press_name" name="press_name">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info text-white col-2">种类</span>
                        <label for="category_name"></label><input type="text" class="form-control" id="category_name"  name="category_name">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text bg-info text-white col-2">价格</span>
                        <label for="price"></label><input type="text" class="form-control" id="price" name="price">
                    </div>
                    <div class="mb-3">
                        <label for="book_description" class="form-label">书籍简介</label>
                        <textarea class="form-control" id="book_description" rows="3" name="book_description" oninput="checkInputLength()"></textarea>
                    </div>

                    <div class="row mt-2 justify-content-center">
                        <div class="col-md-6">
                            <button type="submit" class="btn btn-outline-secondary w-100">提交</button>
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
<%--@elvariable id="errorMessage" type="com.jspservlet.servlet.LoginServlet"--%>
<c:if test = "${not empty errorMessage}">
    <script>
        alert("${errorMessage}");
    </script>
</c:if>
</body>

</html>