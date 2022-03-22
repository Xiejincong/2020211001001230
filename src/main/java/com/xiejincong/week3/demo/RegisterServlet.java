package com.xiejincong.week3.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/register"},loadOnStartup = 1)

public class RegisterServlet extends HttpServlet {
    Connection con =null;
    @Override
public void init() throws ServletException {
        super.init();
        ServletContext context=getServletContext();
        String driver=context.getInitParameter("driver");
        String url=context.getInitParameter("url");
        String username=context.getInitParameter("username");
        String password=context.getInitParameter("password");
        try{
            Class.forName(driver);
            con = DriverManager.getConnection("url,usernmae,password");
            System.out.println("init()-->"+ con);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        con = (Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String birthDate = request.getParameter("birthDate");

        PrintWriter writer = response.getWriter();
        writer.println("<br> username :" + username);
        writer.println("<br> password :" + password);
        writer.println("<br> email :" + email);
        writer.println("<br> gender :" + gender);
        writer.println("<br> birthDate :" + birthDate);
        writer.close();
        request.getRequestDispatcher("userList.jsp").forward(request,response);
        System.out.println("i am in RegisterServlet-->doPost()-->after forward()");
        response.sendRedirect("login.jsp");
    }

}
