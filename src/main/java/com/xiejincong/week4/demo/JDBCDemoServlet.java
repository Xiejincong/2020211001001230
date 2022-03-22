package com.xiejincong.week4.demo;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class JDBCDemoServlet extends HttpServlet {
    Connection con=null;
    @Override
    public void init() throws ServletException {
        super.init();
        String driver="com.microsoft.sqlserver.jdbc.SqlServerDriver";
        java.lang.String url="jdbc:sqlserver:localhost:1433;database=userab;encrypt=false";
        java.lang.String username="xjc";
        java.lang.String password="jc1230";

        try {
            Class.forName(driver);
             con= DriverManager.getConnection(url,username,password);
            System.out.println("Connection -->"+con);
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        java.lang.String username = request.getParameter("username");
        java.lang.String password = request.getParameter("password");
        java.lang.String email = request.getParameter("email");
        java.lang.String gender = request.getParameter("gender");
        java.lang.String birthDate = request.getParameter("birthDate");
        int id=1;
        try {
            Statement stmt = con.createStatement();
            java.lang.String x = "insert into Usertable(id,username,password,email,gender,birthDate)values (" + id + "," + username + "," +
                    "','" + password + "','" + email + "','" + gender + "','" + birthDate + "');";
            int count = stmt.executeUpdate(x);
            if (count > 0) {
                System.out.println("successful");
                id++;
            } else {
                System.out.println("zhejieG");
            }
        }catch (Exception e) {
            System.out.println("wrong");
        }

        try{
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery("select id,username,password,email,gender,birthDate from userxjc");
            while(rs.next()){
                PrintWriter writer = response.getWriter();
                writer.println("<br> username :" + username);
                writer.println("<br> password :" + password);
                writer.println("<br> email :" + email);
                writer.println("<br> gender :" + gender);
                writer.println("<br> birthDate :" + birthDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
