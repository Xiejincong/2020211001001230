package com.xiejincong.week5.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name="LoginServlet", value="/login")
public class LoginServlet extends HttpServlet {
    Connection dbConn=null;
    @Override
    public void init() throws ServletException{
        super.init();
        String driver=getServletConfig().getServletContext().getInitParameter("driver");
        String url=getServletConfig().getServletContext().getInitParameter("url");
        String username=getServletConfig().getServletContext().getInitParameter("username");
        String password=getServletConfig().getServletContext().getInitParameter("password");
        try{
            Class.forName(driver);
            System.out.println("loading driver succeeded!");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to load driver!");
        }
        try{
            dbConn= DriverManager.getConnection(url,username,password);
            System.out.println("Successfully connected to database!");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Sql Server connection failed!");
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String uname=request.getParameter("username");
     String upw=request.getParameter("password");
     String password=null;
        PrintWriter out=response.getWriter();
        try{
            Statement stmt=dbConn.createStatement();
            ResultSet rs=stmt.executeQuery("select password from Usertable where username='"+uname+"'");
            while (rs.next()){
                password=rs.getString("password");
            }
            if(upw.equals(password.trim())){
                out.println("Login Success");
                out.println("Welcome"+uname);
            }
        }catch (Exception e){
            out.println("wu le");
        }
    }
}
