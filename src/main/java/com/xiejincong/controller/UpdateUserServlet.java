package com.xiejincong.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateUserServlet", value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//Todo 1:forward to WEB-INF/views/updateUser.jsp
        //Todo 2:create one jsp page-update User
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//Todo 1: get all (6) request parameters
        //Todo 2:create an object of User Model
        //Todo 3:set all 6 request parameters values into User model - setXXX()
       // Todo 4:create an object of UserDao
       // Todo 5:Call updateUser() in UserDao
       // Todo 6:forward to WEB-INF/views/userlnfo.jsp
    }
}
