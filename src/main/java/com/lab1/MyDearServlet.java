package com.lab1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyDearServlet", value = "/myDear")
public class MyDearServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        String MyClass=request.getParameter("myclass");
        String ID=request.getParameter("id");
        String submit=request.getParameter("submitButton");
        PrintWriter out=response.getWriter();
        out.println("xiejincong-2020211001001230");
        out.println("name:"+name);
        out.println("submit:"+submit);
        out.println("class:"+MyClass);
        out.println("id:"+ID);
    }
}
