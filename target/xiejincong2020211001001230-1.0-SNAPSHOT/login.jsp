<%--
  Created by IntelliJ IDEA.
  User: xjc
  Date: 2022/3/29
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<form method="post" action="login">
<h1>Login</h1>
username:<input type=text" name="username" placeholder="username"><br>
password:<input type="password" name="password" placeholder="password"><br>
<input type="submit" value=Login"><br>
</form>
<%@include file="footer.jsp"%>