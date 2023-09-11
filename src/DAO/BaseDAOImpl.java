package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDAOImpl<VO> implements BaseDAO<VO> {

    final static String URL = "jdbc:postgresql://localhost/Editorial";
    final static String USER = "postgres"; //alterar usuario
    final static String PASS = "password"; //alterar senha
	static Connection con = null;

	public static Connection getConnection() {
		if(con == null) {
			try {
				con = DriverManager.getConnection(URL,USER,PASS);
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

    @Override
    public abstract void inserir(VO vo);

    @Override
    public abstract void atualizar(VO vo);

    @Override
    public abstract ResultSet buscarPorId(VO vo);

    @Override
    public abstract ResultSet listarTodos(VO vo);

    @Override
    public abstract void excluir(VO vo);
}
