package co.edu.sena.registroz4.util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UseConnectionPool {
    private static Connection getConnection()
            throws SQLException {
        return ConnectionPool.getConnection();
    }
    public static void main(String[] args)
            throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Registroz4.Moto ")) {
            while (rs.next()) {
                System.out.print(rs.getString("Placa_moto"));
                System.out.print(" | ");

                System.out.println(rs.getString("modelo_moto"));
                System.out.print(" | ");

                System.out.println(rs.getString("Cilindraje_moto"));
                System.out.print(" | ");

                System.out.println(rs.getString("kilometros_recorridos"));
                System.out.print(" | ");

                System.out.println(rs.getString("Cilindraje_moto"));
                System.out.print(" | ");

                System.out.println(rs.getString("fecha"));
                System.out.print(" | ");

                System.out.println(rs.getString("Caracteristicas"));
            }
        }
    }
}