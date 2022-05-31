package com.xiejincong.controller;
import com.xiejincong.dao.ProductDao;
import com.xiejincong.model.Category;
import com.xiejincong.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShopServlet", value = "/shop")
public class ShopServlet extends HttpServlet {
    Connection dbConn=null;

    @Override
    public void init() throws ServletException {
        super.init();
        dbConn=(Connection) getServletContext().getAttribute("dbConn");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category=new Category();
        List<Category> categoryList= null;
        try {
            categoryList = category.findAllCategory(dbConn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("categoryList",categoryList);

        ProductDao productDao=new ProductDao();
        List<Product> productList=null;
        try {
            if(request.getParameter("categoryId")==null){
                productList=productDao.findAll(dbConn);
            }else {
                int categoryId=Integer.parseInt(request.getParameter("categoryId"));
                productList=productDao.findByCategoryId(categoryId,dbConn);
            }
        }catch (Exception e){
            System.out.println("oh No");
        }
        request.setAttribute("productList",productList);
        String path="/WEB-INF/views/shop.jsp";
        request.getRequestDispatcher(path).forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
