package co.edu.sena.registroz4.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface repositoryP<Products>{
    List<Products> listAllObj() throws SQLException;

    Products byIdobj(Integer id) throws  SQLException;

    int saveObj(Products t) throws SQLException;

    void deleteObj(Integer id) throws  SQLException;

    Products createObj(ResultSet rs) throws SQLException;
}
