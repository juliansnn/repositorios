package co.edu.sena.registroz4.model;

import co.edu.sena.registroz4.util.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import co.edu.sena.registroz4.model.Products;

public class ProductRepositoryImpl implements repositoryP<Products>{

    private  String sql = null ;

    @Override
    public List<Products> listAllObj() throws SQLException {
        sql = "select p.product_id, p.product_name, p.product_value, p.category_id " + "from Products p order by p.category_id, p.product_id, p.product_name, p.product_value ";
        List<Products> products = new ArrayList<>();
        try (Connection conn = ConnectionPool.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs =  stmt.executeQuery(sql)){
            while (rs.next()){
                Products p = createObj(rs);
                products.add(p);
            } // while
        } // try
        return products;
    }

    @Override
    public Products byIdobj(Integer id) throws SQLException {
        sql = "select p.product_id, p.product_name, p.product_value, p.category_id "+"from Products p where p.product_id = ? ";
        Products products = null ;

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    products = createObj(rs);
                } // if conditional
            } // try num two
        } // try
        return products;
    } // byIdObj

    @Override
    public int saveObj(Products products) throws SQLException {
        int rowsAffected = 0 ;
        if (products.getProduct_id() !=null && products.getCategory_id() > 0) {
            sql = "update Products set product_name = ?, product_value = ?, category_id = ? "+"where product_id = ? ";
        } else {
            sql = "insert into Products (product_name, product_value, category_id ) "+"values (upper(?), upper(?), lower(?) ";
        }

        try (Connection conn = ConnectionPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, products.getProduct_name());
            ps.setInt(2, products.getProduct_value());
            ps.setInt(3,products.getCategory_id());
            if (products.getCategory_id() != null && products.getCategory_id() > 0){
                ps.setInt(4, products.getProduct_id());
            }

            rowsAffected = ps.executeUpdate();
        }

        return rowsAffected;
    } // saveObj

    @Override
    public void deleteObj(Integer id) throws SQLException {
        sql = "delete from Products where product_id = ? ";
        try (Connection conn = ConnectionPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } // try
    } // deleteObj
    @Override
    public Products createObj(ResultSet rs) throws SQLException {
        Products products = new Products();
        products.setProduct_id(rs.getInt("product_id"));
        products.setProduct_name(rs.getString("product_name"));
        products.setProduct_value(rs.getInt("product_value"));
        products.setCategory_id(rs.getInt("category_id"));
        return products;
    }
}
