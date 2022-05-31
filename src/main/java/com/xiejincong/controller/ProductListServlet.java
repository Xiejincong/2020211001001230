package com.xiejincong.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "ProductListServlet", value = "/admin/productList")
public class ProductListServlet extends HttpServlet {
    Connection dbConn=null;

    @Override
    public void init() throws ServletException {
        super.init();
        dbConn=(Connection) getServletContext().getAttribute("dbConn");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDao=new ProductDao();
        try{
            List<Product> productList=productDao.findAll(dbConn);
            HttpSession session=request.getSession();
            session.setAttribute("productList",productList);

        }catch(Exception e){
            System.out.println("111");
        }
        String path="/WEB-INF/views/admin/productList.jsp";
        request.getRequestDispatcher(path).forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
