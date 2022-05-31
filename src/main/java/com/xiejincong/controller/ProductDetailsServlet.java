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
import java.util.List;

@WebServlet(name = "ProductDetailsServlet", value = "/productDetails")
public class ProductDetailsServlet extends HttpServlet {
    Connection dbConn=null;

    @Override
    public void init() throws ServletException {
        super.init();
        dbConn=(Connection) getServletContext().getAttribute("dbConn");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=request.getParameter("id")!=null?Integer.parseInt(request.getParameter("id")):0;
        ProductDao productDao=new ProductDao();
        if(id==0){
            return;
        }
        try {
            List<Category> categoryList=Category.findAllCategory(dbConn);
            request.setAttribute("categoryList",categoryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Product product=productDao.findById(id,dbConn);
        request.setAttribute("p",product);
        String path="/WEB-INF/views/productDetails.jsp";
        request.getRequestDispatcher(path).forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
