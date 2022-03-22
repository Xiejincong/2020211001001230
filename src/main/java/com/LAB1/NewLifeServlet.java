package com.LAB1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "NewLifeServlet", value = "/newLife")
public class NewLifeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String name=request.getParameter("name");
String Class=request.getParameter("class");
String ID=request.getParameter("id");
        PrintWriter out=response.getWriter();
        out.println("name:"+name);
        out.println("class:"+Class);
        out.println("id:"+ID);
        out.println("submit:Send data to server");
    }



}
