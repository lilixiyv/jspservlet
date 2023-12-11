<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/4
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>
  <head>
    <title>index</title>
    <link rel="icon" type="image/svg+xml" href="assets/img/web_icon.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="./assets/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="./assets/bootstrap-icons-1.11.2/font/bootstrap-icons.css">
  </head>
  <!DOCTYPE html>
  <body>
<%--  自动跳转到login.jsp页面--%>
    <script>
      window.location.href="login.jsp";
      <%--window.location.href="<%=request.getRequestURI()+"test/test.jsp"%>"--%>
    </script>
  </body>

</html>
