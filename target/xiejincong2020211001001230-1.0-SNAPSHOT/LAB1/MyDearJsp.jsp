<%--
  Created by IntelliJ IDEA.
  User: xjc
  Date: 2022/5/18
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
name:<%=request.getParameter("name")%><br>
<h2>Send data to server</h2>
Class:<%=request.getParameter("class")%><br>
ID:<%=request.getParameter("id")%>
<h3>Use EL</h3>
name:${param.name}
<h4>Send data to server</h4>
Class:${param.myclass}<br>
ID:${param.id}
