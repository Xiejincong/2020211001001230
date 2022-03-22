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
    <%
        if(!(request.getAttribute("message")==null)){
            out.println(request.getAttribute("message"));
        }
    %>
    <%
        Cookie[] allCookies=request.getCookies();
        String username="",password="",rememberMeVale="";
        if(allCookies!=null){
            for(Cookie c:allCookies) {
                if (c.getName().equals("cUsername")) {
                    username = c.getValue();
                }
                if (c.getName().equals("cPassword")) {
                    password = c.getValue();
                }
                if (c.getName().equals("cRememberMe")) {
                    rememberMeVale = c.getValue();
                }
            }
        }
    %>
username:<input type=text" name="username" value="<%=username%>"><br>
password:<input type="password" name="password" value="<%=password%>"><br>
    <input type="checkbox" value="1" <%=rememberMeVale.equals("1") ?checked:""%>checked/>RememberMe<br/>

<input type="submit" value=Login"><br>
</form>
<%@include file="footer.jsp"%>