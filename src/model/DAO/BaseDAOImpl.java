package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDAOImpl<E> implements BaseDAO<E> {

    //Pedro: 1812, pedro, password

    final static String URL = "jdbc:postgresql://localhost:1812/Editorial"; //alterar database e port
    final static String USER = "pedro"; //alterar usuario
    final static String PASSWORD = "password"; //alterar senha
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