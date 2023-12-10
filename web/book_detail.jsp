<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/9
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍详情</title>
</head>
<body>
    <div class="container book-details">
        <h1 class="mt-4">Book Title</h1>
        <p class="author">Author: John Doe</p>
        <p class="publisher">Publisher: ABC Publications</p>

        <div class="comments">
            <h2>Comments</h2>
            <div class="comment">
                <h4>User1</h4>
                <p>This is a great book! I enjoyed reading it.</p>
            </div>
            <div class="comment">
                <h4>User2</h4>
                <p>Not bad, but I expected more from the author.</p>
            </div>
            <!-- More comments can be added dynamically using JavaScript -->
        </div>
    </div>
    <button class="btn-close" onclick="window.history.back();">返回</button>
</body>
</html>
