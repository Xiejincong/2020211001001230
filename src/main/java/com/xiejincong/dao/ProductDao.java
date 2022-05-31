package com.xiejincong.dao;

import com.xiejincong.model.Product;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements  IProductDao{
    @Override
    public int save(Product product, Connection con) throws SQLException {
        int n = 0;
        String sql = "insert into product(ProductName,ProductDescription,picture,price,CategoryId) values(?,?,?,?,?)";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, product.getProductName());
        pt.setString(2, product.getProductDescription());
        if(product.getPicture()!=null) {
            //for sql server
            pt.setBinaryStream(3, product.getPicture());
            //for mysql
            //   pt.setBlob(3, product.getPicture());
        }
        pt.setDouble(4, product.getPrice());
        pt.setInt(5, product.getCategoryId());
        n = pt.executeUpdate();
        if (n > 0) {
            return n;
        }
        return 0;
    }//end save

    @Override
    public int delete(Integer productId, Connection con) {
        String sql="delete Product where productId="+productId;
        int n=0;
        try {
            Statement statement=con.createStatement();
            n=statement.executeUpdate(sql);
        }catch (Exception e){
            System.out.println("");
        }
        return n;
    }

    @Override
    public int update(Product instance, Connection con) {
        int n=0;
        int productId=instance.getProductId();
        String productName=instance.getProductName();
        String productDescription=instance.getProductDescription();
        InputStream picture=instance.getPicture();
        double price=instance.getPrice();
        int categoryId=instance.getCategoryId();
        String names[]={"productId","productName","productDescription","picture","price","categoryId"};
        String data[]={String.valueOf(productId),productName,productDescription, String.valueOf(picture),
                String.valueOf(price), String.valueOf(categoryId)};
        for (int i = 0; i < data.length; i++) {
            String sql="update Product where "+names[i]+"='"+data[i]+"'";
            try {
                Statement statement=con.createStatement();
                n=statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return n;
    }

    @Override
    public Product findById(Integer productId, Connection con) {
        String sql="select * from Product where productId="+productId;
        Product product =new Product();
        try {
            Statement statement=con.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                product.setProductId(resultSet.getInt("productId"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setProductDescription(resultSet.getString("ProductDescription"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCategoryId(resultSet.getInt("CategoryId"));
                product.setPicture(resultSet.getBinaryStream("picture"));
            }
        }catch (Exception e){
            System.out.println("");
        }
        return product;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId, Connection con) {
        String sql="select * from Product where categoryId="+categoryId;
        List<Product> list=new ArrayList<>();
        try {
            Statement statement=con.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                Product product=new Product();
                product.setProductName(resultSet.getString("ProductName"));
                product.setProductDescription(resultSet.getString("ProductDescription"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCategoryId(resultSet.getInt("CategoryId"));
                product.setPicture(resultSet.getBinaryStream("picture"));
                list.add(product);
            }
        }catch (Exception e){
            System.out.println("");
        }
        return list;
    }

    @Override
    public List<Product> findByPrice(double minPrice, double maxPrice, Connection con) throws SQLException {
        String sql="select * from Product where price between "+minPrice+" and "+maxPrice;
        List<Product> list=new ArrayList<>();
        try{
            Statement statement=con.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                Product product=new Product();
                product.setProductName(resultSet.getString("ProductName"));
                product.setProductDescription(resultSet.getString("ProductDescription"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCategoryId(resultSet.getInt("CategoryId"));
                product.setPicture(resultSet.getBinaryStream("picture"));
                list.add(product);
            }
        }catch (Exception e){
            System.out.println("");
        }
        return list;
    }

    @Override
    public List<Product> findAll(Connection con) throws SQLException {
        String sql="select * from Product";
        List<Product> list=new ArrayList<>();
        try{
            Statement statement=con.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                Product product=new Product();
                product.setProductId(resultSet.getInt("ProductId"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setProductDescription(resultSet.getString("ProductDescription"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCategoryId(resultSet.getInt("CategoryId"));
                // product.setPicture(resultSet.getBinaryStream("picture"));
                list.add(product);
            }
        }catch (Exception e){
            System.out.println("222");
        }
        return list;
    }

    @Override
    public List<Product> findByProductName(String productName, Connection con) throws SQLException {
        String sql="select * from Product where ProductName='"+productName+"'";
        List<Product> list=new ArrayList<>();
        try {
            Statement statement=con.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                Product product=new Product();
                product.setProductName(resultSet.getString("ProductName"));
                product.setProductDescription(resultSet.getString("ProductDescription"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCategoryId(resultSet.getInt("CategoryId"));
                product.setPicture(resultSet.getBinaryStream("picture"));
                list.add(product);
            }
        }catch (Exception e){
            System.out.println("");
        }
        return list;
    }

    @Override
    public List<Product> getPicture(Integer productId, Connection con) throws SQLException {
        String sql=" select picture from Product where ProductId="+productId;
        List<Product> list=new ArrayList<>();
        try {
            Statement statement=con.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                Product product=new Product();
                product.setProductName(resultSet.getString("ProductName"));
                product.setProductDescription(resultSet.getString("ProductDescription"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCategoryId(resultSet.getInt("CategoryId"));
                product.setPicture(resultSet.getBinaryStream("picture"));
                list.add(product);
            }
        }catch (Exception e){
            System.out.println("");
        }

        return list;
    }

    public byte[] getPictureById(Integer productId,Connection con) throws  SQLException{
        byte[] imgBytes=null;
        String sql="select picture from product where productId=?";
        PreparedStatement pt=con.prepareStatement(sql);
        pt.setInt(1,productId);
        ResultSet rs=pt.executeQuery();
        while (rs.next()){
            Blob blob= rs.getBlob("picture");
            imgBytes=blob.getBytes(1,(int) blob.length());
        }
        return imgBytes;
    }
}
//
