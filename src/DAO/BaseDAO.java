package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BaseDAO<VO> {

    public void inserir(VO entity);
    
    public void atualizar(VO entity);

    public ResultSet buscarPorId(VO entity);

    public ResultSet listarTodos(VO entity) throws SQLException;

    public void excluir(VO entity);
    
    //TODO
    //adicionar os outros tipos de busca e de listar
    //se deve remover o buscar e listar da classe gerente?
    //metodos de busca por x devem receber objeto ou tipo primitivo?
    //adicionar os close connection nos metodos dos DAO
}
