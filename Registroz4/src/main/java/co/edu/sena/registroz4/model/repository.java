package co.edu.sena.registroz4.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface repository<Categories> {
    List<Categories> listAllObj() throws SQLException ;

    Categories byIdobj(Integer id) throws  SQLException;

    int saveObj(Categories t) throws SQLException;

    void deleteObj(Integer id) throws  SQLException;

    Categories createObj(ResultSet rs) throws SQLException;

}
