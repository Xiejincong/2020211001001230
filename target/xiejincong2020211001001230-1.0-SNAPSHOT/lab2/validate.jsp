<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 5/15/2021
  Time: 11:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>validate</title>
</head>
<body>
<%--Todo 1: Use <jsp:useBean> to create a Login bean instance in request scope --%>
<jsp:useBean id="login" class="com.lab2.Login" scope="request"></jsp:useBean>
    <%--Todo 2: Use <jsp:setProperty> to set  beans' property username and password--%>
<jsp:setProperty name="login" property="username" value="${param.username}"></jsp:setProperty>
<jsp:setProperty name="login" property="password" value="${param.password}"></jsp:setProperty>
<%
   //todo 3: use if check username is admin and password is admin
    if((login.getUsername().equals("admin"))&&(login.getPassword().equals("admin"))){
%>
    <%--todo 4: use jsp:forward to welcome.jsp page--%>
<jsp:forward page="welcome.jsp"></jsp:forward>
<%
    }else{
%>
    <%--todo 5: else part{ --%>

<%
// todo 6: print username or password error message
   out.println("Username Password Error");

%>
    <%--todo 7: use jsp:include login.jsp page --%>
<jsp:include page="login.jsp"></jsp:include>
    <%--todo 8: close else --%>
<%}%>
</body>
</html>