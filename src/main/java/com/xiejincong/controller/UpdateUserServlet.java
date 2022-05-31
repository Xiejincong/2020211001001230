package com.xiejincong.controller;

import com.xiejincong.dao.UserDao;
import com.xiejincong.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "UpdateUserServlet", value = "/update")
public class UpdateUserServlet extends HttpServlet {
    Connection dbConn=null;

    @Override
    public void init() throws ServletException {
        super.init();
        dbConn=(Connection) getServletContext().getAttribute("dbConn");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/updateUser.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        //int id=user.getId();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String gender=request.getParameter("male");
        String birthdate=request.getParameter("birthday");

        if (!username.equals("")){
            user.setUsername(username);
        }
        if (password!=""){
            user.setPassword(password);
        }
        if (email!=""){
            user.setEmail(email);
        }
        if (gender!=null){
            user.setGender(gender);
        }
        if (birthdate!=""){
            try {
                Date birth = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
//                System.out.println("birthdate:"+birthdate);
//                System.out.println("birth:"+birth);
                user.setBirthdate(birth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        UserDao userDao=new UserDao();
        try {
            int count=userDao.updateUser(dbConn,user);
            if(count>0){
                System.out.println("Update Successful");
            }else {
                System.out.println("Update Failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        String informations[]={username,password,email,gender,birthdate};
//        String[][] names ={{"username"},{"password"},{"email"},{"gender"},{"birthdate"}};
//        for(int i=0;i<informations.length;i++){
//           while (informations[i]!=""){
//               try{
//                   String sql="update Usertable set "+names[i][0]+"='"+informations[i]+"' where id="+id;
//                   Statement stmt=dbConn.createStatement();
//                   count=stmt.executeUpdate(sql);
//               }catch (Exception e){
//                   System.out.println("There are some problems");
//               }
//               break;
//           }
//        }

        request.getRequestDispatcher("accountDetails").forward(request,response);
    }
}