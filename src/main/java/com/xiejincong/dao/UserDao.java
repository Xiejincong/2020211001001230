package com.xiejincong.dao;

import com.xiejincong.model.User;
import com.xiejincong.week5.demo.LoginServlet;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UserDao implements IUserDao{

    @Override
    public boolean saveUser(Connection con, User user) throws SQLException {
        int id=user.getId();
        String username=user.getUsername();
        String password=user.getPassword();
        String email=user.getEmail();
        String gender=user.getGender();
        Date birthdate=user.getBirthdate();
        String s="insert into Usertable(id,username,password,email,gender,birthdate) values ( "+id+",'"+username+"','"+
                password+"','"+email+"','"+gender+"','"+birthdate+"');";
        try{
            Statement stmt = con.createStatement();
            stmt.execute(s);
            return true;
        }catch (Exception e){
           return  false;
        }
    }

    @Override
    public int deleteUser(Connection con, User user) throws SQLException {
        int id=user.getId();
        String sql="delete from Usertable where id="+id;
        int count=0;
        try {
            Statement stmt=con.createStatement();
            count=stmt.executeUpdate(sql);
        }catch (Exception e){
            System.out.println("oh No");
        }
        return  count;
    }

    @Override
    public int updateUser(Connection con, User user) throws SQLException {
        int id=user.getId();
        String username=user.getUsername();
        String password=user.getPassword();
        String email=user.getEmail();
        String gender=user.getGender();
        Date birthdate=user.getBirthdate();
        String names[] ={"username","password","email","gender","birthdate"};
        String informations[]={username,password,email,gender, String.valueOf(birthdate)};
//        for (int i = 0; i < informations.length; i++) {
//            System.out.println(informations[i]);
//        }
        int count=0;
        for(int i=0;i<informations.length;i++){
               try{
                   String sql="update Usertable set "+names[i]+"='"+informations[i]+"' where id="+id;
//                   System.out.println(sql);
                   Statement stmt=con.createStatement();
                   count=stmt.executeUpdate(sql);
               }catch (Exception e){
                   System.out.println("There are some problems");
               }
           }
        return count;
    }

    @Override
    public User findById(Connection con, Integer id) throws SQLException {
        String sql="select * from Usertable where id=?";
        PreparedStatement stmt=con.prepareStatement(sql);
        stmt.setInt(1,id);
        ResultSet rs=stmt.executeQuery();
        User user=new User();
        while (rs.next()){
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));
        }
        return  user;
    }

    @Override
    public User findByUsernamePassword(Connection con, String username, String password) throws SQLException {
        String sql="select * from Usertable where username=? and password=?;";
        PreparedStatement stmt= con.prepareStatement(sql);
        stmt.setString(1,username);
        stmt.setString(2,password);
        ResultSet rs=stmt.executeQuery();
        User user=null;
        if(rs.next()){
            user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setGender(rs.getString("gender"));
            user.setEmail(rs.getString("email"));
            user.setBirthdate(rs.getDate("BirthDate"));
        }
        return user;
    }

    @Override
    public List<User> findByUsername(Connection con, String username) throws SQLException {
        String sql="select * from Usertable where username=?";
        PreparedStatement stmt=con.prepareStatement(sql);
        stmt.setString(1,username);
        ResultSet rs=stmt.executeQuery();
        List<User> u=new ArrayList<>();
        while (rs.next()){
            User user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setGender(rs.getString("gender"));
            user.setEmail(rs.getString("email"));
            user.setBirthdate(rs.getDate("BirthDate"));
            u.add(user);
        }
        return u;
    }

    @Override
    public List<User> findByPassword(Connection con, String password) throws SQLException {
        String sql="select * from Usertable where password=?";
        PreparedStatement stmt=con.prepareStatement(sql);
        stmt.setString(1,password);
        ResultSet rs=stmt.executeQuery();
        List<User> u=new ArrayList<>();
        while (rs.next()){
            User user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setGender(rs.getString("gender"));
            user.setEmail(rs.getString("email"));
            user.setBirthdate(rs.getDate("BirthDate"));
            u.add(user);
        }
        return u;
    }

    @Override
    public List<User> findByEmail(Connection con, String email) throws SQLException {
        String sql="select * from Usertable where email=?";
        PreparedStatement stmt=con.prepareStatement(sql);
        stmt.setString(1,email);
        ResultSet rs=stmt.executeQuery();
        List<User> u=new ArrayList<>();
        while (rs.next()){
            User user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setGender(rs.getString("gender"));
            user.setEmail(rs.getString("email"));
            user.setBirthdate(rs.getDate("BirthDate"));
            u.add(user);
        }
        return u;
    }

    @Override
    public List<User> findByGender(Connection con, String gender) throws SQLException {
        String sql="select * from Usertable where gender=?";
        PreparedStatement stmt=con.prepareStatement(sql);
        stmt.setString(1,gender);
        ResultSet rs=stmt.executeQuery();
        List<User> u=new ArrayList<>();
        while (rs.next()){
            User user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setGender(rs.getString("gender"));
            user.setEmail(rs.getString("email"));
            user.setBirthdate(rs.getDate("BirthDate"));
            u.add(user);
        }
        return u;
    }

    @Override
    public List<User> findByBirthdate(Connection con, Date birthDate) throws SQLException {
        String sql="select * from Usertable where birthdate=?";
        PreparedStatement stmt=con.prepareStatement(sql);
        stmt.setDate(1, (java.sql.Date) birthDate);
        ResultSet rs=stmt.executeQuery();
        List<User> u=new ArrayList<>();
        while (rs.next()){
            User user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setGender(rs.getString("gender"));
            user.setEmail(rs.getString("email"));
            user.setBirthdate(rs.getDate("BirthDate"));
            u.add(user);
        }
        return u;
    }

    @Override
    public List<User> findAllUser(Connection con) throws SQLException {
       String s="select * from Usertable";
       Statement stmt=con.createStatement();
        List<User> u=new ArrayList<>();
       try {
          ResultSet rs=stmt.executeQuery(s);
          while (rs.next()){
              User user=new User();
              user.setId(rs.getInt("id"));
              user.setUsername(rs.getString("username"));
              user.setPassword(rs.getString("password"));
              user.setGender(rs.getString("gender"));
              user.setEmail(rs.getString("email"));
              user.setBirthdate(rs.getDate("BirthDate"));
              u.add(user);
          }
       }catch (Exception e){
           System.out.println("oh No");
       }
       return  u;
    }
}
