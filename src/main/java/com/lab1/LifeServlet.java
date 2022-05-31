package com.lab1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LifeServlet", value = "/life")
public class LifeServlet extends HttpServlet {
    static int i=0;
    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("I am in init");
        System.out.println("I am from default constructor");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet");
        PrintWriter out=response.getWriter();
        out.println("Since loading,this servlet has been accessed "+(++i)+" times");
        out.println("xiejincong-2020211001001230");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost");
    }

    @Override
    public void destroy() {
        System.out.println("I am in destroy");
        super.destroy();
    }
}