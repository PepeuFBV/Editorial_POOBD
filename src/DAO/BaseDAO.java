package DAO;

import java.sql.ResultSet;

public interface BaseDAO<E> {

    public void inserir(E entity);
    
    public void atualizar(E entity);

    public ResultSet buscarPorId(E entity);

    public ResultSet listarTodos();

    public void excluir(E entity);

}
