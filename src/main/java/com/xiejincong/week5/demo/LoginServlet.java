package com.xiejincong.week5.demo;

import com.xiejincong.dao.UserDao;
import com.xiejincong.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

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
request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out= response.getWriter();
        String uname=request.getParameter("username");
     String upw=request.getParameter("password");
        UserDao userDao=new UserDao();
        User user=null;
        try {


            user = userDao.findByUsernamePassword(con,uname,upw);
            if(user!=null){
                String rememberMe=request.getParameter("rememberMe");
                if(request.getParameter("rememberMe").equals("1")){
                    Cookie usernameCookie=new Cookie("cUsername",user.getUsername());
                    Cookie passwordCookie=new Cookie("cPassword",user.getPassword());
                    Cookie rememberMeCookie=new Cookie("cRememberMe",rememberMe);

                  usernameCookie.setMaxAge(5);
                  passwordCookie.setMaxAge(5);
                  rememberMeCookie.setMaxAge(5);

                  response.addCookie(usernameCookie);
                  response.addCookie(passwordCookie);
                  response.addCookie(rememberMeCookie);
                }





               HttpSession session=request.getSession();
               System.out.println("session id-->"+session.getId());
session.setMaxInactiveInterval(10);

                session.setAttribute("user",user);
request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
            }else {
request.setAttribute("message","Username or Password Error!");
request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

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
