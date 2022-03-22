package com.xiejincong.week6.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet(name="RedirectServlet", value="/redirect")

public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("before redirect");
        response.sendRedirect("index.jsp");
        System.out.println("after redirect");
        response.sendRedirect("/2020211001001230xiejincong_war_exploded/index.jsp");
        response.sendRedirect("www.bilibili.com");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request,response);
    }
}
