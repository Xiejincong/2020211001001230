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
    Connection con=null;
    @Override
    public void init() throws ServletException{
        super.init();
        con = (Connection) getServletContext().getAttribute("con");
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
            con= DriverManager.getConnection(url,username,password);
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
        PrintWriter out= response.getWriter();
        String uname=request.getParameter("username");
     String upw=request.getParameter("password");
     String password=null;

        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select password from Usertable where username='"+uname+"'");
            while (rs.next()){
                //week5 code
                //password=rs.getString("password");
                request.setAttribute("id",rs.getInt("id"));
                request.setAttribute("username",rs.getString("username"));
                request.setAttribute("password",rs.getString("password"));
                request.setAttribute("email",rs.getString("email"));
                request.setAttribute("gender",rs.getString("gender"));
                request.setAttribute("birthdate",rs.getString("birthdate"));
                request.getRequestDispatcher("userInfo.jsp").forward(request,response);
            }
            if(upw.equals(password.trim())){
               // out.println("Login Success");
               // out.println("Welcome"+uname);
                request.setAttribute("messaage","Username or password Error!!!");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        }catch (Exception e){
           // out.println("wu le");
        }
    }
}
