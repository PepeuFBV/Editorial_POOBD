package DAO;

import java.util.List; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDAOImpl<E> implements BaseDAO<E> {
    final static String URL = "jdbc:postgresql://localhost/Editorial";
    final static String USER = "postgres";
    final static String PASS = "password"; //alterar senha
	static Connection con = null ;
	public static Connection getConnection() {
		if(con == null) {
			try {
				con = DriverManager.getConnection(URL,USER,PASS);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = null;
		}
		
	}

    @Override
    public abstract void inserir(E entity);

    @Override
    public abstract void atualizar(E entity);

    @Override
    public abstract E buscar(E entity);

    @Override
    public abstract List<E> listar(E entity);

    @Override
    public abstract void excluir(E entity);
}
