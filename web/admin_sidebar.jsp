<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/10
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%-- 导入自定义的侧边栏样式   --%>
<link rel="stylesheet" href="assets/self_css/self_sidebar.css">
<nav id="sidebar">
    <p id="sidebar-heading">管理员</p>
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link<%= (request.getRequestURI().endsWith("customers.jsp")) ? " active" : "" %>" href="CustomersServlet">
                普通用户
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link<%= (request.getRequestURI().endsWith("admins")) ? " active" : "" %>" href="AdminsServlet">
                管理员用户
            </a>
        </li>
        <li class="nav-item">
                <a class="nav-link<%= (request.getRequestURI().endsWith("books.jsp")) ? " active" : "" %>" href="HomeServlet">
                书籍管理
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link<%= (request.getRequestURI().endsWith("account.jsp")) ? " active" : "" %>" href="AccountServlet">
                账号管理
            </a>
        </li>
    </ul>
</nav>