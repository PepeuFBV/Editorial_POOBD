package application.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() {
        final String URL = "jdbc:postgresql://localhost/Editorial";
        final String USER = "postgres";
        final String PASS = "password"; //alterar senha
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}