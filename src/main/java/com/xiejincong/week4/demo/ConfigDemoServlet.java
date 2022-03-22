package com.xiejincong.week4.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="ConfigDemoServlet",value="/config",
initParams ={
        @WebInitParam(name ="name",value ="xiejincong"),
        @WebInitParam(name="studentid",value ="2020211001001230"),
}
)

public class ConfigDemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
ServletConfig config=getServletConfig();
String name=config.getInitParameter("name");
String studentid=config.getInitParameter("studentid");
        PrintWriter writer=response.getWriter();
        writer.println("<h1> name:"+name);
        writer.println("<h1> studentid:"+studentid);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
