<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/9
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%-- 导入自定义的侧边栏样式   --%>
<link rel="stylesheet" href="assets/self_css/self_sidebar.css">
<nav id="sidebar">
    <p id="sidebar-heading">网上书店</p>
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link<%= (request.getRequestURI().endsWith("HomeServlet")) ? " active" : "" %>" href="HomeServlet">
                首页
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link<%= (request.getRequestURI().endsWith("CurrentOrderServlet")) ? " active" : "" %>" href="CurrentOrderServlet">
                当前订单
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link<%= (request.getRequestURI().endsWith("history_order.jsp")) ? " active" : "" %>" href="history_order.jsp">
                历史订单
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link<%= (request.getRequestURI().endsWith("account.jsp")) ? " active" : "" %>" href="account.jsp">
                账号管理
            </a>
        </li>
    </ul>
</nav>
