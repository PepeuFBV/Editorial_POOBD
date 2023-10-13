package model.DAO;

import java.util.ArrayList;

public interface BaseDAO<E> {

    public void inserir(E entity);
    
    public void atualizar(E entity);

    public ArrayList<E> listar();

    public void excluir(E entity);

}