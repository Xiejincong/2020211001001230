package com.xiejincong.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private int categoryId;
    private String categoryName;
    private String description;
    private boolean active;

    public Category(int categoryId, String categoryName, String description, boolean active) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.active = active;
    }

    public Category(){}

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }
    public static List<Category> findAllCategory(Connection con) throws SQLException{
        String sql="select * from Category";
        List<Category> list=new ArrayList<>();
        try {
            Statement statement=con.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                Category c=new Category();
                c.setCategoryId(resultSet.getInt("categoryId"));
                c.setCategoryName(resultSet.getString("categoryName"));
                c.setDescription(resultSet.getString("description"));
                c.setActive(resultSet.getBoolean("active"));
                list.add(c);
            }

        }catch (Exception e){
            System.out.println("on No");
        }
        return list;
    }

    public  static String findByCategoryId(Connection con,int categoryId) throws SQLException{
        String sql="select CategoryName from Category where CategoryId="+categoryId;
        String CategoryName=null;
        try{
            Statement statement=con.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                CategoryName=resultSet.getString("CategoryName");
            }
        }catch (Exception e){
            System.out.println("oh No");
        }
        return CategoryName;
    }
}
