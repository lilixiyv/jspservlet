<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/11
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="javax.servlet.http.Cookie" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0); // 设置Cookie的最大生存时间为0，即立即失效
            cookie.setPath("/"); // 设置Cookie的路径，确保能够清除所有路径下的Cookie
            response.addCookie(cookie); // 添加修改后的Cookie到响应中
        }
    }
%>
<script>
    window.location.href="<%=request.getRequestURI()+"login.jsp"%>"
</script>
</body>
</html>
