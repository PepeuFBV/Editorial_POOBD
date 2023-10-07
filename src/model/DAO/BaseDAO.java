package model.DAO;

import java.sql.ResultSet;

public interface BaseDAO<E> {

    public void inserir(E entity);
    
    public void atualizar(E entity);

    public ResultSet buscarPorId(E entity);

    public ResultSet listar();

    public void excluir(E entity);

}