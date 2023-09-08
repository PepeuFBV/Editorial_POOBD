package DAO;

import java.sql.Connection;

public abstract class BaseDAOImpl<E> implements BaseDAO<E> {
    
    private Connection con;
    private String URL;
    private String USER;
    private String PASSWORD;
    private ConnectionFactory connectionFactory; //tenho que adicionar isso?
    
}
