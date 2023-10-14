package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDAOImpl<E> implements BaseDAO<E> {

    final static String URL = "jdbc:postgresql://localhost:5432/Editorial"; //alterar database e port
    final static String USER = "postgres"; //alterar usuario
    final static String PASSWORD = "root123"; //alterar senha
	static Connection con = null;

	public static Connection getConnection() {
		if (con == null) {
			try {
				con = DriverManager.getConnection(URL,USER,PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return con;
		}
		else return con;
			
	}
	
	public static void closeConnection() {
		if(con !=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con = null;
		}
	}

}