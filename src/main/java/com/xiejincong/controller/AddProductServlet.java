package com.xiejincong.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "AddProductServlet", value = "/admin/addProduct")
@MultipartConfig(maxFileSize = 16177215)
public class AddProductServlet extends HttpServlet {
    Connection dbConn = null;

    @Override
    public void init() throws ServletException {
        super.init();
        dbConn = (Connection) getServletContext().getAttribute("dbConn");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> categoryList = Category.findAllCategory(dbConn);
            request.setAttribute("categoryList", categoryList);
            request.getRequestDispatcher("/WEB-INF/views/admin/addProduct.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("oh No");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        double price = request.getParameter("price") != null ? Double.parseDouble(request.getParameter("price")) : 0.0;
        int categoryId = request.getParameter("categoryId") != null ? Integer.parseInt(request.getParameter("categoryId")) : 8;
        //picture
        InputStream inputStream = null;
        Part filePart = request.getPart("picture");
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        }

        Product product = new Product();
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setPrice(price);
        product.setCategoryId(categoryId);
        product.setPicture(inputStream);

        ProductDao productDao = new ProductDao();
        try {
            int n = productDao.save(product, dbConn);
            if (n > 0) {
                response.sendRedirect("productList");
            } else {
                System.out.println("增加失败");
            }
        } catch (Exception e) {
            System.out.println("出现问题");
        }


    }
}