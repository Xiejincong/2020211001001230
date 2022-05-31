<%--
  Created by IntelliJ IDEA.
  User: xjc
  Date: 2022/5/18
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h6>Use java code</h6>
name:<%=request.getParameter("name")%><br>
submit:<%=request.getParameter("submitButton")%><br>
MyClass:<%=request.getParameter("myclass")%><br>
ID:<%=request.getParameter("id")%>
<hr>
<h6>Use EL</h6>
name:${param.name}<br>
submit:${param.submitButton}<br>
MyClass:${param.myclass}<br>
ID:${param.id}
<hr>
<h1>xiejincong-2020211001001230</h1>