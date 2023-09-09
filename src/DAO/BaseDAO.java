package DAO;

import java.util.List;

public interface BaseDAO<E> {

    public void inserir(E entity);
    
    public void atualizar(E entity);

    public E buscar(E entity);

    public List<E> listar(E entity);

    public void excluir(E entity);
    
}
