package model.DAO;

import java.util.ArrayList;

//TODO
//Checar a existencia do dado antes de inserir na tabela
//Fazer a adição na tabela Users antes da tabela correspondente

public interface BaseDAO<E> {

    public void inserir(E entity);
    
    public void atualizar(E entity);

    public ArrayList<E> buscarPorId(E entity);

    public ArrayList<E> listar();

    public void excluir(E entity);
    
    public ArrayList<E> buscarPorEmail(E entity);

}