package model.DAO;

import java.sql.ResultSet;

import model.VO.GerenteVO;

public class GerenteDAO extends UsersDAO<GerenteVO> {
    
	@Override
    public void inserir(GerenteVO gerente) {
		super.inserir(gerente);
	}
    
    @Override
    public void atualizar(GerenteVO gerente) {
        super.atualizar(gerente);
    }

    @Override
    public ResultSet buscarPorId(GerenteVO gerente) {
        return super.buscarPorId(gerente);
    }

    @Override
    public ResultSet listar() {
        return super.listar();
    }

    @Override
    public void excluir(GerenteVO gerente) {
        super.excluir(gerente);
    }

    
}