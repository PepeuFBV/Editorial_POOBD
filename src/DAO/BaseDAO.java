package DAO;

import java.sql.Connection;
import java.util.List;

public interface BaseDAO<E> {

    public Connection getConnection();

    public void inserir(E entity);
    
    public void atualizar(E entity);

    public E buscar(E entity);

    public List<E> listar(E entity);

    public void excluir(E entity);
    
}
