package co.edu.sena.registroz4.model;

import co.edu.sena.registroz4.model.Categories;
import co.edu.sena.registroz4.util.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements repository<Categories> {

    private  String sql = null ;

    @Override
    public List<Categories> listAllObj() throws SQLException {
        sql = "select c.category_id, c.category_name " + "from Categories c order by c.category_id, c.category_name ";
        List<Categories> categories = new ArrayList<>();
        try (Connection conn = ConnectionPool.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs =  stmt.executeQuery(sql)){
            while (rs.next()){
                Categories c = createObj(rs);
                categories.add(c);
            } // while
        } // try
        return categories;
    }

    @Override
    public Categories byIdobj(Integer id) throws SQLException {
        sql = "select c.category_id, c.category_name "+"from Categories c where c.category_id = ? ";
        Categories categories = null ;

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    categories = createObj(rs);
                } // if conditional
            } // try num two
        } // try
        return categories;
    } // byIdObj

    @Override
    public int saveObj(Categories categories) throws SQLException {
        int rowsAffected = 0 ;
        if (categories.getCategory_id() !=null && categories.getCategory_id() > 0) {
            sql = "update Categories set category_name = ? "+"where category_id = ? ";
        } else {
            sql = "insert into Categories (category_name ) "+"values (upper(?), upper(?), lower(?) ";
        }

        try (Connection conn = ConnectionPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, categories.getCategory_name());
            if (categories.getCategory_id() != null && categories.getCategory_id() > 0){
                ps.setInt(2, categories.getCategory_id());
            }

            rowsAffected = ps.executeUpdate();
        }

        return rowsAffected;
    } // saveObj

    @Override
    public void deleteObj(Integer id) throws SQLException {
        sql = "delete from Categories where category_id = ? ";
        try (Connection conn = ConnectionPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } // try
    } // deleteObj

    @Override
    public Categories createObj(ResultSet rs) throws SQLException {
        Categories categories = new Categories();
        categories.setCategory_id(rs.getInt("category_id"));
        categories.setCategory_name(rs.getString("category_name"));
        return categories;
    }
} // UserRepositoryImpl
